package mk.finki.ukim.seminarska.everythingyouneed.model.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class CategoryAlreadyExistsException extends  RuntimeException{
    public CategoryAlreadyExistsException(String category){super(String.format("Category with %s already exists.",category));}
}
