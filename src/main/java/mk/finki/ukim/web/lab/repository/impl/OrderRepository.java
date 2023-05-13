package mk.finki.ukim.web.lab.repository.impl;

import mk.finki.ukim.web.lab.bootstrap.DataHolder;
import mk.finki.ukim.web.lab.model.Balloon;
import mk.finki.ukim.web.lab.model.Order;
import mk.finki.ukim.web.lab.model.User;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Repository
public class OrderRepository {

    public List<Order> findOrders(){
        return DataHolder.orders;
    }

    public Order saveOrder(String color, String size, LocalDateTime dateTime, User user) {
        Order order = new Order(color, size, dateTime, user);
        DataHolder.orders.add(order);
        return order;
    }

}
