package mk.finki.ukim.web.lab.service.impl;

import mk.finki.ukim.web.lab.model.User;
import mk.finki.ukim.web.lab.model.enumerations.Role;
import mk.finki.ukim.web.lab.model.exceptions.InvalidUsernameOrPasswordException;
import mk.finki.ukim.web.lab.model.exceptions.PasswordsDoNotMatchException;
import mk.finki.ukim.web.lab.model.exceptions.UsernameAlreadyExistsException;
import mk.finki.ukim.web.lab.repository.jpa.UserRepositoryJPA;
import mk.finki.ukim.web.lab.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepositoryJPA userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepositoryJPA userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User register(String username, String password, String repeatPassword, String name, String surname, LocalDate localDate, Role role) {
        if(username==null || username.isEmpty() || password==null || password.isEmpty()){
            throw new InvalidUsernameOrPasswordException();
        }
        if(!password.equals(repeatPassword)){
            throw new PasswordsDoNotMatchException();
        }

        if(this.userRepository.findByUsername(username).isPresent()){
            throw new UsernameAlreadyExistsException(username);
        }
        User user = new User(username, passwordEncoder.encode(password), name, surname, localDate, role);
        return userRepository.save(user);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(username));
    }
}
