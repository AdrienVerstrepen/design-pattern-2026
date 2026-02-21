package fr.fges.services.results;

public record Failure<T, E>(E error) implements Result<T, E> {
    @Override
    public boolean isSuccess() {
        return false;
    }

    @Override
    public T value() {
        throw new UnsupportedOperationException("No value in failure result");
    }
}