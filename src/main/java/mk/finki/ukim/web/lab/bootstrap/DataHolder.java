package mk.finki.ukim.web.lab.bootstrap;

import mk.finki.ukim.web.lab.model.Balloon;
import mk.finki.ukim.web.lab.model.Manufacturer;
import mk.finki.ukim.web.lab.model.Order;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
public class DataHolder {
    public static List<Balloon> balloons = new ArrayList<>();
    public static List<Order> orders = new ArrayList<>();
    public static List<Manufacturer> manufacturers = new ArrayList<>();

    @PostConstruct
    public void init(){
        manufacturers.add(new Manufacturer("Apple", "Macedonia", "Goce Delcev"));
        manufacturers.add(new Manufacturer("Samsung", "Macedonia", "Goce Delcev"));
        manufacturers.add(new Manufacturer("Huawei", "Macedonia", "Goce Delcev"));
        manufacturers.add(new Manufacturer("Xiaomi", "Macedonia", "Goce Delcev"));
        manufacturers.add(new Manufacturer("Sony", "Macedonia", "Goce Delcev"));


        balloons.add(new Balloon("Transparent Balloon", "one", new Manufacturer("ABC", "Macedonia", "Goce Delcev")));
        balloons.add(new Balloon("Black Balloon", "two", new Manufacturer("ABC", "Macedonia", "Goce Delcev")));
        balloons.add(new Balloon("White Balloon", "tree", new Manufacturer("ABC", "Macedonia", "Goce Delcev")));
        balloons.add(new Balloon("Blue Balloon", "four", new Manufacturer("ABC", "Macedonia", "Goce Delcev")));
        balloons.add(new Balloon("Cyan Balloon", "five", new Manufacturer("ABC", "Macedonia", "Goce Delcev")));
        balloons.add(new Balloon("Green Balloon", "six", new Manufacturer("ABC", "Macedonia", "Goce Delcev")));
        balloons.add(new Balloon("Lime Balloon", "seven", new Manufacturer("ABC", "Macedonia", "Goce Delcev")));
        balloons.add(new Balloon("Yellow Balloon", "eight", new Manufacturer("ABC", "Macedonia", "Goce Delcev")));
        balloons.add(new Balloon("Red Balloon", "nine", new Manufacturer("ABC", "Macedonia", "Goce Delcev")));
        balloons.add(new Balloon("Magenta Balloon", "ten", new Manufacturer("ABC", "Macedonia", "Goce Delcev")));
    }


}
