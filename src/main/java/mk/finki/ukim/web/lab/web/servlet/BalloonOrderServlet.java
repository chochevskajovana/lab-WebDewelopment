package mk.finki.ukim.web.lab.web.servlet;

import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

@WebServlet (name = "balloon-order", urlPatterns = "/BalloonOrder")
public class BalloonOrderServlet extends HttpServlet {

    private final SpringTemplateEngine springTemplateEngine;

    public BalloonOrderServlet(SpringTemplateEngine springTemplateEngine) {
        this.springTemplateEngine = springTemplateEngine;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String balloon = (String) req.getSession().getAttribute("balloon");
        if(balloon == null){
            resp.sendRedirect("/");
        }else{
            WebContext contex = new WebContext(req, resp, req.getServletContext());
            resp.setContentType("text/html;charset=utf-8");
            contex.setVariable("selectedBalloon", req.getSession().getAttribute("balloon"));
            contex.setVariable("balloonSize", req.getSession().getAttribute("size"));
            this.springTemplateEngine.process("deliveryInfo.html", contex, resp.getWriter());
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        String name = req.getParameter("clientName");
//        String address = req.getParameter("clientAddress");
//        if(name == null || address == null){
//            resp.sendRedirect("/BalloonOrder");
//        }else{
//            req.getSession().setAttribute("name", req.getParameter("clientName"));
//            req.getSession().setAttribute("address", req.getParameter("clientAddress"));
//            resp.sendRedirect("/ConfirmationInfo");
//        }
        req.getSession().setAttribute("dateCreated", LocalDateTime.parse(req.getParameter("dateCreated")));
        resp.sendRedirect("/ConfirmationInfo");
    }
}
