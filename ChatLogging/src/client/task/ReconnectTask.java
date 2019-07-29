package client.task;

import client.Controller;

import java.util.TimerTask;

public class ReconnectTask extends TimerTask {
    private Controller controller;
    public ReconnectTask(Controller controller) {
        super();
        this.controller = controller;
    }

    @Override
    public void run() {
        System.out.println("Task executed");
        if (controller.isManualConnected() && !controller.isConnected()){
            controller.connect();
        }
    }
}


