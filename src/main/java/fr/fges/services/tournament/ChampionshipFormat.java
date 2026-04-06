package fr.fges.services.tournament;
import fr.fges.data.models.Player;

import java.util.ArrayList;
import java.util.List;

public class ChampionshipFormat implements TournamentFormat{
	private final String label;
	private List<Player> players;
	private int numberOfMatches;
	private List<List<Player>> matchQueue;

	public ChampionshipFormat(String label) {
		this.label = label;
	}

	@Override
	public String label() {
		return this.label;
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
		this.matchQueue = new ArrayList<>();
		for (int i = 0; i < players.size(); i++) {
			for (int j = i + 1; j < players.size(); j++) {
				this.matchQueue.add(List.of(players.get(i), players.get(j)));
			}
		}
		this.numberOfMatches = ((players.size()-1)*players.size())/2;
	}

	@Override
	public int getNumberOfMatches() {
		return this.numberOfMatches;
	}

	@Override
	public List<Player> getCurrentMatch() {
		if (matchQueue.isEmpty()) {
			return null;
		}
		return matchQueue.getFirst();
	}

	@Override
	public void registerMatch(Player winner, Player loser) {
		attributePoints(winner, 3);
		attributePoints(loser, 1);
		if(!matchQueue.isEmpty()) {
			matchQueue.removeFirst();
		}
	}

	@Override
	public List<Player> getPlayers() {
		return this.players;
	}
}