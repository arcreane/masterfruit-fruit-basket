package com.example.fruitsbasket;

import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class Game {
    ArrayList<Fruits> listToFind;
    public static int difficulty = 4;

    /**
     * Function which returns an ArrayList of 4 different fruits
     *
     * @return ArrayList of enum
     */
    public static ArrayList<Fruits> generateFruitCombination() {
        ArrayList<Fruits> combination = new ArrayList<>();
        int count = 0;
            while (count < difficulty) {
                Fruits fruitToAdd = generateRandomFruit();
                if (!combination.contains(fruitToAdd)) {
                    combination.add(fruitToAdd);
                    count++;
                }
            }
        for (int i = 0; i < 4; i++) {
            System.out.println(i +": " + combination.get(i));
        }
        return combination;
        }

        /**
         * Generates a random element from an enum
         *
         * @return enum item
         */
        public static Fruits generateRandomFruit () {
            Fruits[] values = Fruits.values();
            int length = values.length -1;
            int randIndex = new Random().nextInt(length);
            return values[randIndex];
        }

    /**
     * Generates the two hints "Peelable" at index 0 and "with Seeds" at index 1
     * @param fruitCombination
     * @return An ArrayList of 2 String[]
     */
    public static ArrayList<String[]> generateHints(ArrayList<Fruits> fruitCombination){
        ArrayList<String[]> hint = new ArrayList<>();
        String[] hintPeel = new String[4];
        String[] hintSeed = new String[4];
        for (int i = 0; i < fruitCombination.size(); i++) {
            Fruits fruit = fruitCombination.get(i);
            hintPeel[i] = fruit.isM_bPeelable();
            hintSeed[i] = fruit.isM_bSeed();
        }
        hint.add(hintPeel);
        hint.add(hintSeed);
        return hint;
    }


    public static String[] verification(ArrayList < Fruits > combination, ArrayList < Fruits > proposal) {
        String[] evaluation = new String[4];
        int index = 0;
        int nbV=0;
        int nbX=0;

        for (int i = 0; i < 4 ; i++) {
            evaluation[i]="O";
        }
        for (int i = 0; i < 4; i++) {System.out.println(i +": " + proposal.get(i) + " / " + combination.get(i));}

        for (int i = 0; i < 4; i++) {
            Fruits playerFruit = proposal.get(i);
            for (int j = 0; j < 4; j++) {
                Fruits combinationFruits = combination.get(j);
                if (combinationFruits == playerFruit) {

                    if (i == j) {
                        nbV++;
                        break;
                    } else{
                        nbX++;
                        break;
                    }
                }

            }
        }
        for (int i = 0; i< nbV; i++) evaluation[i]="V";
        for (int i = nbV; i<nbX+nbV; i++) evaluation[i]="X";


        return evaluation;
    }
}


