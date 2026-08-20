package br.com.projetoapi.api.resources.exceptions;

import br.com.projetoapi.api.services.exceptions.DataIntegratyViolationException;
import br.com.projetoapi.api.services.exceptions.ObjectNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

@ControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundException ex,
                                                        HttpServletRequest request) {
        StandardError error = new StandardError(LocalDateTime.now(),
                HttpStatus.NOT_FOUND.value(),
                ex.getMessage(),
                request.getRequestURI());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(DataIntegratyViolationException.class)
    public ResponseEntity<StandardError> dataIntegrityViolationException(DataIntegratyViolationException ex,
                                                                         HttpServletRequest request) {
        StandardError error =
                new StandardError(LocalDateTime.now(),
                        HttpStatus.BAD_REQUEST.value(),
                        ex.getMessage(),
                        request.getRequestURI());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationError> validationError(
            MethodArgumentNotValidException ex,
            HttpServletRequest request) {

        List<String> messages = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> error.getDefaultMessage())
                .toList();

        ValidationError error = new ValidationError(
                LocalDateTime.now(ZoneId.of("America/Sao_Paulo")),
                HttpStatus.BAD_REQUEST.value(),
                "Validation error",
                request.getRequestURI(),
                messages
        );

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(error);
    }
}
