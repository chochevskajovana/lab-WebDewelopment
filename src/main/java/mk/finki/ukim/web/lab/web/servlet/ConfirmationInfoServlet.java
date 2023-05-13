package mk.finki.ukim.web.lab.web.servlet;

import mk.finki.ukim.web.lab.model.User;
import mk.finki.ukim.web.lab.service.OrderService;
import mk.finki.ukim.web.lab.service.UserService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;

@WebServlet (name = "confirmation-info", urlPatterns = "/ConfirmationInfo")
public class ConfirmationInfoServlet extends HttpServlet {
    private final SpringTemplateEngine springTemplateEngine;
    private final OrderService orderService;
    private final UserService userService;

    public ConfirmationInfoServlet(SpringTemplateEngine springTemplateEngine, OrderService orderService, UserService userService) {
        this.springTemplateEngine = springTemplateEngine;
        this.orderService = orderService;
        this.userService = userService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String balloon = (String) req.getSession().getAttribute("balloon");
        if(balloon == null){
            resp.sendRedirect("/balloons");
        }else{
            WebContext contex = new WebContext(req, resp, req.getServletContext());
            contex.setVariable("selectedBalloon", req.getSession().getAttribute("balloon"));
            contex.setVariable("balloonSize", req.getSession().getAttribute("size"));
            contex.setVariable("name", req.getSession().getAttribute("name"));
            contex.setVariable("address", req.getSession().getAttribute("address"));
            contex.setVariable("ipAddress", req.getRemoteAddr());
            contex.setVariable("browser", req.getHeader("User-Agent"));
            resp.setContentType("text/html;charset=utf-8");
            this.springTemplateEngine.process("confirmationInfo.html", contex, resp.getWriter());
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String color = (String) req.getSession().getAttribute("balloon");
        String size = (String) req.getSession().getAttribute("size");
        String name = (String) req.getSession().getAttribute("name");
        String address = (String) req.getSession().getAttribute("address");
        String username = req.getRemoteUser();
        User user = userService.findByUsername(username).orElse(null);
        LocalDateTime dateTime = (LocalDateTime) req.getSession().getAttribute("dateCreated");

        orderService.placeOrder(color, size, dateTime, user);
        //req.getSession().invalidate();
        resp.sendRedirect("/orders");
    }
}
