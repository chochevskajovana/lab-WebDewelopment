package mk.finki.ukim.web.lab.repository.jpa;

import mk.finki.ukim.web.lab.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface OrderRepositoryJPA extends JpaRepository<Order, Long> {
    List<Order> findAllByDateCreatedBetween(LocalDateTime from, LocalDateTime to);
}
