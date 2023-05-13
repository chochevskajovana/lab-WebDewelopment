package mk.finki.ukim.web.lab.service;

import mk.finki.ukim.web.lab.model.User;
import mk.finki.ukim.web.lab.model.enumerations.Role;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.time.LocalDate;
import java.util.Optional;

public interface UserService extends UserDetailsService {

    UserDetails loadUserByUsername(String var1) throws UsernameNotFoundException;
//    User login(String username, String password);
    User register(String username, String password, String repeatPassword, String name, String surname, LocalDate localDate, Role role);
    Optional<User> findByUsername(String username);
}
