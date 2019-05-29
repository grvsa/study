package client;

import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Iterator;

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

    Socket chatSocket;
    DataInputStream inputStreamChat;
    DataOutputStream outputStreamChat;
    Socket serviceSocket;
    DataInputStream inputStreamService;
    DataOutputStream outputStreamService;
    boolean isConnected;
    String title;
    String nick;
    String url = "localhost";
    int port = 8189;
    Thread serviceThread;
    Thread chatThread;

    private static final String BAN = "-fx-text-fill: gray;";
    private static final String UN_BAN = "-fx-text-fill: black;";
    private static final String PRIVATE_MESSAGE = "private %s %s";


    public void initialize() {
        Label label = new Label("All");
        TreeItem<Label> root = new TreeItem<Label>(label);
        root.setExpanded(true);
        treeViewUsers.setRoot(root);
        treeViewUsers.getSelectionModel().select(0);
        isConnected = false;
    }

    public void connect(){
        try {
            if(!isConnected) {
                chatSocket = new Socket(url, port);
                inputStreamChat = new DataInputStream(chatSocket.getInputStream());
                outputStreamChat = new DataOutputStream(chatSocket.getOutputStream());

                serviceSocket = new Socket(url, port);
                inputStreamService = new DataInputStream(serviceSocket.getInputStream());
                outputStreamService = new DataOutputStream(serviceSocket.getOutputStream());

                isConnected = auth();
                if (isConnected) {
                    textFieldMessage.setVisible(true);
                    textFieldNick.setVisible(true);
                    btnSend.setVisible(true);
                    btnBlock.setVisible(true);
                    treeViewUsers.setVisible(true);
                    textAreaChat.setVisible(true);
                    btnConnect.setText("Disconnect");
                    start();
                }else{
                    inputStreamChat.close();
                    outputStreamChat.close();
                    inputStreamService.close();
                    outputStreamService.close();
                    chatSocket.close();
                    serviceSocket.close();
                }
            }else{
                serviceMsg("disconnect");
                disconnect();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void start(){
        chatThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (isConnected && !Thread.currentThread().isInterrupted()) {
                        String str = inputStreamChat.readUTF();
                        textAreaChat.appendText(str + "\n");
                    }
                }catch (IOException e){
                    e.printStackTrace();
                }finally {
                    try {
                        inputStreamChat.close();
                        outputStreamChat.close();
                        chatSocket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        serviceThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (isConnected && !Thread.currentThread().isInterrupted()) {
                        String str = inputStreamService.readUTF();
                        if (str.startsWith("disconnect")){
                            connect();
                        }else if (str.startsWith("setNick")){
                            String[] newNick = str.split(" ");
                            nick = newNick[1];
                            textFieldNick.setText(nick);
                        }else if(str.startsWith("loadUsers")){
                            String selected = treeViewUsers.getSelectionModel().getSelectedItem().getValue().getText();
                            treeViewUsers.getRoot().getChildren().clear();
                            treeViewUsers.getSelectionModel().select(0);
                            String[] users = str.split(" ");
                            int count = 0;
                            for (int i = 1; i < users.length ; i++) {
                                count++;
                                treeViewUsers.getRoot().getChildren().add(new TreeItem<>(new Label(users[i])));
                                if (users[i].equals(selected)){
                                    treeViewUsers.getSelectionModel().select(count);
                                }

                            }
                        }else if (str.startsWith("blackList")){
                            Iterator<TreeItem<Label>> iterator = treeViewUsers.getRoot().getChildren().iterator();
                            while (iterator.hasNext()){
                                Label label = iterator.next().getValue();
                                String text = label.getText();
                                if (str.contains(text)){
                                    label.setStyle(BAN);
                                }else{
                                    label.setStyle(UN_BAN);
                                }
                            }
                        }
                    }
                }catch (IOException e){
                    e.printStackTrace();
                }finally {
                    try {
                        inputStreamService.close();
                        outputStreamService.close();
                        serviceSocket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        serviceThread.start();
        chatThread.start();
    }

    public boolean auth(){
        serviceMsg("auth " + textFieldLogin.getText() + " " + textFieldPass.getText());
        try {
            String str = inputStreamService.readUTF();
            if (!str.equals("disconnect")){
                nick = str;
                textFieldNick.setText(nick);
                return true;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void disconnect(){
        isConnected = false;
        btnConnect.setText("Connect");
        serviceThread.interrupt();
        chatThread.interrupt();
        treeViewUsers.getRoot().getChildren().clear();
        textFieldMessage.setVisible(false);
        textFieldNick.setVisible(false);
        btnSend.setVisible(false);
        btnBlock.setVisible(false);
        treeViewUsers.setVisible(false);
        textAreaChat.setVisible(false);
    }

    public void sendMsg(){
        String name =treeViewUsers.getSelectionModel().getSelectedItem().getValue().getText();
        try{
            if (name.equals("All")) {
                outputStreamChat.writeUTF(textFieldMessage.getText());
            }else{
                serviceMsg(String.format(PRIVATE_MESSAGE,name,textFieldMessage.getText()));
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        textFieldMessage.clear();
        textFieldMessage.requestFocus();
    }

    public void serviceMsg(String message){
        try{
            outputStreamService.writeUTF(message);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void block(){
        Label labelToChange = treeViewUsers.getSelectionModel().getSelectedItem().getValue();
        String style = labelToChange.getStyle();
        if (style.equals(UN_BAN)){
            serviceMsg("addToBan " + labelToChange.getText());
        }else{
            serviceMsg("removeFromBan " + labelToChange.getText());
        }

    }

    public void setNick(){
        String newNick = textFieldNick.getText();
        serviceMsg("setNick " + newNick);
    }
}
