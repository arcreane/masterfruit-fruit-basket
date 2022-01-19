package com.example.fruitsbasket;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.BitmapFactory;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import com.example.fruitsbasket.history_view_holder.MyRecyclerViewAdapter;
import java.util.Arrays;

public class GamePlay extends AppCompatActivity implements MyRecyclerViewAdapter.ItemClickListener {

    MyRecyclerViewAdapter adapter;
    ImageSet playerImageset;
    ImageView focus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_play);
        //Find the images where we put the context view menu
        ImageView Fruit_One = findViewById(R.id.Player_Fruit1);
        ImageView Fruit_Two = findViewById(R.id.Player_Fruit2);
        ImageView Fruit_Tree = findViewById(R.id.Player_Fruit3);
        ImageView Fruit_Four = findViewById(R.id.Player_Fruit4);

        registerForContextMenu(Fruit_One);
        registerForContextMenu(Fruit_Two);
        registerForContextMenu(Fruit_Tree);
        registerForContextMenu(Fruit_Four);

        // Get the user proposal onClick Validate Button returns an ImageSet for the recycle view
        Button validate = findViewById(R.id.guess_validate_btn);
        validate.setOnClickListener(action-> {
            playerImageset = new ImageSet();
            playerImageset.setImage1(BitmapFactory.decodeResource(getResources(), R.id.Player_Fruit1));
            playerImageset.setImage2(BitmapFactory.decodeResource(getResources(), R.id.Player_Fruit2));
            playerImageset.setImage3(BitmapFactory.decodeResource(getResources(), R.id.Player_Fruit3));
            playerImageset.setImage4(BitmapFactory.decodeResource(getResources(), R.id.Player_Fruit4));
            appendRecycler(playerImageset);
        });


        // Test de la fonction Verification de la combinaison player

        ArrayList<Fruits> combination = new ArrayList<Fruits>();
        combination.add(Fruits.BANANA);
        combination.add(Fruits.KIWI);
        combination.add(Fruits.PLUM);
        combination.add(Fruits.GRAPE);

        ArrayList<Fruits> playerCombination = new ArrayList<Fruits>();
        playerCombination.add(Fruits.BANANA);
        playerCombination.add(Fruits.PLUM);
        playerCombination.add(Fruits.STRAWBERRY);
        playerCombination.add(Fruits.GRAPE);
        String[] verif= Functions.verification(combination,playerCombination);
        Toast.makeText(this, ""+ Arrays.toString(verif),
                Toast.LENGTH_SHORT).show();

        // data to populate the RecyclerView with
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

    private void appendRecycler(ImageSet playerImageset) {

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


        ArrayList<StringBuilder> hints = Functions.generateHints(m_fruitsCombine);
        Toast.makeText(this, hints.get(0)+"\n"+hints.get(1), Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onCreateContextMenu(ContextMenu menu, View vue, ContextMenu.ContextMenuInfo menuInfo){
        super.onCreateContextMenu(menu, vue, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.player_input_context_menu, menu);
        menu.setHeaderTitle("Select The Fruit");
        focus = (ImageView) vue;
        Toast.makeText(this, ""+getClass().getSimpleName(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onContextItemSelected(MenuItem item){
        switch (item.getItemId()){
            case (R.id.StrawberryIm):
                focus.setImageResource(R.drawable.strawberry);
                return true;
            case (R.id.BananaIm):
                focus.setImageResource(R.drawable.banana);
                return true;
            case (R.id.KiwiIm):
                focus.setImageResource(R.drawable.kiwi);
                return true;
            case (R.id.OrangeIm):
                focus.setImageResource(R.drawable.orange);
                return true;
            case (R.id.RaspberryIm):
                focus.setImageResource(R.drawable.raspberry);
                return true;
            case (R.id.LemonIm):
                focus.setImageResource(R.drawable.lemon);
                return true;
            case (R.id.PlumIm):
                focus.setImageResource(R.drawable.plum);
                return true;
            case (R.id.GrapeIm):
                focus.setImageResource(R.drawable.grape);
                return true;
            case (R.id.EmptyIm):
                focus.setImageResource(R.drawable.empty);
                return true;
        }
        return false;
    }

}