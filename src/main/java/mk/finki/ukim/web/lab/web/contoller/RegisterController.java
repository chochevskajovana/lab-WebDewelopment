package mk.finki.ukim.web.lab.web.contoller;

import mk.finki.ukim.web.lab.model.enumerations.Role;
import mk.finki.ukim.web.lab.model.exceptions.InvalidUserCredentialsException;
import mk.finki.ukim.web.lab.model.exceptions.PasswordsDoNotMatchException;
import mk.finki.ukim.web.lab.service.AuthService;
import mk.finki.ukim.web.lab.service.UserService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;

@Controller
@RequestMapping("/register")
public class RegisterController {

    public final AuthService authService;
    public final UserService userService;

    public RegisterController(AuthService authService, UserService userService) {
        this.authService = authService;
        this.userService = userService;
    }

    @GetMapping
    public String getRegisterPage(@RequestParam(required = false) String error, Model model){
        if(error != null && !error.isEmpty()){
            model.addAttribute("hasErrorr", true);
            model.addAttribute("error", error);
        }
//        model.addAttribute("bodyContent", "register");
        return "register";  //"master-template";
    }

    @PostMapping
    public String register(@RequestParam String username,
                           @RequestParam String password,
                           @RequestParam String repeatPassword,
                           @RequestParam String name,
                           @RequestParam String surname,
                           @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateOfBirth,
                           @RequestParam Role role){
        try{
            this.userService.register(username, password, repeatPassword, name, surname, dateOfBirth, role);
            return "redirect:/login";
        }catch (PasswordsDoNotMatchException | InvalidUserCredentialsException exception){
            return "redirect:/register?error=" + exception.getMessage();
        }
    }
}
