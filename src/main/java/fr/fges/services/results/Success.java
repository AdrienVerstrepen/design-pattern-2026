package fr.fges.services.results;

public record Success<T, E>(T value) implements Result<T, E> {
    @Override
    public boolean isSuccess() {
        return true;
    }

    @Override
    public E error() {
        throw new UnsupportedOperationException("No error in success result");
    }
}