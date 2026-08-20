package br.com.projetoapi.api.resources.exceptions;

import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class ValidationError extends StandardError {

    private final List<String> messages;

    public ValidationError(
            LocalDateTime timestamp,
            Integer status,
            String error,
            String path,
            List<String> messages) {

        super(timestamp, status, error, path);
        this.messages = messages;
    }
}
