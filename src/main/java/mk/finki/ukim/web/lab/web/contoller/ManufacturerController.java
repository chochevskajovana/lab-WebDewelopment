package mk.finki.ukim.web.lab.web.contoller;

import mk.finki.ukim.web.lab.model.Balloon;
import mk.finki.ukim.web.lab.model.Manufacturer;
import mk.finki.ukim.web.lab.service.ManufacturerService;
import mk.finki.ukim.web.lab.service.impl.ManufactureServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/manufacturer")
public class ManufacturerController {

    private final ManufactureServiceImpl manufacturerService;

    public ManufacturerController(ManufactureServiceImpl manufacturerService) {
        this.manufacturerService = manufacturerService;
    }

    @GetMapping
    public String getBalloonsPage(@RequestParam(required = false) String error, Model model){
        if(error != null && !error.isEmpty()){
            model.addAttribute("hasError", true);
            model.addAttribute("error",error);
        }
        List<Manufacturer> manufacturers = this.manufacturerService.findAll();
        model.addAttribute("manufacturers", manufacturers);
        return "listManufacturers";
//        model.addAttribute("bodyContent", "add-product");
//        return "master-template";
    }

    @GetMapping("/add-form")
    public String getAddManufacturerPage(Model model){
        return "add-manufacturer";
    }

    @PostMapping("/addManufacturer")
    public String addManufacturer(//@RequestParam(required = false) Long id,
                              @RequestParam String name,
                              @RequestParam String country,
                              @RequestParam String address){
        this.manufacturerService.save(name, country, address); //na pochetok imashe Long id
        return "redirect:/balloons";
    }
}
