package com.examplealpha07.bestioles.Common.CustomExceptions;

public class EntityToCreateHasAnIdException extends RuntimeException {
    public EntityToCreateHasAnIdException() {
        super("Entity to create has an Id!");
        setStackTrace(new StackTraceElement[0]);
    }
}
