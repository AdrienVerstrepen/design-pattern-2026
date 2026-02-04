package fr.fges.handlers;

public interface CommandHandler<C> {
    void execute(C command);
}
