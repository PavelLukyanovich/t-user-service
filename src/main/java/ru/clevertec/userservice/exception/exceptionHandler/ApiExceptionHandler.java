package ru.clevertec.userservice.exception.exceptionHandler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.clevertec.userservice.exception.appException.BadLoginParamException;
import ru.clevertec.userservice.exception.appException.ExpireDateException;
import ru.clevertec.userservice.exception.appException.ResourceNotFoundException;
import ru.clevertec.userservice.exception.appException.UserExistsException;
import ru.clevertec.userservice.exception.appException.UserNotFoundException;

@Slf4j
@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Object> handleUserNotFoundException(UserNotFoundException e) {
        var userNotFoundException = new UserNotFoundException(e.getMessage());
        return new ResponseEntity<>(userNotFoundException, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserExistsException.class)
    public ResponseEntity<Object> handleUserExistException(UserExistsException e) {
        var userExistsException = new UserExistsException(e.getMessage());
        return new ResponseEntity<>(userExistsException, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(BadLoginParamException.class)
    public ResponseEntity<Object> badLoginParamException(BadLoginParamException e) {
        var badLoginParamException = new BadLoginParamException(e.getMessage());
        return new ResponseEntity<>(badLoginParamException, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Object> resourceNotFoundException(ResourceNotFoundException e) {
        var resourceNotFoundException = new ResourceNotFoundException(e.getMessage());
        return new ResponseEntity<>(resourceNotFoundException, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ExpireDateException.class)
    public ResponseEntity<Object> expireDateException(ExpireDateException e) {
        var expireDateException = new ExpireDateException(e.getMessage());
        return new ResponseEntity<>(expireDateException, HttpStatus.NOT_FOUND);
    }
}
