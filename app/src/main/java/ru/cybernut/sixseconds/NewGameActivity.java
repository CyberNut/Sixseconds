package ru.cybernut.sixseconds;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import ru.cybernut.sixseconds.model.Game;

public class NewGameActivity extends AppCompatActivity {

    private EditText numberOfQuestions;
    private EditText numberOfPlayers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_game);

        numberOfPlayers = (EditText) findViewById(R.id.numberOfPlayers);
        numberOfQuestions = (EditText) findViewById(R.id.numberOfQuestions);

    }

    public void onStartGameClick(View view) {

        int tempNumberOfQuestions = Integer.parseInt(numberOfQuestions.getText().toString());
        int tempNumberOfPlayers = Integer.parseInt(numberOfPlayers.getText().toString());

        if(tempNumberOfQuestions <=0 || tempNumberOfPlayers <= 0) {
            Toast toast = Toast.makeText(this, "Incorrect data", Toast.LENGTH_SHORT);
            toast.show();
            return;
        }
        Game game = new Game(tempNumberOfQuestions, tempNumberOfPlayers);
        Intent intent = new Intent(this, GameActivity.class);
        intent.putExtra(GameActivity.EXTRA_GAME, game);
        startActivity(intent);

    }
}
