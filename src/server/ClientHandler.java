package server;

import client.Main;
import javafx.event.EventHandler;
import javafx.stage.WindowEvent;

import javax.swing.plaf.TableHeaderUI;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientHandler {
    private static int count = 0;

    private Socket socket;
    private Server server;
    private DataOutputStream out;
    private DataInputStream in;
    private boolean isConnected;
    private ClientHandler client;
    private String nick;

    public String getNick() {
        return nick;
    }

    public ClientHandler(Socket socket, Server server) {
        this.client = this;
        this.socket = socket;
        this.server = server;
        isConnected = true;
        this.nick = "Nick" + ++count;

        try {
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        server.subscribe(client);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    while (isConnected){
                        String message = in.readUTF();
                        if (message.startsWith("disconnect")){
                            sendMessage("disconnect");
                            isConnected = false;
                            server.unSubscribe(client);
                        }else {
                            server.broadcast(client, message);
                        }
                    }
                }catch (IOException e) {
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

    public void sendMessage(String message){
        try {
            out.writeUTF(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
