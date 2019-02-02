package ru.cybernut.sixseconds;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import ru.cybernut.sixseconds.model.Game;

public class GameActivity extends AppCompatActivity {

    public static final String EXTRA_GAME = "EXTRA_GAME";
    private Game game;
    private TextView questionTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        questionTextView = (TextView) findViewById(R.id.questionText);
        game = (Game) getIntent().getSerializableExtra(EXTRA_GAME);
        game.initialize(this);
        updateQuestion();

    }

    private void updateQuestion() {
        questionTextView.setText(game.getQuestionText());
    }

    public void onIncorrectButtonClick(View view) {
        if(game.getNextQuestion()) {
            updateQuestion();
        } else {
            Toast.makeText(this, "Game over!", Toast.LENGTH_SHORT).show();
        }
    }

    public void onCorrectButtonClick(View view) {
        if(game.getNextQuestion()) {
            updateQuestion();
        } else {
            Toast.makeText(this, "Game over!", Toast.LENGTH_SHORT).show();
        }
    }
}
