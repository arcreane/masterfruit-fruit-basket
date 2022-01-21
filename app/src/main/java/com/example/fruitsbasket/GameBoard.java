package com.example.fruitsbasket;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.BitmapFactory;

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

public class GameBoard extends AppCompatActivity implements MyRecyclerViewAdapter.ItemClickListener {

    MyRecyclerViewAdapter adapter;
    ImageSet playerImageset;
    ImageView focus;
    ArrayList<ImageSet> setOfFruit = new ArrayList<>();
    ArrayList<Fruits> GameCombination = new ArrayList<>();
    ArrayList<Fruits> PlayerCombination = new ArrayList<>();
    String[] CombinationCheck = new String[4];
    int hintdeduction = 1;
    int hintUsed=0;

    ArrayList<String[]> seedPeelHints;
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

        GameCombination = Game.generateFruitCombination();
        for (int i = 0; i < 4; i++) {PlayerCombination.add(Fruits.EMPTY);}
        Toast.makeText(this, ""+ Arrays.toString(Game.verification(GameCombination,PlayerCombination)),Toast.LENGTH_SHORT).show();
        seedPeelHints=Game.generateHints(GameCombination);


        // Get the user proposal onClick Validate Button returns an ImageSet for the recycle view
        Button validate = findViewById(R.id.guess_validate_btn);
        validate.setOnClickListener(action-> {


            if(PlayerCombination.contains(Fruits.EMPTY)){
               alertValidateEmpty();
               System.out.println(PlayerCombination);
            }else{
                appendRecycler(playerImageset);
                counter--;
                updateCounter();
                TextView counterDisplay = findViewById(R.id.triesLeft);
                counterDisplay.setText("" + counter);
                CombinationCheck = Game.verification(GameCombination, PlayerCombination);
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

    private void updateCounter() {
        TextView counterDisplay = findViewById(R.id.triesLeft);
        counterDisplay.setText("" + counter);



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
                updateSetOfFruit(f, id);
                return true;
            }
        }
        return false;
    }

    //create hintMenu.
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        //create inflater menu for instance a menu  XML on object Menu.
        MenuInflater inflater = getMenuInflater();
        //instantiation menu XML specified  objet Menu
        inflater.inflate(R.menu.hint_option_menu, menu);
        return true;

    }


    //action for the click
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (hintdeduction < 3) {
            //limitid used Hint if you have 2 tries or 3tries
            if ((hintUsed == 1 && counter <= 2) || (hintUsed == 2 && counter <= 3)) {
                Toast.makeText(this, "Not enough tries left", Toast.LENGTH_SHORT).show();

            } else {



                //we look who item is cliqued  and activate this action.
                switch (item.getItemId()) {
                    case R.id.indiceSeed:
                        //we look it's a first or second Hint
                        if (hintUsed == 1) {
                            Toast.makeText(this, "hint already used", Toast.LENGTH_SHORT).show();
                        } else {
                            LinearLayout mHintSeed = (LinearLayout) findViewById(R.id.hintSeed);
                            mHintSeed.setVisibility(LinearLayout.VISIBLE);
                            Toast.makeText(this, "You take a first HINT.", Toast.LENGTH_SHORT).show();
                            //We deduct -2 at tries if you select a first hint. deduct -3 at tries if you
                            //select a second hint.
                            hintdeduction++;
                            hintUsed = 1;
                            counter -= hintdeduction;
                            //add hint true or false  for hint seed
                            TextView tv1=findViewById(R.id.hintSeed1);
                            tv1.setText(seedPeelHints.get(1)[0]);
                            TextView tv2=findViewById(R.id.hintSeed2);
                            tv2.setText(seedPeelHints.get(1)[1]);
                            TextView tv3=findViewById(R.id.hintSeed3);
                            tv3.setText(seedPeelHints.get(1)[2]);
                            TextView tv4=findViewById(R.id.hintSeed4);
                            tv4.setText(seedPeelHints.get(1)[3]);
                            Toast.makeText(this, "You loose " + hintdeduction + " tries you have : " + counter, Toast.LENGTH_SHORT).show();
                        }
                        break;


                    case R.id.indicePeel:

                        if (hintUsed == 2) {
                            Toast.makeText(this, "Hint already used", Toast.LENGTH_SHORT).show();
                        } else {
                            LinearLayout mHintPeel = (LinearLayout) findViewById(R.id.hintPeel);
                            mHintPeel.setVisibility(LinearLayout.VISIBLE);
                            Toast.makeText(this, "You take Second HINT.", Toast.LENGTH_SHORT).show();
                            hintdeduction++;
                            hintUsed = 2;
                            counter -= hintdeduction;
                            //add hint true or false  for hint
                            TextView tv1=findViewById(R.id.hintPeel1);
                            tv1.setText(seedPeelHints.get(0)[0]);
                            TextView tv2=findViewById(R.id.hintPeel2);
                            tv2.setText(seedPeelHints.get(0)[1]);
                            TextView tv3=findViewById(R.id.hintPeel3);
                            tv3.setText(seedPeelHints.get(0)[2]);
                            TextView tv4=findViewById(R.id.hintPeel4);
                            tv4.setText(seedPeelHints.get(0)[3]);
                            Toast.makeText(this, "vous avez perdu " + hintdeduction + " essais il vous reste : " + counter, Toast.LENGTH_SHORT).show();


                        }
                }
                updateCounter();
            }
            } else{
                Toast.makeText(this, "You already used alL Hints", Toast.LENGTH_SHORT).show();
            }




        return false;
    }

    /**
     * Shows a Dialog box when Player validates his board with empty spaces
     */
    private void alertValidateEmpty(){
        AlertDialog alertDialog = new AlertDialog.Builder(GameBoard.this).create();
        alertDialog.setTitle("Stop!");
        alertDialog.setMessage("You can't submit whit an empty space!");
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                (dialog, which) -> dialog.dismiss());
        alertDialog.show();
    }




    private void updateSetOfFruit(Fruits fruit, int id) {
        boolean chosen = false;
        for (int i = 0; i < 4; i++) {
            if(chosenFruit[i] == fruit.getFruitIcon()) {chosen = true;}
        }
        if(chosen && fruit.getFruitIcon() != Fruits.EMPTY.getFruitIcon()) Toast.makeText(this, "Already chosen", Toast.LENGTH_LONG).show();
        else {
            chosenFruit[id] = fruit.getFruitIcon();
            focus.setImageResource(fruit.getFruitIcon());
            playerImageset.setImage(BitmapFactory.decodeResource(getResources(), fruit.getFruitIcon()), id);
            PlayerCombination.set(id, fruit);
        }
    }

}