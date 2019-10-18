package ru.geekbrains.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.geekbrains.persist.Order;
import ru.geekbrains.persist.Product;
import ru.geekbrains.persist.ShopRepository;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

@SessionScoped
@Named
public class OrderBean implements Serializable {
    private static final Logger logger = LoggerFactory.getLogger(OrderBean.class);

    @Inject
    private ShopRepository shopRepository;
    private Order order;

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
//------------------------------------------------------------------------------------
    public List<Order> getAllOrders() throws SQLException {
        return shopRepository.findAllOrders();
    }

    public void delete(Order order) throws SQLException {
        shopRepository.delete(order);
    }

    public void changeOrderStatus(Order order) throws SQLException {
        shopRepository.changeStatus(order);
    }
}
