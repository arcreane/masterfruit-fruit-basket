package com.example.fruitsbasket;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

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
        //scoreBdd.removeScore("Popo");
        scoreBdd.insertScore(Score1);
        //scoreBdd.updateScore(1, Score2);
        //scoreBdd.removeScore("Lulu");

        ArrayList<RegisteredScore> scoreListData = scoreBdd.getAllScores();
        scoreBdd.close();

        ArrayAdapter<RegisteredScore> adapter = new ArrayAdapter<RegisteredScore>(this,
                R.layout.list_item_score_layout, scoreListData) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent){
                View view = super.getView(position, convertView, parent);
                TextView name = view.findViewById(R.id.tvName);
                TextView wons = view.findViewById(R.id.tvWons);
                TextView score = view.findViewById(R.id.tvScore);
                name.setText(scoreListData.get(3).toString());
                wons.setText(scoreListData.get(1).toString());
                score.setText(scoreListData.get(2).toString());
                return view;
            }
        } ;
        scoreListView.setAdapter(adapter);
    }
}