package mk.finki.ukim.web.lab.service.impl;

import mk.finki.ukim.web.lab.model.Order;
import mk.finki.ukim.web.lab.model.User;
import mk.finki.ukim.web.lab.repository.jpa.OrderRepositoryJPA;
import mk.finki.ukim.web.lab.service.OrderService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

//    private final OrderRepository orderRepository;
    private final OrderRepositoryJPA orderRepository;


    public OrderServiceImpl(OrderRepositoryJPA orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Optional<Order> placeOrder(String balloonColor, String balloonSize, LocalDateTime dateTime, User user) {
        return Optional.of(orderRepository.save(new Order(balloonColor, balloonSize, dateTime, user)));
    }

    @Override
    public List<Order> findOrders() {
        return orderRepository.findAll();
    }

    @Override
    public List<Order> findAllByDateCreatedBetween(LocalDateTime from, LocalDateTime to) {
        return orderRepository.findAllByDateCreatedBetween(from, to);
    }

}
