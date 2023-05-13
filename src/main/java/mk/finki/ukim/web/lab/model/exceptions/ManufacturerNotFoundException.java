package mk.finki.ukim.web.lab.model.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class ManufacturerNotFoundException extends RuntimeException{

    public ManufacturerNotFoundException(Long id) {
        super(String.format("The manufacturer with id %d not found", id));
    }
}
