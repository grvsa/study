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

    public ClientHandler(Socket socket, Server server) {
        this.client = this;
        this.socket = socket;
        this.server = server;

        this.nick = "";

        try {
//            in = new DataInputStream(socket.getInputStream());
//            out = new DataOutputStream(socket.getOutputStream());
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
                            case AUTHREQUEST:
                                login = message.getFrom();
                                nick = AuthSQL.getNickByLoginPass(login, message.getMessage());
                                if (nick != null && server.checkNick(nick)) {
                                    sendMessage(new Message(Type.AUTHOK, nick, ""));
                                    server.subscribe(client);
                                }else{
                                    isConnected = false;
                                    sendMessage(new Message(Type.AUTHNG,"Server", nick == null ? "Login or pass is not valid":nick + " Already used"));
                                }
                                break;
                            case DISCONNECT:
                                sendMessage(message);
                                isConnected = false;
                                server.unSubscribe(client);
                                break;
                            case MESSAGE:
                                server.broadcast(message);
                                break;
                            case PRIVATE:
                                server.privateMessage(message);
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
