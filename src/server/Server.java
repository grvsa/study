package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {

    ServerSocket serverSocket;
    Socket chatSocket;
    Socket serviceSocket;
    Vector<ClientHandler> clients = new Vector<>();
    ExecutorService executorService;

    public ExecutorService getExecutorService() {
        return executorService;
    }

    {
        try {
            Thread console = new Thread(new Runnable() {
                @Override
                public void run() {
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
                    try {
                        while (true) {
                            String str = bufferedReader.readLine();
                            if (str.equals("end")){
                                break;
                            }else{
                                for (ClientHandler client :
                                        clients) {
                                    if (client.getNick().equals(str)) {
                                        client.serviceMsg("disconnect");
                                    }
                                }
                            }
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }finally {
                        try {
                            bufferedReader.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
            console.setDaemon(true);
            console.start();

            serverSocket = new ServerSocket(8189);
            System.out.println("ServerSocket created");
            executorService = Executors.newCachedThreadPool();
            while (true){
                chatSocket = serverSocket.accept();
                serviceSocket = serverSocket.accept();

                ClientHandler client = new ClientHandler(this,chatSocket,serviceSocket);
                System.out.println("Client +++");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public synchronized void subscribe(ClientHandler client){
        StringBuilder sb = new StringBuilder("loadUsers ");
        clients.add(client);
        for (ClientHandler user :
                clients) {
            sb.append(user.getNick() + " ");
        }

        for (ClientHandler user :
                clients) {
            user.serviceMsg(sb.toString());
        }
        AuthSQL.connect();
        for (ClientHandler user :
                clients) {
            String banList = AuthSQL.banList(user.getLogin());
            user.serviceMsg(banList);
        }
        AuthSQL.disconnect();
    }

    public synchronized void unSubscribe(ClientHandler client){
        StringBuilder sb = new StringBuilder("loadUsers ");
        clients.remove(client);
        for (ClientHandler user :
                clients) {
            sb.append(user.getNick() + " ");
        }

        for (ClientHandler user :
                clients) {
            user.serviceMsg(sb.toString());
        }

        AuthSQL.connect();
        for (ClientHandler user :
                clients) {
            String banList = AuthSQL.banList(user.getLogin());
            user.serviceMsg(banList);
        }
        AuthSQL.disconnect();
    }

    public synchronized void deleteFromBlackList(ClientHandler client, String nick){
        AuthSQL.connect();
        AuthSQL.blackListRemoveItem(client.getLogin(),nick);
        String banList = AuthSQL.banList(client.getLogin());
        client.serviceMsg(banList);
        AuthSQL.disconnect();
    }

    public synchronized void addToBlackList(ClientHandler client, String nick){
        AuthSQL.connect();
        AuthSQL.blackListAddItem(client.getLogin(),nick);
        String banList = AuthSQL.banList(client.getLogin());
        client.serviceMsg(banList);
        AuthSQL.disconnect();
    }

    public void privateMessage(String message){
        String[] array = message.split(" ",4);
        for (ClientHandler client :
                clients) {
            if (client.getNick().equals(array[2])) {
                client.sendMsg("[" + array[0] + "] to [" + client.getNick() + "] " + array[3]);
            }
        }
    }
    public synchronized void broadcast(String message, ClientHandler user){
        AuthSQL.connect();
        List<String> blackList = AuthSQL.blackListAsList(user.getLogin());
        for (ClientHandler client :
                clients) {
            if (!blackList.contains(client.getLogin())) {
                client.sendMsg(message);
            }
        }
        AuthSQL.disconnect();
    }

    public boolean loginAvailable(String login){
        for (ClientHandler client :
                clients) {
            if (client.getLogin().equals(login)) {
                return false;
            }
        }
        return true;
    }

}
