package fr.fges.services.tournament;

import fr.fges.data.models.Player;

import java.util.List;

public class KingOfTheHillFormat implements TournamentFormat{
	@Override
	public String label() {
		return "";
	}

	@Override
	public void playMatch(Player player1, Player player2) {

	}

	@Override
	public void attributePoints(Player player, Integer gain) {

	}

	@Override
	public List<Player> playTournament() {
		return List.of();
	}
}
