package client;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import message.Message;
import message.Type;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
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
    TextArea textAreaChat;
    @FXML
    Button btnSend;
    @FXML
    Button btnConnect;
    @FXML
    Button btnBlock;

    private final String URL = "localhost";
    private final int PORT = 8189;

    private final String PASS_REGEX = "^(?=.*[0-9])(?=.*[A-Z])(?=.*[a-z])(?=.*[!@#\\$%\\^\\.&\\*_])[0-9a-zA-Z!@#\\$%\\^\\.&\\*_]{8,}$";
    private String nick = "Guest";
    private final String MESSAGE = "[%s] %s%n";
    private Socket socket;
    private DataInputStream in;
    public static DataOutputStream out;
    private boolean isConnected = false;

    private ObjectOutputStream messageOut;
    private ObjectInputStream messageIn;


    public void initialize() {
        textFieldNick.setText("Guest");
        textFieldLogin.setText("Enter Login ...");
        textFieldPass.setText("Enter pass ...");
        TreeItem<Label> root = new TreeItem<>(new Label("All"));
        root.setExpanded(true);
        treeViewUsers.setRoot(root);
        treeViewUsers.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        treeViewUsers.getSelectionModel().select(0);
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

            try {
                messageOut.writeObject(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        textFieldMessage.clear();
        textFieldMessage.requestFocus();

    }

    public void setNick() {
        nick = textFieldNick.getText();
        textFieldNick.setText(nick);
        textFieldMessage.requestFocus();
    }

    public void block() {

    }

    public void connect() {
        if (isConnected) {
            isConnected = false;
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    Main.stage.setTitle("Disconnected");
                    btnConnect.setText("Connect");
                }
            });
            treeViewUsers.getRoot().getChildren().clear();
            try {
                messageOut.writeObject(new Message(Type.DISCONNECT,nick,"Bye ..."));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try {
                socket = new Socket(URL, PORT);
//            in = new DataInputStream(socket.getInputStream());
//            out = new DataOutputStream(socket.getOutputStream());
                messageOut = new ObjectOutputStream(socket.getOutputStream());
                messageIn = new ObjectInputStream(socket.getInputStream());

//  Send login and pass
                isConnected = true;
                startReaderThread();
                messageOut.writeObject(new Message(Type.AUTHREQUEST, textFieldLogin.getText(), textFieldPass.getText()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public void passwordIsValid() {
        Pattern p = Pattern.compile(PASS_REGEX);
        String pass = textFieldPass.getText();
        StringBuilder sb = new StringBuilder();
        if (p.matcher(pass).find()) {
            sb.append("Password is valid\n");
        } else {
            if (pass.length() < 8) {
                sb.append("Password length is less than 8\n");
            }
            p = Pattern.compile("[0-9]");
            if (!p.matcher(pass).find()) {
                sb.append("Number character absent\n");
            }
            p = Pattern.compile("[a-z]");
            if (!p.matcher(pass).find()) {
                sb.append("Lower case characters absent\n");
            }
            p = Pattern.compile("[A-Z]");
            if (!p.matcher(pass).find()) {
                sb.append("Capital case characters absent\n");
            }
            p = Pattern.compile("[!@#\\$%\\^\\.&\\*_]");
            if (!p.matcher(pass).find()) {
                sb.append("Special character absent\n");
            }

            p = Pattern.compile("[^0-9a-zA-Z!@#\\$%\\^\\.&\\*_]");
            if (p.matcher(pass).find()) {
                sb.append("Not correct characters used\n");
            }
        }
        textAreaChat.appendText(sb.toString() + "\n");
    }

    public void startReaderThread() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (isConnected) {
                        Message message = (Message) messageIn.readObject();
                        switch (message.getType()) {
                            case JOIN:
                            case LEFT:
                            case PRIVATE:
                            case MESSAGE:
                                textAreaChat.appendText(message.toString());
                                break;
                            case AUTHNG:
                            case DISCONNECT:
                                isConnected = false;
                                // ToDo invisible
                                textAreaChat.appendText(message.toString());
                                break;
                            case AUTHOK:
                                nick = message.getFrom();

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
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }
        }).start();
    }

}
