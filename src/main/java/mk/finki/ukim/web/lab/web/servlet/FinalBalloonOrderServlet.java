package mk.finki.ukim.web.lab.web.servlet;

import mk.finki.ukim.web.lab.model.Order;
import mk.finki.ukim.web.lab.service.OrderService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;


@WebServlet(name = "final-balloon-order", urlPatterns = "/FinalOrders")
public class FinalBalloonOrderServlet extends HttpServlet {
    private final SpringTemplateEngine springTemplateEngine;
    private final OrderService orderService;

    public FinalBalloonOrderServlet(SpringTemplateEngine springTemplateEngine, OrderService orderService) {
        this.springTemplateEngine = springTemplateEngine;
        this.orderService = orderService;
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        WebContext contex = new WebContext(req, resp, req.getServletContext());
        contex.setVariable("orders", this.orderService.findOrders());
        resp.setContentType("text/html;charset=utf-8");
        this.springTemplateEngine.process("userOrders.html", contex, resp.getWriter());

    }

}
