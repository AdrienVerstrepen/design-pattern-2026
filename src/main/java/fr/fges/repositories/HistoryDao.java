package fr.fges.repositories;
import fr.fges.models.commands.Command;
import java.util.List;

public interface HistoryDao {
	void saveModification(Command command);

	List<Command> findAll();

	Command removeLast();

}