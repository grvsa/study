package ru.geekbrains.persist;

import java.util.HashMap;
import java.util.Map;

public class Order{
    private Long id;
    private Map<Long, Long> order;
    private Boolean active;

    public Order() {
        order = new HashMap<>();
    }

    public Order(Long id, Map<Long, Long> order, Boolean active) {
        this.id = id;
        this.order = order;
        this.active = active;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Map<Long, Long> getOrder() {
        return order;
    }

    public void setOrder(Map<Long, Long> order) {
        this.order = order;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}
