package com.example.fruitsbasket;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Scores extends AppCompatActivity {
    private LayoutInflater layoutInflater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scores);

        ListView scoreListView = (ListView) findViewById(R.id.scoreListV);

        RegisteredScore Score1 = new RegisteredScore("Pipi", 0, 0);
        RegisteredScore Score2 = new RegisteredScore("Popo", 6, 9);

        ScoreBDD scoreBDD = new ScoreBDD(this);
        scoreBDD.openForWrite();
        //scoreBdd.dropTable();
        //scoreBdd.removeScore("Popo");
        scoreBDD.insertScore(Score1);
        //scoreBDD.insertScore(Score2);
        //scoreBdd.removeScore("Lulu");

        ArrayList<RegisteredScore> scoreListData = scoreBDD.getAllScores();
        scoreBDD.close();

        ArrayAdapter<RegisteredScore> adapter = new ArrayAdapter<RegisteredScore>(this,
                android.R.layout.simple_list_item_1, scoreListData);
        scoreListView.setAdapter(adapter);
    }


}