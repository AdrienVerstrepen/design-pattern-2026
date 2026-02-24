package fr.fges.services.results;

public interface Result<T, E extends Exception> {
    boolean isSuccess();

    T value();

    E error();
}