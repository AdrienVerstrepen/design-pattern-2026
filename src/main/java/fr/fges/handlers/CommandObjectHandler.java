package fr.fges.handlers;

public interface CommandObjectHandler<C> {
    void execute(C command);
}
