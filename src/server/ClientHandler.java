package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Timer;

public class ClientHandler {
    private static final long DISCONNECT_DELAY = 180000; // 3 min not active
    private static final String USER_ENTER = "%s Enter chat room";
    private static final String USER_LEAVE = "%s Left chat room";
    private static final String USER_CHANGE_NICK = "%s change nick to %s";
    private static final String BROADCAST_MESSAGE = "[%s] %s";

    private Server server;
    private Socket chatSocket;
    private Socket serviceSocket;
    private DataInputStream inputStreamChat;
    private DataOutputStream outputStreamChat;
    private DataInputStream inputStreamService;
    private DataOutputStream outputStreamService;
    private Timer timer;

    private String nick;
    private String login;

    public Server getServer() {
        return server;
    }

    ClientHandler client;
    boolean isConnected = false;
    Thread chatThread;
    Thread serviceThread;

    public void setConnected(boolean connected) {
        isConnected = connected;
    }

    public Thread getChatThread() {
        return chatThread;
    }

    public Thread getServiceThread() {
        return serviceThread;
    }

    public ClientHandler(Server server, Socket chatSocket, Socket serviceSocket) {
        client = this;
        this.server = server;
        this.chatSocket = chatSocket;
        this.serviceSocket = serviceSocket;

        try {
            inputStreamChat = new DataInputStream(chatSocket.getInputStream());
            outputStreamChat = new DataOutputStream(chatSocket.getOutputStream());

            inputStreamService = new DataInputStream(serviceSocket.getInputStream());
            outputStreamService = new DataOutputStream(serviceSocket.getOutputStream());

            auth();

            if (isConnected) {
                timer = new Timer();
                timer.schedule(new DisconnectTask(client),DISCONNECT_DELAY);
                serviceMsg(nick);
                server.subscribe(this);
                server.broadcast(String.format(USER_ENTER,nick),client);

                chatThread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            while (isConnected && !Thread.currentThread().isInterrupted()) {
                                String str = inputStreamChat.readUTF();
                                timer.cancel();
                                timer = new Timer();
                                timer.schedule(new DisconnectTask(client),DISCONNECT_DELAY);
                                server.broadcast(String.format(BROADCAST_MESSAGE,nick,str),client);
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        } finally {
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
                                server.getExecutorService().execute(new Runnable() {
                                    @Override
                                    public void run() {
                                        if (str.startsWith("disconnect")) {
                                            isConnected = false;
                                            server.broadcast(String.format(USER_LEAVE,nick),client);
                                            server.unSubscribe(client);
                                        }else if (str.startsWith("setNick")){
                                            String[] newNick = str.split(" ");
                                            AuthSQL.connect();
                                            if (AuthSQL.checkAvailableNick(newNick[1])){
                                                AuthSQL.disconnect();
                                                AuthSQL.connect();
                                                AuthSQL.setNick(login,newNick[1]);
                                                server.broadcast(String.format(USER_CHANGE_NICK,nick,newNick[1]),client);
                                                nick = newNick[1];
                                                serviceMsg("setNick " + nick);
                                                server.unSubscribe(client);
                                                server.subscribe(client);
                                                AuthSQL.disconnect();
                                            }
                                        }else if (str.startsWith("private")){
                                            server.privateMessage(nick + " " + str);
                                        }else if (str.startsWith("addToBan")){
                                            String[] token = str.split(" ");
                                            server.addToBlackList(client,token[1]);
                                        }else if (str.startsWith("removeFromBan")){
                                            String[] token = str.split(" ");
                                            server.deleteFromBlackList(client,token[1]);
                                        }
                                    }
                                });
                             }
                        } catch (IOException e) {
                            e.printStackTrace();
                        } finally {
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
            }else {
                serviceMsg("disconnect");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendMsg(String message){
        try {
            outputStreamChat.writeUTF(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void serviceMsg(String message){
        try {
            outputStreamService.writeUTF(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private int passwordHash(String pass){
        //ToDo
        return pass.hashCode();
    }

    public void auth(){
        try {
            String[] loginPass = inputStreamService.readUTF().split(" ");
            if (loginPass[0].equals("auth")) {
                login = loginPass[1];
                AuthSQL.connect();
                nick = AuthSQL.getNickByLoginPass(login,passwordHash(loginPass[2]));
                if (nick != null) {
                    isConnected = server.loginAvailable(login);
                }else{
                    isConnected = false;
                }
                AuthSQL.disconnect();
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public String getNick() {
        return nick;
    }

    public String getLogin() {
        return login;
    }

}

