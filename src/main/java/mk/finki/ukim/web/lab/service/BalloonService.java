package mk.finki.ukim.web.lab.service;

import mk.finki.ukim.web.lab.model.Balloon;
import java.util.List;
import java.util.Optional;

public interface BalloonService {
    List<Balloon> listAll();
    List<Balloon> searchByNameOrDescription(String text);
    Optional<Balloon> searchById(Long id);
    Optional<Balloon> save(String name, String description, Long manufacturer);

    Optional<Balloon> edit(Long id, String name, String description, Long manufacturer);

    void deleteBalloonsByDescriptionContaining(String text);

    void removeByDescription(String text);

    void deleteById(Long id);
}
