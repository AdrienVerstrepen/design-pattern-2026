package fr.fges.services.tournament;

import fr.fges.UI.formatters.MenuInterface;
import fr.fges.data.models.Player;

import java.util.List;

public class ChampionshipFormat implements TournamentFormat{
	private final String label;
	private List<Player> players;
	private final MenuInterface UI;

	public ChampionshipFormat(String label, List<Player> players, MenuInterface UI) {
		this.label = label;
		this.players = players;
		this.UI = UI;
	}

	@Override
	public String label() {
		return this.label;
	}

	@Override
	public void playMatch(Player player1, Player player2) {
		UI.displayMessage("≡≡≡" + player1.name() + " VS " + player2.name() + "≡≡≡");
		String winner = UI.getUserInput("Winner (1="+player1.name()+", 2="+player2.name()+"): ");

		int player1Point = winner.equals("1") ? 3 : 1;
		int player2Point = winner.equals("2") ? 3 : 1;
		attributePoints(player1, player1Point);
		attributePoints(player2, player2Point);
	}

	@Override
	public void attributePoints(Player player, Integer gain) {
		//player.points += gain;
	}

	@Override
	public List<Player> playTournament() {

		for (int i = 0; i < players.size()-1 ; i++){
			for (int j = i; j < players.size(); j++){
				playMatch(players.get(i), players.get(j));
			}
		}

		return players;
	}
}
