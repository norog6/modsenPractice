package org.modsen.practise.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class ControllerExceptionHandler {
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<?> onRuntimeException(RuntimeException ex){
        return new ResponseEntity<>(ex.getMessage(),HttpStatus.OK);
    }
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ValidationErrorMessage> onConstraintValidationException(
            ConstraintViolationException ex) {
        final List<Violation> violations = ex.getConstraintViolations().stream()
                .map(
                        violation -> new Violation(
                                violation.getPropertyPath().toString(),
                                violation.getMessage()
                        )
                )
                .collect(Collectors.toList());
        ValidationErrorMessage errorMessage = new ValidationErrorMessage(violations);

        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationErrorMessage> onMethodArgumentNotValidException(
            MethodArgumentNotValidException e) {
        final List<Violation> violations = e.getBindingResult().getFieldErrors().stream()
                .map(error -> new Violation(error.getField(), error.getDefaultMessage()))
                .collect(Collectors.toList());
        ValidationErrorMessage errorMessage = new ValidationErrorMessage(violations);

        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }
}
