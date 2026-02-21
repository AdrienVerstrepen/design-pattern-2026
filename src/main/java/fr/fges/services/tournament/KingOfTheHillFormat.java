package fr.fges.services.tournament;
import fr.fges.UI.formatters.MenuInterface;
import fr.fges.data.models.Player;
import java.util.ArrayList;
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
		return label;
	}

	@Override
	public void playMatch(Player currentWinner, Player newPlayer) {
		UI.displayMessage("=== " + currentWinner.getName() + " VS " + newPlayer.getName() + " ===");
		String winner = "";
		while (!winner.equals("1") && !winner.equals("2")) {
			winner = UI.getUserInput("Winner (1=" + currentWinner.getName() + ", 2=" + newPlayer.getName() + "): ");
			if (!winner.equals("1") && !winner.equals("2")) {
				UI.displayMessage("Invalid input. Please enter 1 or 2.");
			}
		}
		if (winner.equals("1")) {
			attributePoints(currentWinner, 3);
			attributePoints(newPlayer, 1);
		} else {
			attributePoints(newPlayer, 3);
			attributePoints(currentWinner, 1);
		}
	}

	@Override
	public void attributePoints(Player player, Integer gain) {
		player.setPoints(player.getPoints() + gain);
		if (gain == 3) {
			player.setNumberOfWins(player.getNumberOfWins() + 1 );
		}
	}

	@Override
	public List<Player> playTournament() {
		if (players == null || players.isEmpty()){
			return List.of();
		}
		List<Player> remainingPlayers = new ArrayList<>(players);
		Player currentWinner = remainingPlayers.getFirst();
		for (int i = 1; i < remainingPlayers.size(); i++) {
			Player newPlayer = remainingPlayers.get(i);
			int currentWinnerWinsBeforeMatch = currentWinner.getNumberOfWins();
			playMatch(currentWinner, newPlayer);
			if (currentWinnerWinsBeforeMatch == currentWinner.getNumberOfWins()){
				currentWinner = newPlayer;
			}
		}
		return players;
	}

	@Override
	public void setPlayers(List<Player> players) {
		this.players = players;
	}
}