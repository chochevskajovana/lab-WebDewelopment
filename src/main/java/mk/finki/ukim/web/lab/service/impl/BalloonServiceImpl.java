package mk.finki.ukim.web.lab.service.impl;

import mk.finki.ukim.web.lab.model.Balloon;
import mk.finki.ukim.web.lab.model.Manufacturer;
import mk.finki.ukim.web.lab.model.exceptions.BalloonNotFoundException;
import mk.finki.ukim.web.lab.model.exceptions.ManufacturerNotFoundException;
import mk.finki.ukim.web.lab.repository.impl.BalloonRepository;
import mk.finki.ukim.web.lab.repository.jpa.BalloonRepositoryJPA;
import mk.finki.ukim.web.lab.repository.jpa.ManufacturerRepositoryJPA;
import mk.finki.ukim.web.lab.service.BalloonService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class BalloonServiceImpl implements BalloonService {
    private final BalloonRepository balloonRepository1;
//    private final ManufacturerRepository manufacturerRepository;

    private final BalloonRepositoryJPA balloonRepository;
    private final ManufacturerRepositoryJPA manufacturerRepository;

    public BalloonServiceImpl(BalloonRepository balloonRepository1, BalloonRepositoryJPA balloonRepository, ManufacturerRepositoryJPA manufacturerRepository) {
        this.balloonRepository1 = balloonRepository1;
        this.balloonRepository = balloonRepository;
        this.manufacturerRepository = manufacturerRepository;
    }

    @Override
    public List<Balloon> listAll() {
        return balloonRepository.findAll();
    }

    @Override
    public List<Balloon> searchByNameOrDescription(String text) {
        return balloonRepository.findAllByName(text); // !!! treba da bide NameAndDescription
    }

    @Override
    public Optional<Balloon> searchById(Long id) {
        return balloonRepository.findById(id);
    }

    @Override
    @Transactional
    public Optional<Balloon> save(String name, String description, Long manufacturerId) {
        Manufacturer manufacturer1 = this.manufacturerRepository.findById(manufacturerId)
                .orElseThrow(() -> new ManufacturerNotFoundException(manufacturerId));

        this.balloonRepository.deleteByName(name);
        return Optional.of(this.balloonRepository.save(new Balloon(name, description, manufacturer1)));
    }

    @Override
    @Transactional
    public Optional<Balloon> edit(Long id, String name, String description, Long manufacturer) {
        Balloon balloon = this.balloonRepository.findById(id).orElseThrow(() -> new BalloonNotFoundException(id));

        balloon.setName(name);
        balloon.setDescription(description);
        Manufacturer manufacturer1 = this.manufacturerRepository.findById(manufacturer)
                .orElseThrow(() -> new ManufacturerNotFoundException(manufacturer));

        balloon.setManufacturer(manufacturer1);
        return Optional.of(this.balloonRepository.save(balloon));
    }

    @Transactional
    @Override
    public void deleteBalloonsByDescriptionContaining(String text) {
        balloonRepository.deleteAllByDescriptionContaining(text);
    }

    @Override
    public void removeByDescription(String text) {
        this.balloonRepository1.removeByDescription(text);
    }

    @Override
    public void deleteById(Long id) {
        this.balloonRepository.deleteById(id);
    }

}
