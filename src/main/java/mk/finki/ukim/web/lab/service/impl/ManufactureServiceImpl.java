package mk.finki.ukim.web.lab.service.impl;

import mk.finki.ukim.web.lab.model.Manufacturer;
import mk.finki.ukim.web.lab.repository.jpa.ManufacturerRepositoryJPA;
import mk.finki.ukim.web.lab.service.ManufacturerService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ManufactureServiceImpl implements ManufacturerService {

//    private final ManufacturerRepository manufacturerRepository;
    private final ManufacturerRepositoryJPA manufacturerRepository;

    public ManufactureServiceImpl(ManufacturerRepositoryJPA manufacturerRepository) {
        this.manufacturerRepository = manufacturerRepository;
    }

    @Override
    public List<Manufacturer> findAll() {
        return this.manufacturerRepository.findAll();
    }

    @Override
    public <Optional> Manufacturer save(String name, String country, String address) { //na pocetok imashe i Long id
        return this.manufacturerRepository.save(new Manufacturer(name, country, address));
        //return null; //vraka null poradi testot - bez toa ne raboti
    }
}
