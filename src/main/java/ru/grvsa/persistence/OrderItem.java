package ru.grvsa.persistence;

import javax.persistence.*;

//@Entity
//@Table(name = "orderitems")
public class OrderItem {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private Product product;
    private long qty;

    public OrderItem(long id, Product product, long qty) {
        this.id = id;
        this.product = product;
        this.qty = qty;
    }

    public OrderItem() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public long getQty() {
        return qty;
    }

    public void setQty(long qty) {
        this.qty = qty;
    }
}
