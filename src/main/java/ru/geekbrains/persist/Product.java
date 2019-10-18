package ru.geekbrains.persist;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Product {
    private Long id;
    @NotNull(message = "Can not be empty")
    @Size(min = 5, max = 30, message = "The field length should be from 5 to 30")
    private String name;
    @NotNull(message = "Can not be empty")
    private Long category;
    @NotNull(message = "Can not be empty")
    @Size(min = 5, max = 30, message = "The field length should be from 5 to 30")
    private String description;
    @NotNull(message = "Can not be empty")
    private Double price;

    public Product() {
    }

    public Product(Long id, String name, Long category, String description, double price) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.price = price;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getCategory() {
        return category;
    }

    public void setCategory(Long category) {
        this.category = category;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", category=" + category +
                ", description='" + description + '\'' +
                ", price=" + price +
                '}';
    }
}
