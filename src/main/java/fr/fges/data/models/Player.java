package fr.fges.data.models;

public class Player {
    private String name;
    private int numberOfWins;
    private int points;

    public Player(String name, int numberOfWins, int points) {
        this.name = name;
        this.numberOfWins = numberOfWins;
        this.points = points;
    }

    public String getName() {
        return name;
    }

    public int getNumberOfWins() {
        return numberOfWins;
    }

    public int getPoints() {
        return points;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNumberOfWins(int numberOfWins) {
        this.numberOfWins = numberOfWins;
    }

    public void setPoints(int points) {
        this.points = points;
    }
}