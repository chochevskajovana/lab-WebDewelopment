package mk.finki.ukim.web.lab.web.servlet;

import org.springframework.ui.Model;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet (name = "select-balloon", urlPatterns = "/selectBalloon")
public class SelectBalloonServlet extends HttpServlet {
    private final SpringTemplateEngine springTemplateEngine;


    public SelectBalloonServlet(SpringTemplateEngine springTemplateEngine) {
        this.springTemplateEngine = springTemplateEngine;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String balloon = (String) req.getSession().getAttribute("balloon");
        if(balloon == null){
            resp.sendRedirect("/");
        }else{
            WebContext contex = new WebContext(req, resp, req.getServletContext());
            contex.setVariable("selectedBalloon", req.getSession().getAttribute("balloon"));
            resp.setContentType("text/html;charset=utf-8");
            this.springTemplateEngine.process("selectBalloonSize.html", contex, resp.getWriter());
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String balloonSize = req.getParameter("size");
        if(balloonSize == null){
            resp.sendRedirect("/selectBalloon");
        }else{
            req.getSession().setAttribute("size", req.getParameter("size"));
            resp.sendRedirect("/BalloonOrder");
        }
    }
}
