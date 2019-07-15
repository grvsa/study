package client;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import message.Message;
import message.Type;

import java.util.regex.Pattern;

public class RegisterWindow {
    @FXML
    TextField login;
    @FXML
    TextField password;
    @FXML
    TextField nick;
    @FXML
    Button register;
    @FXML
    Button loginCheck;
    @FXML
    Button nickCheck;
    @FXML
    Button connect;
    @FXML
    Label rule1;
    @FXML
    Label rule2;
    @FXML
    Label rule3;
    @FXML
    Label rule4;
    @FXML
    Label rule5;
    @FXML
    Label rule6;
    @FXML
    Label rule7;
    @FXML
    Label reason;

    private Controller parent;
    private boolean isPasswordValid;
    private boolean isLoginValid;
    private boolean isNickValid;
    private boolean isRegistered;
    private RegisterWindow registerWindow;
    private Stage stage;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    private final String PASS_REGEX = "^(?=.*[0-9])(?=.*[A-Z])(?=.*[a-z])(?=.*[!@#\\$%\\^\\.&\\*_])[0-9a-zA-Z!@#\\$%\\^\\.&\\*_]{8,}$";

    public void setReason(Message message){
        reason.setText(message.toString());
    }

    public void setParent(Controller parent) {
        this.parent = parent;
    }

    public void initialize() {
        registerWindow = this;
        isPasswordValid = false;
        isLoginValid = false;
        isNickValid = false;
        isRegistered = false;

        password.addEventFilter(KeyEvent.ANY, new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                String a = password.getText();
                passwordIsValid(a);
            }
        });
    }

    public void setLoginValid(Message message){
        if (message.getType() == Type.CHECKLOGIN){
            if (message.getMessage().equals("Invalid")){
                isLoginValid = false;
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        loginCheck.setText("Check");
                    }
                });

            }else{
                isLoginValid = true;
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        loginCheck.setText("VALID");
                    }
                });
            }
        }
    }

    public void setRegisterStatus(Message message){
        if (message.getType() == Type.REGISTEROK){
            isRegistered = true;
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    reason.setText(message.getMessage());
                }
            });
            register.setManaged(false);
        }else{
            isRegistered = false;
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    reason.setText(message.getMessage());
                }
            });

        }
    }

    public void setNickValid(Message message){
        if (message.getType() == Type.CHECKNICK){
            if (message.getMessage().equals("Invalid")){
                isNickValid = false;
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        nickCheck.setText("Check");
                    }
                });

            }else{
                isNickValid = true;
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        nickCheck.setText("VALID");
                    }
                });

            }
        }
    }

    public void checkLogin(){
        parent.send(new Message(Type.CHECKLOGIN,login.getText(),"Invalid"));
    }

    public void checkNick(){
        parent.send(new Message(Type.CHECKNICK,nick.getText(),"Invalid"));
    }

    public void connect(){
        if (isPasswordValid && isLoginValid && isNickValid && isRegistered){
            parent.send(new Message(Type.AUTHREQUEST,login.getText(),password.getText()));
            stage.close();
        }
    }

    public void register(){
        if (isPasswordValid && isLoginValid && isNickValid && !isRegistered) {
            parent.send(new Message(Type.REGISTER, login.getText(), password.getText(), nick.getText()));
        }
    }

    public void passwordIsValid(String pass) {
        Pattern p = Pattern.compile(PASS_REGEX);
        if (p.matcher(pass).find()) {
            rule1.setTextFill(Color.DARKGREEN);
            isPasswordValid = true;
        }else{
            rule1.setTextFill(Color.RED);
            isPasswordValid = false;
        }

        if (pass.length() < 8) {
            rule2.setTextFill(Color.RED);
        } else {
            rule2.setTextFill(Color.DARKGREEN);
        }
        p = Pattern.compile("[0-9]");
        if (!p.matcher(pass).find()) {
            rule3.setTextFill(Color.RED);
        } else {
            rule3.setTextFill(Color.DARKGREEN);
        }
        p = Pattern.compile("[a-z]");
        if (!p.matcher(pass).find()) {
            rule4.setTextFill(Color.RED);
        } else {
            rule4.setTextFill(Color.DARKGREEN);
        }
        p = Pattern.compile("[A-Z]");
        if (!p.matcher(pass).find()) {
            rule5.setTextFill(Color.RED);
        } else {
            rule5.setTextFill(Color.DARKGREEN);
        }
        p = Pattern.compile("[!@#\\$%\\^\\.&\\*_]");
        if (!p.matcher(pass).find()) {
            rule6.setTextFill(Color.RED);
        } else {
            rule6.setTextFill(Color.DARKGREEN);
        }

        p = Pattern.compile("[^0-9a-zA-Z!@#\\$%\\^\\.&\\*_]");
        if (p.matcher(pass).find()) {
            rule7.setTextFill(Color.RED);
        } else {
            rule7.setTextFill(Color.DARKGREEN);
        }
    }
}
