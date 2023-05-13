package mk.finki.ukim.web.lab.service.impl;

import mk.finki.ukim.web.lab.model.User;
import mk.finki.ukim.web.lab.model.exceptions.InvalidArgumentsException;
import mk.finki.ukim.web.lab.model.exceptions.InvalidUserCredentialsException;
import mk.finki.ukim.web.lab.model.exceptions.PasswordsDoNotMatchException;
import mk.finki.ukim.web.lab.model.exceptions.UsernameAlreadyExistsException;
import mk.finki.ukim.web.lab.repository.jpa.UserRepositoryJPA;
import mk.finki.ukim.web.lab.service.AuthService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepositoryJPA userRepository;

    public AuthServiceImpl(UserRepositoryJPA userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public User login(String username, String password) {
        if(username==null || username.isEmpty() || password==null || password.isEmpty()){
            throw new InvalidArgumentsException();
        }
        return userRepository.findByUsernameAndPassword(username, password).orElseThrow(InvalidUserCredentialsException::new);
    }

    @Override
    public User register(String username, String password, String repeatPassword, String name, String surname, LocalDate date) {
        return null;
    }

//    @Override
//    public User register(String username, String password, String repeatPassword, String name, String surname, LocalDate date) {
//        if(username==null || username.isEmpty() || password==null || password.isEmpty()){
//            throw new InvalidArgumentsException();
//        }
//        if(!password.equals(repeatPassword)){
//            throw new PasswordsDoNotMatchException();
//        }
//
//        if(this.userRepository.findByUsername(username).isPresent() || !this.userRepository.findByUsername(username).isEmpty()){
//            throw new UsernameAlreadyExistsException(username);
//        }
//        User user = new User(username, password, name, surname, date);
//        return userRepository.save(user); //bilo saveOrUpdate(user);
//        // return user; //moze na dvata nachini
//    }
}
