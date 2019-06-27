package server;

import message.Message;
import message.Type;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Set;
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
        AuthSQL.connect();
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
            AuthSQL.disconnect();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public boolean checkNick(String nick){
        for (ClientHandler user :
                clients) {
            if (user.getNick().equals(nick)) return false;
        }

        return true;
    }
    public void subscribe(ClientHandler client){
        System.out.println(client.getNick() + " Subscribed");
        clients.add(client);
        userList();
        System.out.println(clients.size());
        for (ClientHandler user :
                clients) {
            System.out.println(user.getNick());
        }
        broadcast(new Message(Type.JOIN,client.getNick(),"Hello !!!"));
    }

    public void unSubscribe(ClientHandler client){
        clients.remove(client);
        userList();
        System.out.println(clients.size());
        for (ClientHandler user :
                clients) {
            System.out.println(user.getNick());
        }
        broadcast(new Message(Type.LEFT,client.getNick(),"Bye ..."));
        System.out.println(client.getNick() + " UnSubscribed");
    }

    public void broadcast(Message message){
        for (ClientHandler c :
                clients) {
            c.sendMessage(message);
        }
    }

    public void privateMessage(Message message) {
        Set<String> list = message.getDestination();
        for (ClientHandler user :
                clients) {
            if (list.contains(user.getNick())) {
                user.sendMessage(message);
            }
        }
    }

    private void userList(){
        Message message = new Message(Type.USERS,"Server","User List");
        Set<String> list = message.getDestination();
        for (ClientHandler user :
                clients) {
            list.add(user.getNick());
        }
        broadcast(message);
    }
}
