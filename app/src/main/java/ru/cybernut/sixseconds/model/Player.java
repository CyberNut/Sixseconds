package ru.cybernut.sixseconds.model;

public class Player {

    private String name;
    private int score;
    private boolean gameMaster;

    public Player(String name, boolean gameMaster) {
        this.name = name;
        this.gameMaster = gameMaster;
        this.score = 0;
    }

    public boolean isGameMaster() {
        return gameMaster;
    }

    public void setGameMaster(boolean gameMaster) {
        this.gameMaster = gameMaster;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void addScore() {
        this.score++;
    }
}
