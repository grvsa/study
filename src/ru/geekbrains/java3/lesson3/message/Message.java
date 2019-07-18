package ru.geekbrains.java3.lesson3.message;

import java.io.Serializable;

public class Message implements Serializable {
    private static final long serialVersionUID = 1L;

    private String message;
    private String from;

    public Message(String message, String from) {
        this.message = message;
        this.from = from;
    }

    public String getMessage() {
        return message;
    }

    public String getFrom() {
        return from;
    }

    @Override
    public String toString() {
        return "[" + from + "]: " + message;
    }
}
