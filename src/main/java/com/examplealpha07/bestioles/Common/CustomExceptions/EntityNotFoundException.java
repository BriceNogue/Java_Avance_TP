package com.examplealpha07.bestioles.Common.CustomExceptions;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class EntityNotFoundException extends RuntimeException{
    public EntityNotFoundException() {
        super("Entity not found!");
        setStackTrace(new StackTraceElement[0]);
    }
}
