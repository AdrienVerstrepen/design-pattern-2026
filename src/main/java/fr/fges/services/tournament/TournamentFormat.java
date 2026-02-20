package fr.fges.services.tournament;

import fr.fges.data.models.Player;

import java.util.List;

public interface TournamentFormat {
	String label();
	void playMatch(Player player1, Player player2);
	void attributePoints(Player player, Integer gain);
	List<Player> playTournament();
	void setPlayers(List<Player> players);
}
