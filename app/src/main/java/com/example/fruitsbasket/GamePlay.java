package com.example.fruitsbasket;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fruitsbasket.history_view_holder.MyRecyclerViewAdapter;

import java.util.ArrayList;

public class GamePlay extends AppCompatActivity implements MyRecyclerViewAdapter.ItemClickListener {

    MyRecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_play);

        // data to populate the RecyclerView with
//        ArrayList<Bitmap> setOfFruit = new ArrayList<>();
//        setOfFruit.add(BitmapFactory.decodeResource(getResources(),R.drawable.banana));
//        setOfFruit.add(BitmapFactory.decodeResource(getResources(),R.drawable.grape));
//        setOfFruit.add(BitmapFactory.decodeResource(getResources(),R.drawable.kiwi));
//        setOfFruit.add(BitmapFactory.decodeResource(getResources(),R.drawable.lemon));
//        setOfFruit.add(BitmapFactory.decodeResource(getResources(),R.drawable.orange));
//        setOfFruit.add(BitmapFactory.decodeResource(getResources(),R.drawable.plum));
//        setOfFruit.add(BitmapFactory.decodeResource(getResources(),R.drawable.raspberry));
//        setOfFruit.add(BitmapFactory.decodeResource(getResources(),R.drawable.strawberry));

        ImageSet imageset1 = new ImageSet();
        imageset1.setImage1(BitmapFactory.decodeResource(getResources(),R.drawable.banana));
        imageset1.setImage2(BitmapFactory.decodeResource(getResources(),R.drawable.grape));
        imageset1.setImage3(BitmapFactory.decodeResource(getResources(),R.drawable.kiwi));
        imageset1.setImage4(BitmapFactory.decodeResource(getResources(),R.drawable.lemon));
        ImageSet imageset2 = new ImageSet();
        imageset2.setImage1(BitmapFactory.decodeResource(getResources(),R.drawable.orange));
        imageset2.setImage2(BitmapFactory.decodeResource(getResources(),R.drawable.plum));
        imageset2.setImage3(BitmapFactory.decodeResource(getResources(),R.drawable.raspberry));
        imageset2.setImage4(BitmapFactory.decodeResource(getResources(),R.drawable.strawberry));
        ArrayList<ImageSet> setOfFruit = new ArrayList<>();
        setOfFruit.add(imageset1);
        setOfFruit.add(imageset2);

        // set up the RecyclerView
        RecyclerView recyclerView = findViewById(R.id.player_guess_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MyRecyclerViewAdapter(this, setOfFruit);
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);

        TextView mtv = findViewById(R.id.ScoreValue);
        mtv.setText("" + adapter.getItemCount());

//        Button addPig = findViewById(R.id.guess_validate_btn);
//        addPig.setOnClickListener(view -> {
//            Bitmap item = BitmapFactory.decodeResource(getResources(),R.drawable.banana);
//            int insertIndex = 2;
//            setOfFruit.add(insertIndex, item);
//            adapter.notifyItemInserted(insertIndex);

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

            /** Supression de tous les éléments
             * data.clear();
             * adapter.notifyDataSetChanged();
             */
//        });

    }


    @Override
    public void onItemClick(View view, int position) {
        Toast.makeText(this, "You clicked " + adapter.getItem(position) + " on row number " + position, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}