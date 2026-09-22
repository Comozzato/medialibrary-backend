package com.wille.template.exception;

import java.util.Map;

import lombok.Data;


@Data
public class ValidationError {

    private String message;
    private Map<String, String> errors;

    public ValidationError(
            String message,
            Map<String, String> errors
    ) {
        this.message = message;
        this.errors = errors;
    }
}