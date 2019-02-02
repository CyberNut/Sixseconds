package ru.cybernut.sixseconds.model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.io.Serializable;
import java.util.ArrayList;

public class Game implements Serializable {

    private static final int MAX_PLAYERS = 6;
    private QuestionsDBHelper dbHelper;
    private SQLiteDatabase db;

    private String question;
    private int numberOfQuestions = 0;
    private int numberOfPlayers = 0;
    private Player[] players;
    private ArrayList<Integer> randomIdsList;

    public Game(int numberOfQuestions, int numberOfPlayers) {
        this.numberOfQuestions = numberOfQuestions;
        this.numberOfPlayers = numberOfPlayers;
        this.players = new Player[MAX_PLAYERS];
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

    public String getQuestionText() {
        return question;
    }
}
