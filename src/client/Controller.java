package client;

import client.task.ReconnectTask;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import message.Message;
import message.Type;

import java.io.*;
import java.net.Socket;
import java.util.*;
import java.util.regex.Pattern;

public class Controller {
    @FXML
    TreeView<Label> treeViewUsers;
    @FXML
    TextField textFieldLogin;
    @FXML
    TextField textFieldPass;
    @FXML
    TextField textFieldNick;
    @FXML
    TextField textFieldMessage;
    @FXML
    WebView webViewChat;
    @FXML
    Button btnSend;
    @FXML
    Button btnConnect;
    @FXML
    Button btnBlock;

    private WebEngine webEngine;
    private Controller controller;
    private final String URL = "localhost";
    private final int PORT = 8189;
    private final String PASS_REGEX = "^(?=.*[0-9])(?=.*[A-Z])(?=.*[a-z])(?=.*[!@#\\$%\\^\\.&\\*_])[0-9a-zA-Z!@#\\$%\\^\\.&\\*_]{8,}$";
    private String nick = "Guest";
    private Socket socket;
    private boolean isConnected = false;
    private boolean isManualConnected = false;
    private ObjectOutputStream messageOut;
    private ObjectInputStream messageIn;
    private Map<String, ModalWindow> privateWindows;
    private StringBuilder chatStrings;
    private Timer reconnectTimer;
    private RegisterWindow registerWindowController;
    private static final String BAN = "-fx-text-fill: gray;";
    private static final String UN_BAN = "-fx-text-fill: black;";

    public boolean isManualConnected() {
        return isManualConnected;
    }

    public void initialize() {
        registerWindowController = null;
        controller = this;
        chatStrings = new StringBuilder();
        privateWindows = new HashMap<>();
        webEngine = webViewChat.getEngine();
        textFieldNick.setText("Guest");
        textFieldLogin.setText("Enter Login ...");
        textFieldPass.setText("Enter pass ...");
        TreeItem<Label> root = new TreeItem<>(new Label("All"));
        root.setExpanded(true);
        treeViewUsers.setRoot(root);
        treeViewUsers.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        treeViewUsers.getSelectionModel().select(0);
        treeViewUsers.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.getClickCount() == 2){
                    newPrivateWindow(treeViewUsers.getSelectionModel().getSelectedItem().getValue().getText());
                }
            }
        });
    }

    public void newPrivateWindow(String nick){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                Parent root = null;
                try {
                    FXMLLoader loader = new FXMLLoader();

                    root = loader.load(getClass().getResourceAsStream("modalWindow.fxml"));
                    ModalWindow windowController = loader.getController();
                    Stage newWindow = new Stage();
                    newWindow.setTitle("Private chat: " + nick);
                    newWindow.setScene(new Scene(root, 300, 200));
                    newWindow.initModality(Modality.NONE);
                    newWindow.initOwner(Main.stage);
                    newWindow.setX(Main.stage.getX() + 200);
                    newWindow.setY(Main.stage.getY() + 100);
                    windowController.setParent(controller);
                    windowController.setNick(nick);
                    privateWindows.put(nick,windowController);
                    System.out.println("Put collection" + privateWindows.size());
                    newWindow.setOnCloseRequest(new EventHandler<WindowEvent>() {
                        @Override
                        public void handle(WindowEvent event) {
                            privateWindows.remove(nick);
                        }
                    });
                    newWindow.show();
                    System.out.println("Put collection" + privateWindows.size());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void newRegisterWindow(Message message){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                FXMLLoader loader = new FXMLLoader();
                try {
                    Parent root = loader.load(getClass().getResourceAsStream("registerWindow.fxml"));
                    registerWindowController = loader.getController();
                    Stage registerStage = new Stage();
                    registerStage.setTitle("Register");
                    registerStage.setScene(new Scene(root,250,260));
                    registerStage.initModality(Modality.APPLICATION_MODAL);
                    registerStage.initOwner(Main.stage);
                    registerStage.setX(Main.stage.getX() + 200);
                    registerStage.setY(Main.stage.getY() + 100);
                    registerWindowController.setParent(controller);
                    registerWindowController.setStage(registerStage);
                    registerStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                        @Override
                        public void handle(WindowEvent event) {
                            registerWindowController = null;
                        }
                    });
                    registerWindowController.setReason(message);
                    registerStage.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }


            }
        });
    }

    public void sendMessage() {
        if (isConnected) {
            Message message = new Message(Type.PRIVATE, nick, textFieldMessage.getText());
            List<TreeItem<Label>> users = treeViewUsers.getSelectionModel().getSelectedItems();
            for (TreeItem<Label> selectedUser :
                    users) {
                if (selectedUser.getValue().getText().equals("All")){
                    message = new Message(Type.MESSAGE, nick, textFieldMessage.getText());
                    break;
                }else{
                    message.getDestination().add(selectedUser.getValue().getText());
                }
            }

            send(message);
        }
        textFieldMessage.clear();
        textFieldMessage.requestFocus();
    }

    public void send(Message message){
        try {
            messageOut.writeObject(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setNick() {
        nick = textFieldNick.getText();
        textFieldNick.setText(nick);
        textFieldMessage.requestFocus();
    }

    public void ban() {
        Iterator<TreeItem<Label>> iterator = treeViewUsers.getSelectionModel().getSelectedItems().iterator();
        while (iterator.hasNext()){
            TreeItem<Label> label = iterator.next();
            if (label.getValue().getStyle().equals(BAN)){
                label.getValue().setStyle(UN_BAN);
                send(new Message(Type.BANREMOVE,nick,label.getValue().getText()));
            }else{
                label.getValue().setStyle(BAN);
                send(new Message(Type.BANADD,nick,label.getValue().getText()));
            }
        }
    }

    public void manualConnect(){
        if (isManualConnected){
            isManualConnected = false;
        }else{
            isManualConnected = true;
        }

        connect();
    }

    public void connect() {
        if (isConnected) {
            isConnected = false;
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    Main.stage.setTitle("Disconnected");
                    btnConnect.setText("Con/Reg");
                }
            });
            treeViewUsers.getRoot().getChildren().clear();
                send(new Message(Type.DISCONNECT,nick,"Bye ..."));
        } else {
            try {
                socket = new Socket(URL, PORT);
                messageOut = new ObjectOutputStream(socket.getOutputStream());
                messageIn = new ObjectInputStream(socket.getInputStream());

                isConnected = true;
                startReaderThread();
                send(new Message(Type.AUTHREQUEST, textFieldLogin.getText(), textFieldPass.getText()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public String getNick() {
        return nick;
    }

    public boolean isConnected() {
        return isConnected;
    }

    public void startReaderThread() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (isConnected) {
                        Message message = (Message) messageIn.readObject();
                        switch (message.getType()) {
                            case REGISTERNG:
                            case REGISTEROK:
                                if (registerWindowController != null){
                                    registerWindowController.setRegisterStatus(message);
                                }
                                break;
                            case CHECKLOGIN:
                                if (registerWindowController != null){
                                    registerWindowController.setLoginValid(message);
                                }
                                break;
                            case CHECKNICK:
                                if (registerWindowController != null){
                                    registerWindowController.setNickValid(message);
                                }
                                break;
                            case PING:
                                reconnectTimer.cancel();
                                reconnectTimer = new Timer();
                                reconnectTimer.schedule(new ReconnectTask(controller),10000L,1000L);
                                System.out.println("Timer cancel");
                                break;
                            case JOIN:
                            case LEFT:
                            case PRIVATE:
                            case MESSAGE:
                                Platform.runLater(new Runnable() {
                                    @Override
                                    public void run() {
                                        webEngine.loadContent(computeString(message));
                                    }
                                });

                                break;
                            case AUTHNG:
                                newRegisterWindow(message);
                                break;
                            case DISCONNECT:
                                isConnected = false;
                                // ToDo invisible
                                Platform.runLater(new Runnable() {
                                    @Override
                                    public void run() {
                                        webEngine.loadContent(computeString(message));
                                    }
                                });
                                break;
                            case AUTHOK:
                                nick = message.getFrom();
                                reconnectTimer = new Timer();
                                reconnectTimer.schedule(new ReconnectTask(controller),10000L,1000L);
                                Platform.runLater(new Runnable() {
                                    @Override
                                    public void run() {
                                        textFieldNick.setText(nick);
                                        Main.stage.setTitle("Connected as " + nick);
                                        btnConnect.setText("Disconnect");
                                        textFieldMessage.clear();
                                        textFieldMessage.requestFocus();
                                    }
                                });
                                break;
                            case USERS:
                                TreeItem<Label> root = (TreeItem<Label>) treeViewUsers.getRoot();
                                List<TreeItem<Label>> users = treeViewUsers.getSelectionModel().getSelectedItems();
                                Set<String> setUsers = new HashSet<>();

                                for (TreeItem<Label> selectedUser :
                                        users) {
                                    setUsers.add(selectedUser.getValue().getText());
                                }
                                root.getChildren().clear();
                                int count = 0;
                                for (String s :
                                        message.getDestination()) {
                                    count++;
                                    root.getChildren().add(new TreeItem<>(new Label(s)));
                                    if (setUsers.contains(s)){
                                        treeViewUsers.getSelectionModel().select(count);
                                    }
                                }
                                break;
                            case BANLIST:
                                Set<String> banlist = message.getDestination();
                                Iterator<TreeItem<Label>> iterator = treeViewUsers.getRoot().getChildren().iterator();
                                while (iterator.hasNext()){
                                    TreeItem<Label> label = iterator.next();
                                    if (banlist.contains(label.getValue().getText())){
                                        label.getValue().setStyle(BAN);
                                    }
                                }
                                break;
                            case PRIVATECHAT:
                                if (message.getFrom().equals(nick)){
                                    Set<String> set = message.getDestination();
                                    for (String s :
                                            set) {
                                        ModalWindow window = privateWindows.get(s);
                                        Platform.runLater(new Runnable() {
                                            @Override
                                            public void run() {
                                                window.printMessage(message);
                                            }
                                        });
                                    }
                                }else {
                                    if (!privateWindows.containsKey(message.getFrom())) {
                                        newPrivateWindow(message.getFrom());
                                    }
                                    while (!privateWindows.containsKey(message.getFrom())) ;
                                    System.out.println(privateWindows.size());
                                    ModalWindow window = privateWindows.get(message.getFrom());


                                    Platform.runLater(new Runnable() {
                                        @Override
                                        public void run() {
                                            window.printMessage(message);
                                        }
                                    });
                                }
                                break;
                            default:
                                break;
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        messageIn.close();
                        messageOut.close();
                        socket.close();
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                Main.stage.setTitle("Disconnected");
                                btnConnect.setText("Con/Reg");
                            }
                        });
                        isConnected = false;
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }
        }).start();
    }

    public String computeString(Message message){
        final String header =
                "<html>\n" +
                        " <head>\n" +
                        "  <meta charset=\"utf-8\">\n" +
                        "  <title>text-align</title>\n" +
                        "  <style>\n" +
                        "   #left { \n" +
                        "    clear: center;\n" +
                        "    clear: rigth;\n" +
                        "    clear: left;\n" +
                        "    float: left; \n" +
                        "    width: 75%; \n" +
                        "    margin-bottom: 5px;\n" +
                        "    word-wrap: break-word;\n" +
                        "    color: black;\n" +
                        "    background: #e6e7e8;\n" +
                        "    padding-left: 5px;\n" +
                        "    padding-top: 4px;\n" +
                        "    padding-bottom: 4px;\n" +
                        "    padding-right: 5px;\n" +
                        "    border-radius: 5px;\n" +
                        "   }\n" +

                        "   #right { \n" +
                        "    clear: left;\n" +
                        "    clear: center;\n" +
                        "    clear: right;\n" +
                        "    float: right;\n" +
                        "    width: 75%; \n" +
                        "    margin-bottom: 5px;\n" +
                        "    word-wrap: break-word;\n" +
                        "    color: white;\n" +
                        "    background: #7facf5;\n" +
                        "    padding-left: 5px;\n" +
                        "    padding-top: 4px;\n" +
                        "    padding-bottom: 4px;\n" +
                        "    padding-right: 5px;\n" +
                        "    border-radius: 5px;\n" +
                        "   }\n" +
                        "   #center { \n" +
                        "    margin: 0 auto;\n" +
                        "   clear: left;\n" +
                        "   clear: rigth;\n" +
                        "   clear: center;\n" +
                        "   float: left;\n" +
                        "    width: 99%; \n" +
                        "    margin-bottom: 5px;\n" +
                        "    word-wrap: break-word;\n" +
                        "    color: black;\n" +
                        "    background: #b3ffcc;\n" +
                        "    padding-left: 5px;\n" +
                        "    padding-top: 4px;\n" +
                        "    padding-bottom: 4px;\n" +
                        "    padding-right: 5px;\n" +
                        "    border-radius: 5px;\n" +
                        "   }\n" +
                        "   #cont { \n" +
                        "    clear: center;\n" +
                        "    clear: rigth;\n" +
                        "    clear: left;\n" +
                        "    float: left;\n" +
                        "    margin-bottom: 5px;\n" +
                        "    padding-left: 5px;\n" +
                        "    padding-top: 4px;\n" +
                        "    padding-bottom: 4px;\n" +
                        "    padding-right: 5px;\n" +
                        "    border-radius: 5px;\n" +
                        "    width: 10%; \n" +
                        "    color: white; \n" +
                        "   }\n" +
                        "  </style>\n" +
                        "   <script language=\"javascript\" type=\"text/javascript\">\n" +
                        "       function toBottom(){\n" +
                        "           window.scrollTo(0, 10000000 );\n" +
                        "       }\n" +
                        "   </script>\n" +
                        " </head>\n" +
                        " <body onload='toBottom()'>\n";
        final String bottom =
                " </body>\n" +
                        "</html>";


        final String htmlLeft = "<div id=\"left\"><tt>%s</tt></div>\n";
        final String htmlRight = "<div id=\"right\"><tt>%s</tt></div>\n";
        final String htmlCenter = "<div id=\"center\"><tt align=\"center\">%s</tt></div>\n";

        if (message.getFrom().equals(nick)){
            chatStrings.append(String.format(htmlRight,message.toString()));
        }else if (message.getFrom().equals("server")){
            chatStrings.append(String.format(htmlCenter,message.toString()));
        }else{
            chatStrings.append(String.format(htmlLeft,message.toString()));
        }
        return header+ chatStrings.toString() + bottom;
    }

}
