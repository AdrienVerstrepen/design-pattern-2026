package fr.fges.services.results;

public class Failure<T, E> implements Result<T, E> {
    private final E error;

    public Failure(E error) {
        this.error = error;
    }

    @Override
    public boolean isSuccess() {
        return false;
    }

    @Override
    public T getValue() {
        throw new UnsupportedOperationException("No value in failure result");
    }

    @Override
    public E getError() {
        return error;
    }
}