package server;

import java.util.TimerTask;

public class DisconnectTask extends TimerTask {
    private ClientHandler client;

    public DisconnectTask(ClientHandler client) {
        super();
        this.client = client;
    }

    @Override
    public void run() {
            client.setConnected(false);
            client.serviceMsg("disconnect");
            client.getServer().unSubscribe(client);
            client.getServiceThread().interrupt();
            client.getChatThread().interrupt();
    }
}
