package com.example.fruitsbasket;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.graphics.BitmapFactory;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import com.example.fruitsbasket.history_view_holder.MyRecyclerViewAdapter;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class GamePlay extends AppCompatActivity implements MyRecyclerViewAdapter.ItemClickListener {

    MyRecyclerViewAdapter adapter;
    ImageSet playerImageset;
    ImageView focus;
    ArrayList<ImageSet> setOfFruit = new ArrayList<>();
    ArrayList<Fruits> GameCombination = new ArrayList<>();
    ArrayList<Fruits> PlayerCombination = new ArrayList<>();
    String[] CombinationCheck = new String[4];

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

        GameCombination = Functions.generateFruitCombination();
        for (int i = 0; i < 4; i++) {PlayerCombination.add(Fruits.EMPTY);}
        Toast.makeText(this, ""+ Arrays.toString(Functions.verification(GameCombination,PlayerCombination)),Toast.LENGTH_SHORT).show();


        // Get the user proposal onClick Validate Button returns an ImageSet for the recycle view
        Button validate = findViewById(R.id.guess_validate_btn);
        validate.setOnClickListener(action-> {
            if(PlayerCombination.contains(Fruits.EMPTY)){
               alertValidateEmpty();
               System.out.println(PlayerCombination);
            }else{
                appendRecycler(playerImageset);
                counter--;
                TextView counterDisplay = findViewById(R.id.triesLeft);
                counterDisplay.setText("" + counter);
                CombinationCheck = Functions.verification(GameCombination, PlayerCombination);
                playerImageset.setCheck(CombinationCheck);
            }

            Toast.makeText(this, ""+ Arrays.toString(playerImageset.getCheck()),Toast.LENGTH_SHORT).show();
        });



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
    public void onCreateContextMenu(ContextMenu menu, View vue, ContextMenu.ContextMenuInfo menuInfo){
        super.onCreateContextMenu(menu, vue, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.player_input_context_menu, menu);
        menu.setHeaderTitle("Select The Fruit");
        focus = (ImageView) vue;
    }

    @Override
    public boolean onContextItemSelected(MenuItem item){
        int id = 0;
        switch (focus.getId()){
            case R.id.Player_Fruit1: id = 0; break;
            case R.id.Player_Fruit2: id = 1; break;
            case R.id.Player_Fruit3: id = 2; break;
            case R.id.Player_Fruit4: id = 3; break;
        }

        //Which item has been selected ?
        int selectedFruit = item.getItemId();
        //Update PlayerCombination and array for recycler view
        for(Fruits f : Fruits.values()) {
            if(f.getMenuiId() == selectedFruit) {
                updateSetOfFruit(f.getFruitIcon(), id);
                PlayerCombination.set(id, f);
                return true;
            }
        }
        return false;
    }

    //create hintMenu.
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        //Création d'un MenuInflater qui va permettre d'instancier un Menu XML en un objet Menu
        MenuInflater inflater = getMenuInflater();
        //Instanciation du menu XML spécifié en un objet Menu
        inflater.inflate(R.menu.hint_option_menu, menu);
        return true;

    }

    //Méthode qui se déclenchera au clic sur un item
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //On regarde quel item a été cliqué grâce à  son id et on déclenche une action
        switch (item.getItemId()) {
            case R.id.indiceNumerbe1:
                LinearLayout mHintSeed = (LinearLayout) findViewById(R.id.hintSeed);
                mHintSeed.setVisibility(LinearLayout.VISIBLE);
                Toast.makeText(this, "You take a first HINT.", Toast.LENGTH_SHORT).show();
                break;

            case R.id.indiceNumerbe2:
                LinearLayout mHintPeel = (LinearLayout) findViewById(R.id.hintPeel);
                mHintPeel.setVisibility(LinearLayout.VISIBLE);
                Toast.makeText(this, "You take Second HINT.", Toast.LENGTH_SHORT).show();
                break;

        }

        return false;
    }

    /**
     * Shows a Dialog box when Player validates his board with empty spaces
     */
    private void alertValidateEmpty(){
        AlertDialog alertDialog = new AlertDialog.Builder(GamePlay.this).create();
        alertDialog.setTitle("Stop!");
        alertDialog.setMessage("You can't submit whit an empty space!");
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                (dialog, which) -> dialog.dismiss());
        alertDialog.show();
    }


    private void updateSetOfFruit(int fruit, int id) {
        boolean chosen = false;
        for (int i = 0; i < 4; i++) {
            if(chosenFruit[i] == fruit) {chosen = true;}
        }
        if(chosen && fruit != Fruits.EMPTY.getFruitIcon()) Toast.makeText(this, "Already chosen", Toast.LENGTH_LONG).show();
        else {
            chosenFruit[id] = fruit;
            focus.setImageResource(fruit);
            playerImageset.setImage(BitmapFactory.decodeResource(getResources(), fruit), id);
        }
    }


}