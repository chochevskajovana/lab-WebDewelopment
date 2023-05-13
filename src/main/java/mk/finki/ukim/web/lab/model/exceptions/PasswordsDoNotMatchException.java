package mk.finki.ukim.web.lab.model.exceptions;

public class PasswordsDoNotMatchException extends RuntimeException{

    public PasswordsDoNotMatchException(){
        super("Passwords do not match");
    }
}
