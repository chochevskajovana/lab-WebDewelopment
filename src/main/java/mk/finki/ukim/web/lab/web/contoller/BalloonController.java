package mk.finki.ukim.web.lab.web.contoller;

import mk.finki.ukim.web.lab.model.Balloon;
import mk.finki.ukim.web.lab.model.Manufacturer;
import mk.finki.ukim.web.lab.service.BalloonService;
import mk.finki.ukim.web.lab.service.ManufacturerService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/balloons")
public class BalloonController {

    private final BalloonService balloonService;
    private final ManufacturerService manufacturerService;

    public BalloonController(BalloonService balloonService, ManufacturerService manufacturerService) {
        this.balloonService = balloonService;
        this.manufacturerService = manufacturerService;
    }


    @GetMapping
    public String getBalloonsPage(@RequestParam(required = false) String error, Model model){
        if(error != null && !error.isEmpty()){
            model.addAttribute("hasError", true);
            model.addAttribute("error",error);
        }
        List<Balloon> balloons = this.balloonService.listAll();
        model.addAttribute("balloons", balloons);
        return "listBalloons";
//        model.addAttribute("bodyContent", "listBalloons");
//        return "master-template";
    }

    @GetMapping("/add-form")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String getAddBalloonPage(Model model){
        List<Manufacturer> manufacturers = this.manufacturerService.findAll();
        model.addAttribute("manufacturers", manufacturers);
        return "add-balloon";
//        model.addAttribute("bodyContent", "add-balloon");
//        return "master-template";
    }

    @PostMapping("/add")
    public String saveBalloon(@RequestParam(required = false) Long id,
                              @RequestParam String name,
                              @RequestParam String description,
                              @RequestParam Long manufacturer){
        if(id != null){
            this.balloonService.edit(id, name, description, manufacturer);
        }else{
            this.balloonService.save(name, description, manufacturer);

        }
        return "redirect:/balloons";
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String deleteBalloon(@PathVariable Long id){
        this.balloonService.deleteById(id);
        return "redirect:/balloons";
    }

    @GetMapping("/edit-form/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String getEditBalloonPage(@PathVariable Long id, Model model){
        if(this.balloonService.searchById(id).isPresent()){
            Balloon balloon = this.balloonService.searchById(id).get();
            List<Manufacturer> manufacturers = this.manufacturerService.findAll();
            model.addAttribute("manufacturers", manufacturers);
            model.addAttribute("balloon", balloon);
            return "add-balloon";
//            model.addAttribute("bodyContent", "add-balloon");
//            return "master-template";

        }
        return "redirect:/balloons?error=BalloonNotFound";
    }

    @DeleteMapping("/search-balloon")
    public String getBalloonsDescription(@RequestParam String input){
        this.balloonService.deleteBalloonsByDescriptionContaining(input);
        return "redirect:/balloons";
    }
}
