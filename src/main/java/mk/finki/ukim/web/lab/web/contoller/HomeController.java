package mk.finki.ukim.web.lab.web.contoller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value= {"/"})
public class HomeController {

    @GetMapping("access_denied")
    public String accessDeniedPage(Model model){
        //model.addAttribute("bodyContent", "access_denied");
        return "access_denied"; //"master-template";
    }
}
