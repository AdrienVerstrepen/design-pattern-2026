package fr.fges.data.repositories.history;
import fr.fges.data.commands.Command;
import java.util.List;

public interface HistoryDao {
	void saveModification(Command command);

	List<Command> findAll();

	Command removeLast();
}