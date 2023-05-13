package mk.finki.ukim.web.lab.service;

import mk.finki.ukim.web.lab.model.Manufacturer;

import java.util.List;

public interface ManufacturerService {

    List<Manufacturer> findAll();

    public <Optional>Manufacturer save(String name, String country, String address); //na pochetok imashe i Long id
}
