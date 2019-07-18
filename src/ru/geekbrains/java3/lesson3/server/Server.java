package ru.geekbrains.java3.lesson3.server;

import ru.geekbrains.java3.lesson3.message.Message;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private ServerSocket serverSocket;
    private Socket socket;

    private ObjectOutputStream out;
    private ObjectInputStream in;

    private static final int port = 8189;

    public Server() {
        try {
            serverSocket = new ServerSocket(port);
            socket = serverSocket.accept();


            out = new ObjectOutputStream(socket.getOutputStream());
            in = new ObjectInputStream(socket.getInputStream());

            out.writeObject(new Message("Connected to server", "Server"));
            while (true){
                try {
                    Message message = (Message) in.readObject();
                    if (message.getMessage().equals("end")){
                        out.writeObject(new Message("end","Server"));
                        break;
                    }else{
                        out.writeObject(new Message("[echo] " + message.getMessage(),"Server"));
                        System.out.println(message);
                    }
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
