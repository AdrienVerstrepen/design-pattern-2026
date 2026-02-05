package fr.fges.repositories;


import java.util.ArrayList;
import java.util.List;

public class HistoryDAORam implements HistoryDAO{
	private final List</*type*/> games = new ArrayList<>();

	@Override
	public boolean saveModification() {
		return false;
	}

	@Override
	public List findAll() {
		return List.of();
	}

	@Override
	public boolean restoreModification() {
		return false;
	}
}
