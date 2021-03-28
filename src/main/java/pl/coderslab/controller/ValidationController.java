package pl.coderslab.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.coderslab.entity.Book;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
public class ValidationController {
    private final Validator validator;
    public ValidationController(Validator validator) {

        this.validator = validator;
    }
    @GetMapping(path = "/validate", produces = "text/plain;charset=UTF-8")
    String validate(Book book) {
        Set<ConstraintViolation<Book>> errors = validator.validate(book);
        if (errors.isEmpty()) {
            return "Nie ma błędów";
        } else {
            return errors.stream()
                    .map(cv -> cv.getPropertyPath() + ":" + cv.getMessage())
                    .collect(Collectors.joining(","));
        }
    }
}