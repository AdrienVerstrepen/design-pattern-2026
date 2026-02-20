package fr.fges.services.tournament;

import fr.fges.UI.formatters.MenuInterface;
import fr.fges.data.models.Player;

import java.util.List;

public class KingOfTheHillFormat implements TournamentFormat{
	private final String label;
	private List<Player> players;
	private final MenuInterface UI;

	public KingOfTheHillFormat(String label, MenuInterface UI) {
		this.label = label;
		this.UI = UI;
	}

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

	@Override
	public void setPlayers(List<Player> players) {

	}


}
