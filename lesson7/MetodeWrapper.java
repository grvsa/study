package ru.geekbrains.java3.lesson7;

import java.lang.reflect.Method;

public class MetodeWrapper implements Comparable<MetodeWrapper>{
    private Method content;
    private int priority;

    public MetodeWrapper(Method content, int priority) {
        this.content = content;
        this.priority = priority;
    }

    public Method getContent() {
        return content;
    }

    public int getPriority() {
        return priority;
    }

    @Override
    public int compareTo(MetodeWrapper o) {
        return this.priority - o.getPriority();
    }
}
