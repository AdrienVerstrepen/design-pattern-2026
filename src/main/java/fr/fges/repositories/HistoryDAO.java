package fr.fges.repositories;


import java.util.List;

public interface HistoryDAO {

	boolean saveModification(/*type*/ command);

	List</*type*/> findAll();

	boolean restoreModification();

}
