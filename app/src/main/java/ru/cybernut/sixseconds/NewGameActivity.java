package ru.cybernut.sixseconds;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import java.util.ArrayList;

import ru.cybernut.sixseconds.model.Game;

public class NewGameActivity extends AppCompatActivity {

    private EditText numberOfQuestions;
    private EditText playerName;
    private ListView playersList;
    private final ArrayList<String> playerNames = new ArrayList<>();
    private ArrayAdapter<String> playersListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_game);

        playerName = (EditText) findViewById(R.id.playerName);
        numberOfQuestions = (EditText) findViewById(R.id.numberOfQuestions);
        playersList = (ListView) findViewById(R.id.playersList);
        playersListAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, playerNames);
        playersList.setAdapter(playersListAdapter);
    }

    public void onStartGameClick(View view) {

        int tempNumberOfQuestions = Integer.parseInt(numberOfQuestions.getText().toString());
        int tempNumberOfPlayers = playerNames.size();
        if(tempNumberOfQuestions <=0 || tempNumberOfPlayers <= 0) {
            Toast toast = Toast.makeText(this, "Incorrect data", Toast.LENGTH_SHORT);
            toast.show();
            return;
        } else {
            Game game = new Game(tempNumberOfQuestions, playerNames);
            Intent intent = new Intent(this, GameActivity.class);
            intent.putExtra(GameActivity.EXTRA_GAME, game);
            startActivity(intent);
        }
    }

    public void onAddPlayerButtonClick(View view) {
        if (playerNames.size() < Game.MAX_PLAYERS) {
            String name = playerName.getText().toString();
            if (!"".equals(name)) {
                playerNames.add(name);
                playersListAdapter.notifyDataSetChanged();
                playerName.setText("");
            }
        } else {
            Toast.makeText(this, R.string.max_player_error, Toast.LENGTH_SHORT).show();
        }
    }
}
