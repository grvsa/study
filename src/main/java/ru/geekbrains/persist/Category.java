package ru.geekbrains.persist;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Category {
    private Long id;
    @NotNull(message = "Can not be empty")
    @Size(min = 5, max = 30, message = "The field length should be from 5 to 30")
    private String name;

    public Category(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Category() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
