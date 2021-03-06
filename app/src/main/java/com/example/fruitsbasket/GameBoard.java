package com.example.fruitsbasket;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fruitsbasket.history_view_holder.MyRecyclerViewAdapter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public class GameBoard extends AppCompatActivity implements MyRecyclerViewAdapter.ItemClickListener {

    MyRecyclerViewAdapter adapter;
    ImageSet playerImageset;
    ImageView focus;
//    ArrayList<ImageSet> setOfFruit = new ArrayList<>();
    ArrayList<ImageSet> setOfFruit;
    ArrayList<Fruits> GameCombination = new ArrayList<>();
    ArrayList<Fruits> PlayerCombination;
    String[] CombinationCheck = new String[4];
    int hintdeduction;
    int hintUsed;
    ArrayList<String[]> seedPeelHints;
    int counter;
    int[] chosenFruit;

    public Player currentPlayer;

    Context context = this;


    @Override
    protected void onStop() {
        super.onStop();
        //ShowAlertBox("Warning", "Are you sure sure you want to quit ?" );

        Log.d("Verification: ", "onStop event");
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_board);
        //Find the images where we put the context view menu


        BoardInit();
        RestartGame();


        /** Button VALIDATE
         * Get the user proposal onClick Validate Button returns an ImageSet for the recycle view
         */

        // Get the user proposal onClick Validate Button returns an ImageSet for the recycle view

        Button validate = findViewById(R.id.guess_validate_btn);
        validate.setOnClickListener(action-> {

            if(PlayerCombination.contains(Fruits.EMPTY)){
               alertValidateEmpty();
//               System.out.println(PlayerCombination);
            }else{
                appendRecycler(playerImageset);
                counter--;
                updateCounter();
                TextView counterDisplay = findViewById(R.id.triesLeft);
                counterDisplay.setText("" + counter);
                CombinationCheck = Game.verification(GameCombination, PlayerCombination);
                playerImageset.setCheck(CombinationCheck);
                checkGameStatus();
            }

            //Toast.makeText(this, ""+ Arrays.toString(playerImageset.getCheck()),Toast.LENGTH_SHORT).show();
        });

        // Resign button : quit the game when needed
        Button BtnResign = findViewById(R.id.cancel_btn);
        BtnResign.setOnClickListener(action-> {
            ShowAlertBox("Sorry to see you quit", "Do you want to save your score ?");
            if(currentPlayer.getPlayer_name() != null|| currentPlayer.getScore() == 0){
                Toast.makeText(this,"saving " + currentPlayer.getPlayer_name(), Toast.LENGTH_SHORT).show();
            }else Toast.makeText(this, "See you soon", Toast.LENGTH_SHORT).show();

        });
    }

    /*
    Initialise the game parameter from beginning (new game)
     */
    private void RestartGame(){
        playerImageset = new ImageSet();
        currentPlayer=  new Player();
        currentPlayer.setScore(0);
        StartNewSet();

        TextView mtv = findViewById(R.id.ScoreValue);
        mtv.setText("0");

        Toast.makeText(this, "Welcome " + currentPlayer.getPlayer_name() + " you got " + currentPlayer.getScore() + " points", Toast.LENGTH_LONG).show();
    }

    // initialise the game board
    private void BoardInit(){
        ImageView Fruit_One = findViewById(R.id.Player_Fruit1);
        ImageView Fruit_Two = findViewById(R.id.Player_Fruit2);
        ImageView Fruit_Tree = findViewById(R.id.Player_Fruit3);
        ImageView Fruit_Four = findViewById(R.id.Player_Fruit4);

        registerForContextMenu(Fruit_One);
        registerForContextMenu(Fruit_Two);
        registerForContextMenu(Fruit_Tree);
        registerForContextMenu(Fruit_Four);
    }

    /*
    Initialise the game parameter from for a new set (new set)
     */
    private void StartNewSet(){
        ImageView Fruit_One = findViewById(R.id.Player_Fruit1);
        Fruit_One.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.empty));
        ImageView Fruit_Two = findViewById(R.id.Player_Fruit2);
        Fruit_Two.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.empty));
        ImageView Fruit_Three = findViewById(R.id.Player_Fruit3);
        Fruit_Three.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.empty));
        ImageView Fruit_Four = findViewById(R.id.Player_Fruit4);
        Fruit_Four.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.empty));
        GameCombination = Game.generateFruitCombination();

        seedPeelHints = Game.generateHints(GameCombination);
        PlayerCombination = new ArrayList<>();
        for (int i = 0; i < 4; i++) {PlayerCombination.add(Fruits.EMPTY);}

        //Toast.makeText(this, ""+ Arrays.toString(Game.verification(GameCombination,PlayerCombination)),Toast.LENGTH_SHORT).show();
        hintdeduction = 1;
        hintUsed=0;
        counter = 10;
        TextView mtv= findViewById(R.id.triesLeft);
        mtv.setText("" + counter);
        chosenFruit = new int[4];
        setOfFruit = new ArrayList<>();
        Toast.makeText(this, "Hello " + currentPlayer.getPlayer_name() + " you got " + currentPlayer.getScore() + " points", Toast.LENGTH_LONG).show();

        // set up the RecyclerView
        RecyclerView recyclerView = findViewById(R.id.player_guess_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MyRecyclerViewAdapter(this, setOfFruit);
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);
    }

    // update counter - from Validate button or from hints selected
    private void updateCounter() {
        TextView counterDisplay = findViewById(R.id.triesLeft);
        counterDisplay.setText("" + counter);
    }

    // check the game status each 'validate' turn, update parameters and check if won/lost
    private void checkGameStatus() {
        int Vcount = 0;
        String typicalMessage = "Choose your next step :\n- 'Start new set' to continue the current game \n- 'Restart game' to restart \n-  'Quit' to close";
        for (int i = 0; i <4; i++) {
            if(CombinationCheck[i].equals("V")) Vcount++;
        }
        if(Vcount == 4){
            currentPlayer.addGames_won();
            TerminateCurrentGame("Set is won", "Congratulations. " + typicalMessage);
        }else if(counter <= 0){
            TerminateCurrentGame("You loose", "Try again !" + typicalMessage);
        }
        Toast.makeText(this, "name: " + currentPlayer.getPlayer_name() + " / " + currentPlayer.getScore(), Toast.LENGTH_SHORT).show();
    }

    // show alert box when resigning button
    private void ShowAlertBox(String title, String message) {
        AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setTitle(title);
        alertDialog.setMessage(message);
        alertDialog.setCanceledOnTouchOutside(false);
        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Cancel",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        navigateUpTo(new Intent(getBaseContext(), MainActivity.class));
                    }
                });
        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        if( currentPlayer.getScore() != 0){
                            getPlayerName();
                            navigateUpTo(new Intent(getBaseContext(), MainActivity.class));
                        }
                    }
                });
        alertDialog.show();
    }

    // Terminate the game: alerts, storing player name/score if validated
    private void TerminateCurrentGame(String title, String message) {

        int score = currentPlayer.getScore() + counter;
        TextView ScoreTxt = findViewById(R.id.ScoreValue);
        ScoreTxt.setText("" + score);
        currentPlayer.setScore(score);
        AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setTitle(title);
        alertDialog.setIcon(android.R.drawable.ic_dialog_alert);
        alertDialog.setMessage(message);
        alertDialog.setCanceledOnTouchOutside(false);



        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "START A NEW SET",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        StartNewSet();
                        Intent switchToActivity = new Intent(GameBoard.this, GameBoard.class);
                        switchToActivity.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                        startActivity(switchToActivity);
                    }
                });
        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "RESTART GAME",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        getPlayerName();
                        //Register score in BDD
                        System.out.println("Player name"+currentPlayer.getPlayer_name());
                        RestartGame();
                    }
                });

        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "QUIT GAME",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        navigateUpTo(new Intent(getBaseContext(), MainActivity.class));
//                        Intent homeIntent = new Intent(Intent.ACTION_MAIN);
//                        homeIntent.addCategory( Intent.CATEGORY_HOME );
//                        homeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                        startActivity(homeIntent);
//                        System.exit(1);
                    }
                });

        alertDialog.show();
        System.out.println("Dans terminate game" +currentPlayer.getPlayer_name());

    }


    // set the player name and store player def into SQLite
    private void getPlayerName() {
        if(currentPlayer.getPlayer_name() == null) {
            System.out.println("au d??but de getPalyerScore"+currentPlayer.getScore());
            getPlayerNameDialog(new OnSubmitBtnClick() {
                @Override
                public void onClick(String PlayerName) {
                    if(PlayerName.equals("skip")){
                        Toast.makeText(GameBoard.this, "No name saved", Toast.LENGTH_SHORT).show();
                    }else {
                        currentPlayer.setPlayer_name(PlayerName);
                        System.out.println("name = " + currentPlayer.getPlayer_name());
                        System.out.println(currentPlayer.getScore());
                        RegisteredScore r_score = new RegisteredScore(currentPlayer.getPlayer_name(), currentPlayer.getScore(), currentPlayer.nbGames_won);
                        if(r_score.getName() != null)
                            Scores.addScoreToBDD(context, r_score);
                        Toast.makeText(GameBoard.this, "Saving " + PlayerName + " score ", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    // add a try to the recycler list
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


    // display the item list selected - for future use
    @Override
    public void onItemClick(View view, int position) {
        Toast.makeText(this, "You clicked " + adapter.getItem(position) + " on row number " + position, Toast.LENGTH_SHORT).show();
    }

    // menu for selecting a fruit - create
    @Override
    public void onCreateContextMenu(ContextMenu menu, View vue, ContextMenu.ContextMenuInfo menuInfo){
        super.onCreateContextMenu(menu, vue, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.player_input_context_menu, menu);
        menu.setHeaderTitle("Select The Fruit");
        focus = (ImageView) vue;
    }

    // menu for selecting a fruit - select item
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

            //limited used Hint if you have 2 tries or 3tries
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
        alertDialog.setMessage("You can't submit your guess with an empty space!");
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                (dialog, which) -> dialog.dismiss());
        alertDialog.show();
    }

    // once a fruit is selected, update of variables and display
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




/**
 * Dialog to set a name for storing player score
 *
 */
public void getPlayerNameDialog(OnSubmitBtnClick submitBtnClick) {
    AlertDialog.Builder alertDialog = new AlertDialog.Builder(GameBoard.this);
    final View customLayout = GameBoard.this.getLayoutInflater().inflate(R.layout.player_dialog, null);
    alertDialog.setView(customLayout);
    AlertDialog alert = alertDialog.create();
    alert.setCancelable(false);
    alert.setCanceledOnTouchOutside(false);
    EditText editText = customLayout.findViewById(R.id.nameinputtext);
    TextView ScoreText = customLayout.findViewById(R.id.scoreDisplay_text);
    ScoreText.setText("SCORE: " + currentPlayer.getScore());
    Button OKBtn = customLayout.findViewById(R.id.okbutton);
    Button CancelBtn = customLayout.findViewById(R.id.Cancelbutton);
    TextView tvBody = customLayout.findViewById(R.id.title_text);

    OKBtn.setOnClickListener(action -> {
        String userText = editText.getText().toString();
        alert.dismiss();
        submitBtnClick.onClick(userText);
    });



    CancelBtn.setOnClickListener(action -> {
        alert.dismiss();
        submitBtnClick.onClick("skip");
    });
    try{
        alert.show();
    }catch (Exception e){
        System.out.println("Exception: " +  e.getMessage());
    }
}
}