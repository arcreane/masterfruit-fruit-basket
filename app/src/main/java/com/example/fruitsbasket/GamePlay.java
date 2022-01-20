package com.example.fruitsbasket;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.BitmapFactory;

import android.graphics.drawable.Drawable;
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
    View viewClicked;
    ArrayList<ImageSet> setOfFruit = new ArrayList<>();
    ArrayList<Fruits> playerCombination = new ArrayList<>();
    ArrayList<Fruits> playerTarget = new ArrayList<>();
    public static String[] SolutionHints = new String[4];

    int counter = 10;
    int[] chosenFruit = new int[4];

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
        playerImageset = new ImageSet();

        playerCombination = Functions.generateFruitCombination();
        ArrayList<String[]> hints = Functions.generateHints(playerCombination);
        Toast.makeText(this, Arrays.toString(hints.get(0))+"\n"+Arrays.toString(hints.get(1)), Toast.LENGTH_SHORT).show();


        for (int i = 0; i < 4; i++) {playerTarget.add(Fruits.EMPTY);}
        Toast.makeText(this, ""+ Arrays.toString(Functions.verification(playerCombination,playerTarget)),Toast.LENGTH_SHORT).show();

        // Get the user proposal onClick Validate Button returns an ImageSet for the recycle view
        Button validate = findViewById(R.id.guess_validate_btn);
        validate.setOnClickListener(action-> {
            appendRecycler(playerImageset);
            counter--;
            TextView counterDisplay = findViewById(R.id.triesLeft);
            counterDisplay.setText("" + counter);
            SolutionHints = Functions.verification(playerCombination, playerTarget);
            Toast.makeText(this, ""+ Arrays.toString(SolutionHints),Toast.LENGTH_SHORT).show();
        });




        // data to populate the RecyclerView with
//        ImageSet imageset1 = new ImageSet();
//        imageset1.setImage1(BitmapFactory.decodeResource(getResources(),R.drawable.banana));
//        imageset1.setImage2(BitmapFactory.decodeResource(getResources(),R.drawable.grape));
//        imageset1.setImage3(BitmapFactory.decodeResource(getResources(),R.drawable.kiwi));
//        imageset1.setImage4(BitmapFactory.decodeResource(getResources(),R.drawable.lemon));
//        ImageSet imageset2 = new ImageSet();
//        imageset2.setImage1(BitmapFactory.decodeResource(getResources(),R.drawable.orange));
//        imageset2.setImage2(BitmapFactory.decodeResource(getResources(),R.drawable.plum));
//        imageset2.setImage3(BitmapFactory.decodeResource(getResources(),R.drawable.raspberry));
//        imageset2.setImage4(BitmapFactory.decodeResource(getResources(),R.drawable.strawberry));
//        setOfFruit.add(imageset1);
//        setOfFruit.add(imageset2);

        // set up the RecyclerView
        RecyclerView recyclerView = findViewById(R.id.player_guess_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MyRecyclerViewAdapter(this, setOfFruit);
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);

        TextView mtv = findViewById(R.id.ScoreValue);
        mtv.setText("" + adapter.getItemCount());
    }

    private void appendRecycler(ImageSet playerImageset) {
            int insertIndex = 0;
            if(playerImageset == null){
                Toast.makeText(this, "set is null", Toast.LENGTH_LONG).show();
            }else {
                setOfFruit.add(insertIndex, playerImageset);
                adapter.notifyItemInserted(insertIndex);
//            adapter.notifyDataSetChanged(); // alternate to be checked
            }
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


        ArrayList<String[]> hints = Functions.generateHints(m_fruitsCombine);
        Toast.makeText(this, hints.get(0).toString()+"\n"+hints.get(1).toString(), Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onCreateContextMenu(ContextMenu menu, View vue, ContextMenu.ContextMenuInfo menuInfo){
        super.onCreateContextMenu(menu, vue, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.player_input_context_menu, menu);
        menu.setHeaderTitle("Select The Fruit");
        focus = (ImageView) vue;
        viewClicked = vue;
        Toast.makeText(this, ""+getClass().getSimpleName(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onContextItemSelected(MenuItem item){
        int id = 0;
        switch (viewClicked.getId()){
            case R.id.Player_Fruit1: id = 1; break;
            case R.id.Player_Fruit2: id = 2; break;
            case R.id.Player_Fruit3: id = 3; break;
            case R.id.Player_Fruit4: id = 4; break;
        }
        ContextMenu.ContextMenuInfo toto = item.getMenuInfo();

        switch (item.getItemId()){

            case (R.id.StrawberryIm): updateSetOfFruit(R.drawable.strawberry, id);
                playerTarget.set(id-1, Fruits.STRAWBERRY);
                return true;
            case (R.id.BananaIm): updateSetOfFruit(R.drawable.banana, id);
                playerTarget.set(id-1, Fruits.BANANA);
                return true;
            case (R.id.KiwiIm): updateSetOfFruit(R.drawable.kiwi, id);
                playerTarget.set(id-1, Fruits.KIWI);
                return true;
            case (R.id.OrangeIm): updateSetOfFruit(R.drawable.orange, id);
                playerTarget.set(id-1, Fruits.ORANGE);
                return true;
            case (R.id.RaspberryIm): updateSetOfFruit(R.drawable.raspberry, id);
                playerTarget.set(id-1, Fruits.RASPBERRY);
                return true;
            case (R.id.LemonIm): updateSetOfFruit(R.drawable.lemon, id);
                playerTarget.set(id-1, Fruits.LEMON);
                return true;
            case (R.id.PlumIm): updateSetOfFruit(R.drawable.plum, id);
                playerTarget.set(id-1, Fruits.PLUM);
                return true;
            case (R.id.GrapeIm): updateSetOfFruit(R.drawable.grape, id);
                playerTarget.set(id-1, Fruits.GRAPE);
                return true;
            case (R.id.EmptyIm): updateSetOfFruit(R.drawable.empty, id);
                playerTarget.set(id-1, Fruits.EMPTY);
                return true;
        }
        return false;
    }

    private void updateSetOfFruit(int fruit, int id) {
        boolean chosen = false;
        for (int i = 0; i < 4; i++) {
            if(chosenFruit[i] == fruit) {chosen = true;}
        }
        if(chosen) Toast.makeText(this, "Already chosen", Toast.LENGTH_LONG).show();
        else {
            chosenFruit[id - 1] = fruit;
            focus.setImageResource(fruit);
            if (id != 0)
                playerImageset.setImage(BitmapFactory.decodeResource(getResources(), fruit), id);
        }
    }


}