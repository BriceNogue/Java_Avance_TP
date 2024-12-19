package com.examplealpha07.bestioles.DTO;

import org.springframework.validation.BindingResult;

import java.time.LocalDateTime;
import java.util.List;

public class InvalidEntityErrorDto extends ErrorDto {

    private List<String> globalErrors;
    private List<FieldErrorDto> fieldErrors;

    public InvalidEntityErrorDto(LocalDateTime timestamp, String message, String url, BindingResult bindingResult) {
        super(timestamp, message, url);
        this.globalErrors = bindingResult.getGlobalErrors()
                .stream()
                .map(error -> error.getDefaultMessage())
                .toList();
        this.fieldErrors = bindingResult.getFieldErrors()
                .stream()
                .map(error -> new FieldErrorDto(error.getField(), error.getDefaultMessage()))
                .toList();
    }

    public List<String> getGlobalErrors() {
        return globalErrors;
    }

    public void setGlobalErrors(List<String> globalErrors) {
        this.globalErrors = globalErrors;
    }

    public List<FieldErrorDto> getFieldErrors() {
        return fieldErrors;
    }

    public void setFieldErrors(List<FieldErrorDto> fieldErrors) {
        this.fieldErrors = fieldErrors;
    }

    public static class FieldErrorDto {
        private String field;
        private String message;

        public FieldErrorDto(String field, String message) {
            this.field = field;
            this.message = message;
        }

        public String getField() {
            return field;
        }

        public void setField(String field) {
            this.field = field;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }
}
