package fr.fges.services.factories;
import fr.fges.UI.formatters.MenuInterface;
import fr.fges.services.tournament.ChampionshipFormat;
import fr.fges.services.tournament.KingOfTheHillFormat;
import fr.fges.services.tournament.TournamentFormat;
import java.util.ArrayList;
import java.util.List;

public class TournamentFormatFactory {
    public static List<TournamentFormat> create(MenuInterface UI) {
        List<TournamentFormat> tournamentFormats = new ArrayList<>();
        tournamentFormats.add(new ChampionshipFormat("Championship (everyone plays everyone)", UI));
        tournamentFormats.add(new KingOfTheHillFormat("King of the Hill (winner stays)", UI));
        return tournamentFormats;
    }
}