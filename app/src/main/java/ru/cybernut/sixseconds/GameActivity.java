package ru.cybernut.sixseconds;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import ru.cybernut.sixseconds.model.Game;

public class GameActivity extends AppCompatActivity {

    public static final String EXTRA_GAME = "EXTRA_GAME";
    private Game game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        game = (Game) getIntent().getSerializableExtra(EXTRA_GAME);
        game.initialize(this);
        setContentView(R.layout.activity_game);

    }

    public void setGame(Game game) {
        this.game = game;

    }
}
