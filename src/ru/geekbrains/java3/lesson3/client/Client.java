package ru.geekbrains.java3.lesson3.client;

import ru.geekbrains.java3.lesson3.message.Message;

import java.io.*;
import java.net.Socket;

public class Client {
    private Socket socket;
    private ObjectOutputStream out;
    private ObjectInputStream in;

    private static final String URL = "localhost";
    private static final int PORT = 8189;


    public Client() {
        try {
            socket = new Socket(URL,PORT);

            out = new ObjectOutputStream(socket.getOutputStream());
            in  = new ObjectInputStream(socket.getInputStream());

            out.writeObject(new Message("Connection","User"));
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            Thread t1 = new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true){
                        try {
                            Message message = (Message) in.readObject();
                            if (message.getMessage().equals("end")){
                                System.out.println("Disconnected from server");
                                break;
                            }else{
                                System.out.println(message);
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
            t1.setDaemon(true);
            t1.start();

            while (true){
                String result = reader.readLine();
                if (result.equals("end")){
                    out.writeObject(new Message("end","User"));
                    break;
                }else{
                    out.writeObject(new Message(result,"User"));
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
