package ru.grvsa.persistence;

import javax.persistence.*;
import java.util.Map;
import java.util.Set;

//@Entity
//@Table(name = "orders")
public class Order {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private Set<OrderItem> orderItemSet;

    public Order() {
    }

    public Order(long id, Set<OrderItem> orderItemSet) {
        this.id = id;
        this.orderItemSet = orderItemSet;
    }
}
