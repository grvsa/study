package client;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import message.Message;
import message.Type;

import java.io.IOException;

public class Main extends Application {
    public static Stage stage;
    public static Controller controller;

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader();
        Parent root = loader.load(getClass().getResourceAsStream("sample.fxml"));
        stage = primaryStage;
        controller = loader.getController();

        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                if (controller.isConnected()) {
                    controller.send(new Message(Type.DISCONNECT, controller.getNick(), "Bye"));
                }
                Platform.exit();
                System.exit(0);
            }
        });
        primaryStage.setTitle("Disconnected");
        primaryStage.setScene(new Scene(root, 500, 275));
        primaryStage.show();

    }


    public static void main(String[] args) {
        launch(args);
    }
}
