package com.example.fruitsbasket;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button m_startButton = findViewById(R.id.StartGameBtn);
        Button m_scoresButton = findViewById(R.id.ScoresBtn);
        Button m_quitButton = findViewById(R.id.ExitBtn);


        m_quitButton.setOnClickListener(view -> finish());

        m_scoresButton.setOnClickListener(view -> {
            Intent scores = new Intent(MainActivity.this, Scores.class);
            startActivity(scores);
        });

        m_startButton.setOnClickListener(view -> {
            Intent gameplay = new Intent(MainActivity.this, GameBoard.class);
            startActivity(gameplay);
        });


    }
}