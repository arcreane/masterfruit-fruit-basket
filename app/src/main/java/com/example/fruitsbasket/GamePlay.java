package com.example.fruitsbasket;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;

public class GamePlay extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_play);

        //ArrayList of enum generated automatically
        ArrayList<Fruits> m_fruitsCombine = Functions.generateFruitCombination();
        Toast.makeText(this, ""+m_fruitsCombine.toString(), Toast.LENGTH_SHORT).show();


        ArrayList<StringBuilder> hints = Functions.generateHints(m_fruitsCombine);
        Toast.makeText(this, hints.get(0)+"\n"+hints.get(1), Toast.LENGTH_SHORT).show();



    }
}