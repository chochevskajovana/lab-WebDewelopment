package mk.finki.ukim.web.lab.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "user_orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;
    private String balloonColor;
    private String balloonSize;
//    private String clientName;
//    private String clientAddress;
    private LocalDateTime dateCreated;

    @ManyToOne
    private User user;

    public Order(String balloonColor, String balloonSize, LocalDateTime dateCreated, User user) {
        this.balloonColor = balloonColor;
        this.balloonSize = balloonSize;
        this.dateCreated = dateCreated;
        this.user = user;
//        this.clientName = clientName;
//        this.clientAddress = clientAddress;
        //this.orderId = (long)(Math.random() *1000);
    }

    public Order() {}
}
