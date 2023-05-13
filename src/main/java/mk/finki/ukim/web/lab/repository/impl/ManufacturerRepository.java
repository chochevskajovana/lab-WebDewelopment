package mk.finki.ukim.web.lab.repository.impl;

import mk.finki.ukim.web.lab.bootstrap.DataHolder;
import mk.finki.ukim.web.lab.model.Manufacturer;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ManufacturerRepository {

    public List<Manufacturer> findAll() {
        return DataHolder.manufacturers;
    }

    public Optional<Manufacturer> findById(Long id){
        return DataHolder.manufacturers
                .stream()
                .filter(i->i.getId().equals(id))
                .findFirst();
    }

}
