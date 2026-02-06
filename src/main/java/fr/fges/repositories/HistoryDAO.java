package fr.fges.repositories;
import fr.fges.models.commands.Command;

import java.util.List;

public interface HistoryDAO {
	void saveModification(Command command);

	List<Command> findAll();

	void undo();

}