package mk.finki.ukim.web.lab.model.exceptions;

public class BalloonNotFoundException extends RuntimeException{

    public BalloonNotFoundException(Long id){
        super(String.format("Balloon with id %s not found", id));
    }
}
