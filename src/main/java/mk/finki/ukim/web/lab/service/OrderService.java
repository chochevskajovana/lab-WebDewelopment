package mk.finki.ukim.web.lab.service;

import mk.finki.ukim.web.lab.model.Order;
import mk.finki.ukim.web.lab.model.User;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface OrderService {
    Optional<Order> placeOrder(String balloonColor, String size, LocalDateTime dateTime, User user);

    List<Order> findOrders();

    List<Order> findAllByDateCreatedBetween(LocalDateTime from, LocalDateTime to);

}
