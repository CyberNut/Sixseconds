package ru.cybernut.sixseconds.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.Random;

public class QuestionsDBHelper extends SQLiteOpenHelper {

    private static String DB_NAME = "sixseconds";
    private static int DB_VERSION = 1;

    public QuestionsDBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE QUESTION (_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "QUESTION_TEXT TEXT);");
        insertQuestion(db, "Name 3 Mathematical symbols");
        insertQuestion(db, "Name 3 countries you really want to visit");
        insertQuestion(db, "Name 3 dice games");
        insertQuestion(db, "Name 3 comedians");
        insertQuestion(db, "Name 3 talk show hosts");
        insertQuestion(db, "Name 3 breakfast foods");
        insertQuestion(db, "Name 3 deceased actresses");
        insertQuestion(db, "Name 3 movie theatre snacks");
        insertQuestion(db, "Name 3 sports not played with a ball");
        insertQuestion(db, "Name 3 types of jewellery");
        insertQuestion(db, "Name 3 famous TV detectives");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public SQLiteDatabase getDB() {

        SQLiteDatabase db = this.getReadableDatabase();
        return db;
    }

    private void insertQuestion(SQLiteDatabase db, String questionText) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("QUESTION_TEXT", questionText);
        db.insert("QUESTION", null  , contentValues);
    }

    public Cursor getRandomQuestions(int numberOfQuestions) {
        int recievedQuestion = 0;
        int count = 0;
        ArrayList<Integer> fullIDList = new ArrayList<>();
        ArrayList<String> randomIDList = new ArrayList<>();
        Random random = new Random();
        SQLiteDatabase db =  getDB();
        if (db == null) {
            return null;
        };
        Cursor cursor = db.query("QUESTION", new String[] {"_id"}, null, null, null, null, null);
        count = cursor.getCount();
        if (count > 0) {
            while (cursor.moveToNext()) {
                fullIDList.add(cursor.getInt(0));
            }

            while (recievedQuestion < numberOfQuestions) {
                String tempId = fullIDList.get(random.nextInt(count)).toString();
                if (!randomIDList.contains(tempId)) {
                    randomIDList.add(tempId);
                    recievedQuestion++;
                }
            }
            String[] strArray = randomIDList.toArray(new String[randomIDList.size()]);
            try {
                cursor = db.query("QUESTION", new String[] {"_id"}, "_id IN (?)", strArray, null, null, null);
                while (cursor.moveToNext()) {
                    Log.i("DB_TEST", "getRandomQuestions: " + cursor.getInt(0));
                }

            } catch (SQLException e) {
                Log.e("DB_ERROR", "getRandomQuestions: " + e.getMessage());
            }

        }

        return cursor;
    }

}
