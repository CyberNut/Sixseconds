package ru.cybernut.sixseconds.model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.io.Serializable;
import java.util.ArrayList;

public class Game implements Serializable {

    public static final int MAX_PLAYERS = 6;
    private QuestionsDBHelper dbHelper;
    private SQLiteDatabase db;

    private String question;
    private int numberOfQuestions = 0;
    private int numberOfPlayers = 0;
    private ArrayList<Player> players;
    private int currentPlayerIndex;
    private ArrayList<Integer> randomIdsList;

    public Game(int numberOfQuestions, int numberOfPlayers) {
        this.numberOfQuestions = numberOfQuestions;
        this.numberOfPlayers = numberOfPlayers;
        this.players = new ArrayList<>();
        this.currentPlayerIndex = 0;
    }

    public Game(int numberOfQuestions, ArrayList<String> playerNames) {
        this.numberOfQuestions = numberOfQuestions;
        this.players = new ArrayList<>();
        createPlayers(playerNames);
        this.numberOfPlayers = players.size();
    }

    public boolean initialize(Context context) {

        dbHelper = new QuestionsDBHelper(context);
        randomIdsList = dbHelper.getRandomIds(numberOfQuestions);
        if (randomIdsList.size() > 0) {
            return getNextQuestion();
        }
        return false;
    }

    public boolean getNextQuestion() {

        if(randomIdsList.size() > 0) {
            question = dbHelper.getQuestionById(randomIdsList.get(0));
            if (question != null) {
                randomIdsList.remove(0);
                return true;
            }
        }
        return false;
    }

    public void createPlayers(ArrayList<String> playerNames) {
        for (String playerName:playerNames) {
            Player player = new Player(playerName, false);
            players.add(player);
        }
    }

    public void getNextPlayer() {

        currentPlayerIndex++;
        if(currentPlayerIndex == numberOfPlayers) {
            currentPlayerIndex = 0;
        }
    }

    public Player getCurrentPlayer() {
        return players.get(currentPlayerIndex);
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public String getQuestionText() {
        return question;
    }
}
