package fr.fges.services.entriesServices;

public interface EntryService<I, O> {
    O execute(I input);
}
