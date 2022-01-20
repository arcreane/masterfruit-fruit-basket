package com.example.fruitsbasket;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class Scores extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scores);

        ListView scoreListView = (ListView) findViewById(R.id.scoreListV);

        RegisteredScore Score1 = new RegisteredScore("Pipi", "2022/01/17", 0);
        RegisteredScore Score2 = new RegisteredScore("Popo", "2022/01/04", 9);

        ScoreBDD scoreBdd = new ScoreBDD(this);
        scoreBdd.openForWrite();
        scoreBdd.removeScore("Popo");
        //scoreBdd.insertScore(Score1);
        //scoreBdd.updateScore(1, Score2);
        //scoreBdd.removeScore("Lulu");

        ArrayList<RegisteredScore> scoreListData = scoreBdd.getAllScores();
        scoreBdd.close();

        ArrayAdapter<RegisteredScore> adapter = new ArrayAdapter<RegisteredScore>(this,
                android.R.layout.simple_list_item_1, scoreListData);
        scoreListView.setAdapter(adapter);
    }
}