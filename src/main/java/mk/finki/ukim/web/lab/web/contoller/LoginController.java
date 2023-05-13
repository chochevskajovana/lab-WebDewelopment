package mk.finki.ukim.web.lab.web.contoller;

import mk.finki.ukim.web.lab.model.User;
import mk.finki.ukim.web.lab.model.exceptions.InvalidUserCredentialsException;
import mk.finki.ukim.web.lab.service.AuthService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/login")
public class LoginController {
    private final AuthService authService;

    public LoginController(AuthService authService) {
        this.authService = authService;
    }

    //@RequestMapping(method = RequestMethod.GET, value="") //moze no ne se preporachuva
    @GetMapping
    public String getLoginPage(){
        return "login";
    }

    @PostMapping
    public String login(HttpServletRequest request, Model model){
        User user = null;
        try{
            user = this.authService.login(request.getParameter("username"), request.getParameter("password"));
            request.getSession().setAttribute("user", user);
            return "redirect:/balloons";
        }
        catch (InvalidUserCredentialsException exception){
            model.addAttribute("hasError", true);
            model.addAttribute("error", exception.getMessage());
            return "login";
        }
    }
}
