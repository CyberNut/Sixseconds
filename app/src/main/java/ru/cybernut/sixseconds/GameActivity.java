package ru.cybernut.sixseconds;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import ru.cybernut.sixseconds.model.Game;
import ru.cybernut.sixseconds.model.Player;

public class GameActivity extends AppCompatActivity {

    public static final String EXTRA_GAME = "EXTRA_GAME";
    private Game game;
    private TextView questionTextView;
    private ListView playerListView;
    private TextView currentPlayerInfo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_test);

        questionTextView = (TextView) findViewById(R.id.questionText);
        game = (Game) getIntent().getSerializableExtra(EXTRA_GAME);
        game.initialize(this);
        updateQuestion();

        playerListView = (ListView) findViewById(R.id.playersList);
        currentPlayerInfo = (TextView) findViewById(R.id.currentPlayerScore);
        showPlayerInfo();
    }

    private void updateQuestion() {
        questionTextView.setText(game.getQuestionText());
    }

    public void onIncorrectButtonClick(View view) {
        if(game.getNextQuestion()) {
            updateQuestion();
            game.getNextPlayer();
            showPlayerInfo();
        } else {
            Toast.makeText(this, "Game over!", Toast.LENGTH_SHORT).show();
        }
    }

    public void onCorrectButtonClick(View view) {
        if(game.getNextQuestion()) {
            updateQuestion();
            game.getCurrentPlayer().addScore();
            game.getNextPlayer();
            showPlayerInfo();
        } else {
            Toast.makeText(this, "Game over!", Toast.LENGTH_SHORT).show();
        }
    }

    private void showPlayerInfo() {
        Player currentPlayer = game.getCurrentPlayer();
        currentPlayerInfo.setText(currentPlayer.getName() + " (" + currentPlayer.getScore() + ")");
    }
}
