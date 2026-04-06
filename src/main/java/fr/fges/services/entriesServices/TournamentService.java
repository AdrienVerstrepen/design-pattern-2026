package fr.fges.services.entriesServices;
import fr.fges.UI.formatters.MenuInterface;
import fr.fges.data.models.BoardGame;
import fr.fges.data.models.Player;
import fr.fges.data.repositories.games.GameCollectionDao;
import fr.fges.services.exceptions.NoMatchingGamesException;
import fr.fges.services.factories.TournamentFormatFactory;
import fr.fges.services.results.Failure;
import fr.fges.services.results.Result;
import fr.fges.services.results.Success;
import fr.fges.services.tournament.TournamentFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class TournamentService {
    private final GameCollectionDao gamesDao;

    public TournamentService(GameCollectionDao gamesDao) {
        this.gamesDao = gamesDao;
    }

    public Result<List<BoardGame>, Exception> findGames() {
        List<BoardGame> twoPlayerGames = gamesDao.findByNumberOfPlayers(2);
        if (!twoPlayerGames.isEmpty()) {
            return new Success<>(twoPlayerGames);
        }
        return new Failure<>(new NoMatchingGamesException());
    }

    public List<Player> instantiatePlayers(List<String> playerNames) {
        List<Player> players = new ArrayList<>();
        for (String playerName : playerNames) {
            players.add(new Player(playerName, 0, 0));
        }
        return players;
    }

    public List<TournamentFormat> obtainFormats() {
        return TournamentFormatFactory.create();
    }

    public int obtainNumberOfMatches(TournamentFormat format) {
        return format.getNumberOfMatches();
    }

    public List<Player> getMatchParticipants(TournamentFormat format) {
        return format.getCurrentMatch();
    }

    public void registerWinner(Player winner, Player loser, TournamentFormat format) {
        format.registerMatch(winner, loser);
    }

    public Result<List<Player>, Exception> obtainResults(TournamentFormat format) {
        List<Player> players = new ArrayList<>(format.getPlayers());
        players.sort(Comparator.comparingInt(Player::getPoints).reversed());
        return new Success<>(players);
    }
}