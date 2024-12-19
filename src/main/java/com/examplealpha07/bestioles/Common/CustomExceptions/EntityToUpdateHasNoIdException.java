package com.examplealpha07.bestioles.Common.CustomExceptions;

public class EntityToUpdateHasNoIdException extends RuntimeException{
    public EntityToUpdateHasNoIdException(){
        super("Entity to update has no Id!");
        setStackTrace(new StackTraceElement[0]);
    }
}
