package mk.finki.ukim.web.lab.repository.jpa;

import mk.finki.ukim.web.lab.model.Balloon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BalloonRepositoryJPA extends JpaRepository<Balloon, Long> {
    List<Balloon> findAllByName(String text);

    void deleteByName(String name);
    void deleteAllByDescriptionContaining(String text);
    //void removeAllByDescription(String text);

    //void deleteAllByDescription(String text);

    //void deleteBalloonsByDescriptionContaining(String text);
}
