package ru.cybernut.sixseconds.model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.io.Serializable;

public class Game implements Serializable {

    private static final int MAX_PLAYERS = 6;
    private QuestionsDBHelper dbHelper;
    private SQLiteDatabase db;

    private int numberOfQuestions = 0;
    private int numberOfPlayers = 0;
    private Player[] players;

    public Game(int numberOfQuestions, int numberOfPlayers) {
        this.numberOfQuestions = numberOfQuestions;
        this.numberOfPlayers = numberOfPlayers;
        this.players = new Player[MAX_PLAYERS];
    }

    public boolean initialize(Context context) {

        dbHelper = new QuestionsDBHelper(context);
        //db = dbHelper.getDB();
        dbHelper.getRandomQuestions(numberOfQuestions);

        return true;
    }


}
