package mk.finki.ukim.web.lab.web.contoller;

import mk.finki.ukim.web.lab.model.Balloon;
import mk.finki.ukim.web.lab.model.Order;
import mk.finki.ukim.web.lab.service.OrderService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public String getOrdersPage(Model model,
                                @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime from,
                                @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime to){
        List<Order> orders;
        if (from != null && to != null)
            orders = orderService.findAllByDateCreatedBetween(from, to);
        else
            orders = this.orderService.findOrders();
        model.addAttribute("orders", orders);
        return "userOrders";
    }
}
