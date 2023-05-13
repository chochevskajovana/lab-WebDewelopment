package mk.finki.ukim.web.lab.repository.jpa;

import mk.finki.ukim.web.lab.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepositoryJPA extends JpaRepository<User, Long> {

    Optional<User> findByUsernameAndPassword(String username, String password);
    Optional<User> findByUsername(String username);
}
