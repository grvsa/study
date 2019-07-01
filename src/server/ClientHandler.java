package server;

import message.Message;
import message.Type;

import java.io.*;
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
    private String login;

    private ObjectInputStream messageIn;
    private ObjectOutputStream messageOut;

    public String getNick() {
        return nick;
    }

    public String getLogin() {
        return login;
    }

    public ClientHandler(Socket socket, Server server) {
        this.client = this;
        this.socket = socket;
        this.server = server;

        this.nick = "";

        try {
            messageOut = new ObjectOutputStream(socket.getOutputStream());
            messageIn = new ObjectInputStream(socket.getInputStream());

            isConnected = true;
            startReaderThread();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void startReaderThread() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (isConnected) {
                        Message message = (Message) messageIn.readObject();
                        switch (message.getType()) {
                            case BANADD:
                                AuthSQL.addToBanList(message.getFrom(),message.getMessage());
                                break;
                            case BANREMOVE:
                                AuthSQL.removeFromBan(message.getFrom(),message.getMessage());
                                break;
                            case CHECKNICK:
                                if (AuthSQL.checkNickValid(message.getFrom())){
                                    sendMessage(new Message(Type.CHECKNICK,message.getFrom(),"Valid"));
                                }else{
                                    sendMessage(new Message(Type.CHECKNICK,message.getFrom(),"Invalid"));
                                }
                                break;
                            case CHECKLOGIN:
                                if (AuthSQL.checkLoginValid(message.getFrom())){
                                    sendMessage(new Message(Type.CHECKLOGIN,message.getFrom(),"Valid"));
                                }else{
                                    sendMessage(new Message(Type.CHECKLOGIN,message.getFrom(),"Invalid"));
                                }
                                break;
                            case REGISTER:
                                if (AuthSQL.register(message.getFrom(),message.getMessage(),message.getParameters())){
                                    sendMessage(new Message(Type.REGISTEROK,message.getFrom(),"New user " + message.getFrom() + " registered."));
                                }else{
                                    sendMessage(new Message(Type.REGISTERNG,message.getFrom(),"Registration problem. Try again."));
                                }
                                break;
                            case AUTHREQUEST:
                                login = message.getFrom();
                                nick = AuthSQL.getNickByLoginPass(login, message.getMessage());
                                if (nick != null && server.checkNick(nick)) {
                                    sendMessage(new Message(Type.AUTHOK, nick, ""));
                                    server.subscribe(client);
                                }else{
//                                    isConnected = false;
                                    sendMessage(new Message(Type.AUTHNG,"server", nick == null ? "Login or pass is not valid":nick + " Already used"));
                                }
                                break;
                            case DISCONNECT:
                                message.setFrom("server");
                                sendMessage(message);
                                isConnected = false;
                                server.unSubscribe(client);
                                break;
                            case MESSAGE:
                                server.broadcast(message);
                                break;
                            case PRIVATE:
                            case PRIVATECHAT:
                                server.privateMessage(message);
                                sendMessage(message);
                                break;
                            default:
                                System.out.println("Some kind of default ???");
                                break;
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        messageIn.close();
                        messageOut.close();
                        socket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    public void sendMessage(Message message) {
        try {
            messageOut.writeObject(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
