package ru.grvsa.springlesson3;

import javax.persistence.*;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @OneToOne
    @JoinColumn(name = "iduser")
    private User user;

    @OneToOne
    @JoinColumn(name = "idproduct")
    private Product product;

    @Column(name = "qty")
    private short qty;

    public int getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public short getQty() {
        return qty;
    }

    public void setQty(short qty) {
        this.qty = qty;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ",\n user=" + user +
                ",\n product=" + product +
                ",\n qty=" + qty +
                "}\n";
    }
}
