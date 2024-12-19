package com.examplealpha07.bestioles.Common;

import com.examplealpha07.bestioles.Common.CustomExceptions.EntityNotFoundException;
import com.examplealpha07.bestioles.Common.CustomExceptions.EntityToCreateHasAnIdException;
import com.examplealpha07.bestioles.Common.CustomExceptions.EntityToUpdateHasNoIdException;
import com.examplealpha07.bestioles.DTO.ErrorDto;
import com.examplealpha07.bestioles.DTO.InvalidEntityErrorDto;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class RequestHandler {

    public RequestHandler() {}

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ErrorDto> handleEntityNotFound(EntityNotFoundException ex, HttpServletRequest request) {
        ErrorDto errorDto = new ErrorDto(LocalDateTime.now(), ex.getMessage(), request.getRequestURL().toString());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDto);
    }

    @ExceptionHandler({EntityToCreateHasAnIdException.class, EntityToUpdateHasNoIdException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorDto> handleEntityCreationExceptions(RuntimeException ex, HttpServletRequest request) {
        ErrorDto errorDto = new ErrorDto(LocalDateTime.now(), ex.getMessage(), request.getRequestURL().toString());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDto);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<InvalidEntityErrorDto> handleValidationExceptions(MethodArgumentNotValidException ex, HttpServletRequest request) {
        InvalidEntityErrorDto errorDto = new InvalidEntityErrorDto(
                LocalDateTime.now(),
                "Validation errors occurred.",
                request.getRequestURL().toString(),
                ex.getBindingResult()
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDto);
    }
}
