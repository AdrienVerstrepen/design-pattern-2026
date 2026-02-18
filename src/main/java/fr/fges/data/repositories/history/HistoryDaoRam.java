package fr.fges.data.repositories.history;
import fr.fges.data.commands.Command;
import java.util.ArrayList;
import java.util.List;

public class HistoryDaoRam implements HistoryDao {
	private final List<Command> modification = new ArrayList<>();

	@Override
	public void saveModification(Command command) {
		modification.add(command);

	}

	@Override
	public List<Command> findAll() {
		return modification;
	}

	@Override
	public Command removeLast() {
		return modification.removeLast();
	}
}