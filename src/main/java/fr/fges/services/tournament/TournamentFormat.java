package fr.fges.services.tournament;
import fr.fges.data.models.Player;
import java.util.List;

public interface TournamentFormat {
	String label();
	void attributePoints(Player player, Integer gain);
	void setPlayers(List<Player> players);
	int getNumberOfMatches();
	List<Player> getCurrentMatch();
	void registerMatch(Player winner, Player loser);
	List<Player> getPlayers();
}