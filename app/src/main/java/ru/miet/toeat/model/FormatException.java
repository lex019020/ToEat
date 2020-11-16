package ru.miet.toeat.model;

public class FormatException extends Exception{
    private static final long serialVersionUID = 1L;

    private String message;

    public FormatException(String message) {
        this.message = message;
    }

    public String toString() {
        return message;
    }
}
