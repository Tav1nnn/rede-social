package br.com.otavio.clonetwitter.exceptions.handler;

import br.com.otavio.clonetwitter.exceptions.model.ValidationError;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationError> validationDto(MethodArgumentNotValidException e, HttpServletRequest request){
        ValidationError validationError = new ValidationError();

        validationError.setTimestamp(Instant.now());
        validationError.setStatus(HttpStatus.UNPROCESSABLE_ENTITY.value());
        validationError.setError("Validation exception");
        validationError.setMessage(e.getMessage());
        validationError.setPath(request.getRequestURI());

        for(FieldError fieldError : e.getBindingResult().getFieldErrors()) {
            validationError.addError(fieldError.getField(), fieldError.getDefaultMessage());
        }

        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(validationError);
    }
}
