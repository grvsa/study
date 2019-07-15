package message;

import java.io.Serializable;
import java.util.HashSet;

public class Message implements Serializable {
    private static final long serialVersionUID = 1L;

    private Type type;
    private String from;
    private String message;
    private String parameters;
    private HashSet<String> destination;

    private static final String NORMAL_MESSAGE = "[%s]: %s%n";
    private static final String PRIVATE_MESSAGE = "[private from %s]: %s%n";
    private static final String JOIN_MESSAGE = "%s joined to chat room%n";
    private static final String DISCONNECT_MESSAGE = "%s disconnected%n";
    private static final String LEFT_MESSAGE = "%s left chat room%n";
    private static final String AUTHNG_MESSAGE = "Authentification fail. %s%n";

    public Message(Type type, String from, String message) {
        this.destination = new HashSet<>();
        this.type = type;
        this.from = from;
        this.message = message;
    }

    public Message(Type type, String from, String message, String parameters) {
        this.destination = new HashSet<>();
        this.type = type;
        this.from = from;
        this.message = message;
        this.parameters = parameters;
    }

    public Type getType() {
        return type;
    }

    public String getFrom() {
        return from;
    }

    public String getMessage() {
        return message;
    }

    public String getParameters() {
        return parameters;
    }

    public HashSet<String> getDestination() {
        return destination;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    @Override
    public String toString() {
        String showMessage;
        switch (type) {
            case MESSAGE:
                showMessage = String.format(NORMAL_MESSAGE, from, message);
                break;
            case PRIVATE:
                showMessage = String.format(PRIVATE_MESSAGE, from, message);
                break;
            case DISCONNECT:
                showMessage = String.format(DISCONNECT_MESSAGE, from);
                break;
            case JOIN:
                showMessage = String.format(JOIN_MESSAGE, message);
                break;
            case LEFT:
                showMessage = String.format(LEFT_MESSAGE, message);
                break;
            case AUTHNG:
                showMessage = String.format(AUTHNG_MESSAGE, message);
                break;
            case PRIVATECHAT:
                showMessage = String.format(NORMAL_MESSAGE, from, message);
                break;
            default:
                showMessage = "Service message !!! Can not be printed.\n";
                break;
        }
        return showMessage;
    }
}
