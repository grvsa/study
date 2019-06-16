package sample;

import javafx.fxml.FXML;
import javafx.scene.control.*;

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

    private final String PASS_REGEX = "^(?=.*[0-9])(?=.*[A-Z])(?=.*[a-z])(?=.*[!@#\\$%\\^\\.&\\*_])[0-9a-zA-Z!@#\\$%\\^\\.&\\*_]{8,}$";
    private String nick = "Guest";
    private final String MESSAGE = "[%s] %s%n";

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
    }

    public void sendMsg(){
        textAreaChat.appendText(String.format(MESSAGE,nick,textFieldMessage.getText()));
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
