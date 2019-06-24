package client;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.WindowEvent;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
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

    public void initialize(){
        textFieldNick.setText("Guest");
        textFieldLogin.setText("Enter Login ...");
        textFieldPass.setText("Enter pass ...");
        TreeItem<Label> root = new TreeItem<>(new Label("All"));
        root.setExpanded(true);
        root.getChildren().add(new TreeItem<>(new Label("Nick1")));
        root.getChildren().add(new TreeItem<>(new Label("Nick2")));
        root.getChildren().add(new TreeItem<>(new Label("Nick3")));
        treeViewUsers.setRoot(root);
        isConnected = true;

        try {
            socket = new Socket(URL,PORT);
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());


        } catch (IOException e) {
            e.printStackTrace();
        }



        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    while (isConnected){
                        String message = in.readUTF();
                        if (message.startsWith("disconnect")){
                            isConnected = false;
                            textAreaChat.appendText("Disconnected from server\n");
                        }else {
                            textAreaChat.appendText(message + "\n");
                        }
                    }
                }catch (IOException e){
                    e.printStackTrace();
                }finally {
                    try {
                        in.close();
                        out.close();
                        socket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }
        }).start();
    }

    public void sendMsg(){
        try {
            out.writeUTF(textFieldMessage.getText());
        } catch (IOException e) {
            e.printStackTrace();
        }
        textFieldMessage.clear();
        textFieldMessage.requestFocus();

    }

    public void setNick(){
        nick = textFieldNick.getText();
        textFieldNick.setText(nick);
        textFieldMessage.requestFocus();
    }

    public void block(){

    }

    public void connect(){
        passwordIsValid();
    }

    public void passwordIsValid(){
        Pattern p = Pattern.compile(PASS_REGEX);
        String pass = textFieldPass.getText();
        StringBuilder sb = new StringBuilder();
        if (p.matcher(pass).find()){
            sb.append("Password is valid\n");
        }else{
            if (pass.length() < 8){
                sb.append("Password length is less than 8\n");
            }
            p = Pattern.compile("[0-9]");
            if (!p.matcher(pass).find()){
                sb.append("Number character absent\n");
            }
            p = Pattern.compile("[a-z]");
            if (!p.matcher(pass).find()){
                sb.append("Lower case characters absent\n");
            }
            p = Pattern.compile("[A-Z]");
            if (!p.matcher(pass).find()){
                sb.append("Capital case characters absent\n");
            }
            p = Pattern.compile("[!@#\\$%\\^\\.&\\*_]");
            if (!p.matcher(pass).find()){
                sb.append("Special character absent\n");
            }

            p = Pattern.compile("[^0-9a-zA-Z!@#\\$%\\^\\.&\\*_]");
            if (p.matcher(pass).find()){
                sb.append("Not correct characters used\n");
            }
        }
        textAreaChat.appendText(sb.toString() + "\n");
    }
}
