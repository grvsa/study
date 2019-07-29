package server;

import message.Message;
import message.Type;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.Set;
import java.util.Vector;

public class Server {
    static {
        DOMConfigurator.configure("src/javaLog4jProperties/log4j2.xml");
    }

    static final Logger rootLogger = LogManager.getRootLogger();

    private final int HISTORYCOUNT = 5;
    private final int PORT = 8189;
    private Vector<ClientHandler> clients;
    private ServerSocket serverSocket;
    private boolean isConnected;
    private Server server;

    public Server() {
        isConnected = true;
        server = this;
        clients = new Vector<>();
        try {
            serverSocket = new ServerSocket(PORT);
        } catch (IOException e) {
            rootLogger.error(e.getMessage(),e);
        }
        System.out.println("Server+++");
        rootLogger.info("Server started !");
        AuthSQL.connect();
        Thread ping = new Thread(new Runnable() {
            @Override
            public void run() {
                rootLogger.info("Ping Thread executed!");
                while (isConnected){
                    try {
                        Thread.currentThread().sleep(1000L);
                        broadcast(new Message(Type.PING,"server","Connection check ..."));
                    } catch (InterruptedException e) {
                        rootLogger.error(e.getMessage(),e);
                    }
                }
            }
        });
        ping.setDaemon(true);
        ping.start();

        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                rootLogger.info("Client connection thread executed !");
                while (isConnected) {
                    try {
                        Socket socket = serverSocket.accept();
                        rootLogger.info("New Client connected");
                        new ClientHandler(socket, server);
                    } catch (IOException e) {
                        rootLogger.error(e.getMessage(),e);
                    }
                }
            }
        });
        t.start();
        try {
            t.join();
            AuthSQL.disconnect();
        } catch (InterruptedException e) {
            rootLogger.error(e.getMessage(),e);
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
        rootLogger.info(client.getNick() + " Subscribed for broadcast");
        clients.add(client);
        userList();
        banListSend();
        List<Message> history = AuthSQL.getHistoryForNick(client.getNick(), HISTORYCOUNT);
        while (history.size() > 0){
            client.sendMessage(history.remove(history.size() - 1));
        }
        broadcast(new Message(Type.JOIN,"server",client.getNick()));
    }

    public void unSubscribe(ClientHandler client){
        clients.remove(client);
        userList();
        banListSend();
        broadcast(new Message(Type.LEFT,"server",client.getNick()));
        rootLogger.info(client.getNick() + " UnSubscribed for broadcast");
    }

    public void broadcast(Message message){
        Set<String> doNotSendList = AuthSQL.getBanListForUser(AuthSQL.getLoginByNick(message.getFrom()));

        for (ClientHandler c :
                clients) {
            if (!doNotSendList.contains(c.getNick())) {
                c.sendMessage(message);
            }
        }
    }

    public void privateMessage(Message message) {
        Set<String> list = message.getDestination();
        Set<String> doNotSendList = AuthSQL.getBanListForUser(AuthSQL.getLoginByNick(message.getFrom()));
        for (ClientHandler user :
                clients) {
            if (list.contains(user.getNick()) || user.getNick().equals(message.getFrom())) {
                if (!doNotSendList.contains(user.getNick())) {
                    user.sendMessage(message);
                }
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

    private void banListSend(){
        for (ClientHandler user :
                clients) {
            Set<String> banlist = AuthSQL.getBanListByLogin(user.getLogin());
            Message message = new Message(Type.BANLIST,"server","Ban list update");
            message.getDestination().addAll(banlist);
            user.sendMessage(message);
        }
    }
}
