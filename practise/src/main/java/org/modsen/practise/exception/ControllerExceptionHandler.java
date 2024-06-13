package org.modsen.practise.exception;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerExceptionHandler {
    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ErrorMessage> customException(CustomException ex){
        ErrorMessage errorMessage = new ErrorMessage(ex.getStatus(), ex.getMessage());
        return new ResponseEntity<>(errorMessage, HttpStatusCode.valueOf(ex.getStatus()));
    }
}
