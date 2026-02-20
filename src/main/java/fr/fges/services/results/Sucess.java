package fr.fges.services.results;

public class Sucess<T, E> implements Result<T, E> {
    private final T value;

    public Sucess(T value) {
        this.value = value;
    }

    @Override
    public boolean isSuccess() {
        return true;
    }

    @Override
    public T getValue() {
        return value;
    }

    @Override
    public E getError() {
        throw new UnsupportedOperationException("No error in success result");
    }
}
