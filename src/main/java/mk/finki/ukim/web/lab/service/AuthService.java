package mk.finki.ukim.web.lab.service;

import mk.finki.ukim.web.lab.model.User;

import java.time.LocalDate;

public interface AuthService {

    User login(String username, String password);
    User register(String username, String password, String repeatPassword, String name, String surname, LocalDate date);

}
