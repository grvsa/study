package ru.grvsa.springlesson3;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "productname")
    private String productname;

    @Column(name = "productprice")
    private short productprice;

    public int getId() {
        return id;
    }

    @ManyToMany(targetEntity = ru.grvsa.springlesson3.Order.class, mappedBy = "product")
    private List<Order> orders;

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

    public short getProductprice() {
        return productprice;
    }

    public void setProductprice(short productprice) {
        this.productprice = productprice;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", productname='" + productname + '\'' +
                ", productprice=" + productprice +
                '}';
    }
}
