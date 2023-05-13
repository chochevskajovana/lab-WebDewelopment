package mk.finki.ukim.web.lab.web.servlet;

import mk.finki.ukim.web.lab.service.BalloonService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet (name = "balloon-servlet", urlPatterns = "")
public class BalloonListServlet extends HttpServlet {
    private final SpringTemplateEngine springTemplateEngine;
    private final BalloonService balloonService;

    public BalloonListServlet(SpringTemplateEngine springTemplateEngine, BalloonService balloonService) {
        this.springTemplateEngine = springTemplateEngine;
        this.balloonService = balloonService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        WebContext contex = new WebContext(req, resp, req.getServletContext());
        contex.setVariable("balloons", this.balloonService.listAll());
        this.springTemplateEngine.process("listBalloons.html", contex, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getParameter("balloon");
        req.getSession().setAttribute("balloon", req.getParameter("balloon"));
        resp.sendRedirect("/selectBalloon");
    }
}
