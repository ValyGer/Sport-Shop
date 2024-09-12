package ru.kpepskot.sport_shop.error;

public class DataConflictRequest extends RuntimeException{
    public DataConflictRequest (String message) {
        super(message);
    }
}
