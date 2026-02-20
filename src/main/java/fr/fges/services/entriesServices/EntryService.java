package fr.fges.services.entriesServices;

import fr.fges.services.results.Result;

public interface EntryService<T, E> {
    public Result<T, E> execute();
}
