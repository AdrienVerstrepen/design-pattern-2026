package fr.fges.services.tournament;
import fr.fges.data.models.Player;
import java.util.ArrayList;
import java.util.List;

public class KingOfTheHillFormat implements TournamentFormat{
	private final String label;
	private List<Player> players;
	private int numberOfMatches;
	private Player currentWinner;
	private int nextChallengerIndex;

	public KingOfTheHillFormat(String label) {
		this.label = label;
	}

	@Override
	public String label() {
		return label;
	}

	@Override
	public void attributePoints(Player player, Integer gain) {
		player.setPoints(player.getPoints() + gain);
		if (gain == 3) {
			player.setNumberOfWins(player.getNumberOfWins() + 1 );
		}
	}

	@Override
	public void setPlayers(List<Player> players) {
		this.players = players;
		this.numberOfMatches = players.size() - 1;
		this.currentWinner = players.getFirst();
		this.nextChallengerIndex = 1;
	}

	@Override
	public int getNumberOfMatches() {
		return this.numberOfMatches;
	}

    @Override
    public List<Player> getCurrentMatch() {
        if (nextChallengerIndex >= players.size()) {
			return null;
		}
		return List.of(currentWinner, players.get(nextChallengerIndex));
    }

	@Override
	public void registerMatch(Player winner, Player loser) {
		attributePoints(winner, 3);
		attributePoints(loser, 1);

		this.currentWinner = winner;
		this.nextChallengerIndex++;
	}

	@Override
	public List<Player> getPlayers() {
		return this.players;
	}

}