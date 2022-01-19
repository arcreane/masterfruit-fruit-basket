package com.example.fruitsbasket;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;

import com.example.fruitsbasket.history_view_holder.MyRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.Arrays;

public class GamePlay extends AppCompatActivity implements MyRecyclerViewAdapter.ItemClickListener {

    MyRecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_play);

        // Test de la fonction Verification de la combinaison playeur

        ArrayList<Fruits> combination = new ArrayList<Fruits>();
        combination.add(Fruits.BANANA);
        combination.add(Fruits.KIWI);
        combination.add(Fruits.PRUNE);
        combination.add(Fruits.GRAPE);

        ArrayList<Fruits> playerCombination = new ArrayList<Fruits>();
        playerCombination.add(Fruits.BANANA);
        playerCombination.add(Fruits.PRUNE);
        playerCombination.add(Fruits.STRAWBERRY);
        playerCombination.add(Fruits.GRAPE);
        String[] verif= Functions.verification(combination,playerCombination);
        Toast.makeText(this, ""+ Arrays.toString(verif),
                Toast.LENGTH_SHORT).show();

        // data to populate the RecyclerView with
        ArrayList<String> animalNames = new ArrayList<>();
        animalNames.add("Horse");
        animalNames.add("Cow");
        animalNames.add("Camel");
        animalNames.add("Sheep");
        animalNames.add("Goat");

        // set up the RecyclerView
        RecyclerView recyclerView = findViewById(R.id.player_guess_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MyRecyclerViewAdapter(this, animalNames);
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);

        TextView mtv = findViewById(R.id.ScoreValue);
        mtv.setText("" + adapter.getItemCount());

        Button addPig = findViewById(R.id.guess_validate_btn);
        addPig.setOnClickListener(view -> {
            String item = "Pig";
            int insertIndex = 2;
            animalNames.add(insertIndex, item);
            adapter.notifyItemInserted(insertIndex);

            /** ajouter plusieurs elements
             * ArrayList<String> items = new ArrayList<>();
             * items.add("Pig");
             * items.add("Chicken");
             * items.add("Dog");
             * int insertIndex = 2;
             * data.addAll(insertIndex, items);
             * adapter.notifyItemRangeInserted(insertIndex, items.size());
             */

            /** supprimer de la ligne 2 à 4
             * int startIndex = 2; // inclusive
             * int endIndex = 4;   // exclusive
             * int count = endIndex - startIndex; // 2 items will be removed
             * data.subList(startIndex, endIndex).clear();
             * adapter.notifyItemRangeRemoved(startIndex, count);
             */

            /** Suupreesion de tous les éléments
             * data.clear();
             * adapter.notifyDataSetChanged();
             */
        });
    }


    @Override
    public void onItemClick(View view, int position) {
        Toast.makeText(this, "You clicked " + adapter.getItem(position) + " on row number " + position, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        //ArrayList of enum generated automatically
        ArrayList<Fruits> m_fruitsCombine = Functions.generateFruitCombination();
        Toast.makeText(this, ""+m_fruitsCombine.toString(), Toast.LENGTH_SHORT).show();

    }






}