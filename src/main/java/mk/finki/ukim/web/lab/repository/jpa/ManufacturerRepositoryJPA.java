package mk.finki.ukim.web.lab.repository.jpa;

import mk.finki.ukim.web.lab.model.Manufacturer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ManufacturerRepositoryJPA extends JpaRepository<Manufacturer, Long> {
}
