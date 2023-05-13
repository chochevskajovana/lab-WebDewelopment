package mk.finki.ukim.web.lab.repository.impl;

import mk.finki.ukim.web.lab.bootstrap.DataHolder;
import mk.finki.ukim.web.lab.model.Balloon;
import mk.finki.ukim.web.lab.model.Manufacturer;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class BalloonRepository {
    public List<Balloon> findAllBalloons(){
        return DataHolder.balloons;
    }
    public List<Balloon> findAllByNameOrDescription(String text){
        return DataHolder.balloons.stream().filter(r->r.getName().contains(text) || r.getDescription().contains(text)).collect(Collectors.toList());
    }
    public void removeByDescription(String text){
        DataHolder.balloons.removeIf(r -> r.getDescription().contains(text));
    }
    public Optional<Balloon> findById(Long id){
        return DataHolder.balloons.stream().filter(i->i.getId().equals(id)).findFirst();
    }

    public Optional<Balloon> save(Long id, String name, String description, Manufacturer manufacturer) {
        DataHolder.balloons.removeIf(i -> i.getId().equals(id));
        Balloon balloon = new Balloon(name, description, manufacturer);
        DataHolder.balloons.add(balloon);
        return Optional.of(balloon);
    }

    public void deleteById(Long id) {
        DataHolder.balloons.removeIf(i -> i.getId().equals(id));
    }
}
