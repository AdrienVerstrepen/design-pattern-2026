package fr.fges.services.results;

public interface Result<T, E> {
    boolean isSuccess();

    T value();

    E error();
}