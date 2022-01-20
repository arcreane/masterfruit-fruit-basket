package com.example.fruitsbasket;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

public class GamePlay extends AppCompatActivity implements MyRecyclerViewAdapter.ItemClickListener {

    MyRecyclerViewAdapter adapter;
    ImageSet playerImageset;
    ImageView focus;
    View viewClicked;
    ArrayList<ImageSet> setOfFruit = new ArrayList<>();
    ArrayList<Fruits> GameCombination = new ArrayList<>();
    ArrayList<Fruits> PlayerCombination = new ArrayList<>();
    public static String[] CombinationCheck = new String[4];

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
            appendRecycler(playerImageset);
            counter--;
            TextView counterDisplay = findViewById(R.id.triesLeft);
            counterDisplay.setText("" + counter);
            CombinationCheck = Functions.verification(GameCombination, PlayerCombination);
            Toast.makeText(this, ""+ Arrays.toString(CombinationCheck),Toast.LENGTH_SHORT).show();
        });

        // Test de la fonction Verification de la combinaison player
//        ArrayList<Fruits> combination = new ArrayList<Fruits>();
//        combination.add(Fruits.BANANA);
//        combination.add(Fruits.KIWI);
//        combination.add(Fruits.PLUM);
//        combination.add(Fruits.GRAPE);
//
//
//        GameCombination.add(Fruits.BANANA);
//        GameCombination.add(Fruits.PLUM);
//        GameCombination.add(Fruits.STRAWBERRY);
//        GameCombination.add(Fruits.GRAPE);
//        String[] verif= Functions.verification(GameCombination, PlayerCombination);
//        Toast.makeText(this, ""+ Arrays.toString(verif),
//                Toast.LENGTH_SHORT).show();

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
        viewClicked = vue;
        Toast.makeText(this, ""+getClass().getSimpleName(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onContextItemSelected(MenuItem item){
        int id = 0;
        switch (viewClicked.getId()){
            case R.id.Player_Fruit1: id = 0; break;
            case R.id.Player_Fruit2: id = 1; break;
            case R.id.Player_Fruit3: id = 2; break;
            case R.id.Player_Fruit4: id = 3; break;
        }
        ContextMenu.ContextMenuInfo toto = item.getMenuInfo();

        switch (item.getItemId()){
            case (R.id.StrawberryIm): updateSetOfFruit(R.drawable.strawberry, id);
                PlayerCombination.set(id, Fruits.STRAWBERRY);
                return true;
            case (R.id.BananaIm): updateSetOfFruit(R.drawable.banana, id);
                PlayerCombination.set(id, Fruits.BANANA);
                return true;
            case (R.id.KiwiIm): updateSetOfFruit(R.drawable.kiwi, id);
                PlayerCombination.set(id, Fruits.KIWI);
                return true;
            case (R.id.OrangeIm): updateSetOfFruit(R.drawable.orange, id);
                PlayerCombination.set(id, Fruits.ORANGE);
                return true;
            case (R.id.RaspberryIm): updateSetOfFruit(R.drawable.raspberry, id);
                PlayerCombination.set(id, Fruits.RASPBERRY);
                return true;
            case (R.id.LemonIm): updateSetOfFruit(R.drawable.lemon, id);
                PlayerCombination.set(id, Fruits.LEMON);
                return true;
            case (R.id.PlumIm): updateSetOfFruit(R.drawable.plum, id);
                PlayerCombination.set(id, Fruits.PLUM);
                return true;
            case (R.id.GrapeIm): updateSetOfFruit(R.drawable.grape, id);
                PlayerCombination.set(id, Fruits.GRAPE);
                return true;
            case (R.id.EmptyIm): updateSetOfFruit(R.drawable.empty, id);
                PlayerCombination.set(id, Fruits.EMPTY);
                return true;
        }
        return false;
    }


    //create hintMenu.
    //Méthode qui se déclenchera lorsque vous appuierez sur le bouton menu du téléphone
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

            case R.id.indiceNumerbe2:
                LinearLayout mHintPeel = (LinearLayout) findViewById(R.id.hintPeel);
                mHintPeel.setVisibility(LinearLayout.VISIBLE);
                Toast.makeText(this, "You take Second HINT.", Toast.LENGTH_SHORT).show();


        }

        return false;
    }

    void chooseHint (int indice){
        String indiceOuNon="";
        if(indice==1){
            if(!hint1){
                tries-=2;
                System.out.println("You have chosen to have one clue against two attempts you have"+tries+"tries");
            } else {
                System.out.println("Hint already given");
            }
        }else if (indice==2) {
            if(!hint2){
                tries-=3;
                System.out.println("You have chosen to have one clue against two attempts you have"+tries+"tries");
            } else {
                System.out.println("Hint already given");
            }
        }else{
            System.out.println("Indice value not manage");
        }
    }
    int tries;
    boolean hint1;
    boolean hint2;





    private void updateSetOfFruit(int fruit, int id) {
        boolean chosen = false;
        for (int i = 0; i < 4; i++) {
            if(chosenFruit[i] == fruit) {chosen = true;}
        }
        if(chosen) Toast.makeText(this, "Already chosen", Toast.LENGTH_LONG).show();
        else {
            chosenFruit[id] = fruit;
            focus.setImageResource(fruit);
            if (id != 0)
                playerImageset.setImage(BitmapFactory.decodeResource(getResources(), fruit), id);
        }
    }


}