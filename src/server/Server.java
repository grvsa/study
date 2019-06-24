package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

public class Server {
    private final int PORT = 8189;
    private Vector<ClientHandler> clients;
    private ServerSocket serverSocket;
    private boolean isConnected;
    private Server server;

    private final String MESSAGE = "[%s]: %s";

    public Server() {
        isConnected = true;
        server = this;
        clients = new Vector<>();
        try {
            serverSocket = new ServerSocket(PORT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Server+++");
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                while (isConnected) {
                    try {
                        Socket socket = serverSocket.accept();
                        System.out.println("Client+++");
                        new ClientHandler(socket, server);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        t.start();
        try {
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void subscribe(ClientHandler client){
        System.out.println(client.getNick() + " Subscribed");
        clients.add(client);
        broadcast(client,"Enter chat room");
    }

    public void unSubscribe(ClientHandler client){
        clients.remove(client);
        broadcast(client," Left chat room");
        System.out.println(client.getNick() + " UnSubscribed");
    }

    public void broadcast(ClientHandler client, String message){
        for (ClientHandler c :
                clients) {
            c.sendMessage(String.format(MESSAGE,client.getNick(),message));
        }
    }
}
