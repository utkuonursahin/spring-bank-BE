package me.utku.springbank.exception;

public class EntityNotFoundException extends RuntimeException {

    public EntityNotFoundException() {
        super("Entity not found");
    }
}
