package fr.fges.repositories;
import fr.fges.models.commands.Command;

import java.util.ArrayList;
import java.util.List;

public class HistoryDaoRam implements HistoryDao {
	private final List<Command> modification = new ArrayList<>();

	@Override
	public void saveModification(Command command) {

	}

	@Override
	public List<Command> findAll() {
		return modification;
	}

	@Override
	public void undo() {
	}
}