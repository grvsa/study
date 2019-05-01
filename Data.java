package ru.geekbrains.algjava.lesson8;

import java.util.Objects;

public class Data {
    private Object o;

    public Data(Object o) {
        this.o = o;
    }

    public Object getO() {
        return o;
    }

    @Override
    public boolean equals(Object o1) {
        if (this == o1) return true;
        if (o1 == null || getClass() != o1.getClass()) return false;
        Data data = (Data) o1;
        return Objects.equals(o, data.o);
    }

    @Override
    public String toString() {
        return "Data{" +
                "o=" + o +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(o);
    }
}
