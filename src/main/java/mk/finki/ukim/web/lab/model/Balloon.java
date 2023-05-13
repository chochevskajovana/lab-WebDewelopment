package mk.finki.ukim.web.lab.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Balloon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    @ManyToOne
    private Manufacturer manufacturer;

    public Balloon(String name, String description, Manufacturer manufacturer) {
        //this.id = (long)(Math.random() *1000);
        this.name = name;
        this.description = description;
        this.manufacturer = manufacturer;
    }

    public Balloon() {}
}
