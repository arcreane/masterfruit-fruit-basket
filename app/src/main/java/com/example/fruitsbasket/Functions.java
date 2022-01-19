package com.example.fruitsbasket;

import java.util.ArrayList;
import java.util.Random;

public class Functions {
    ArrayList<Fruits> listToFind;
    public static int difficulty = 4;

    /**
     * Function which returns an ArrayList of 4 different fruits
     * @return ArrayList of enum
     */
    public static ArrayList<Fruits> generateFruitCombination(){
        ArrayList<Fruits> combination = new ArrayList<>();
        int count = 0;

        while (count < difficulty) {
            Fruits fruitToAdd = generateRandomFruit();
            if(!combination.contains(fruitToAdd)){
                combination.add(fruitToAdd);
                count++;
            }
        }
        return combination;
    }

    /**
     * Generates a random element from an enum
     * @return enum item
     */
    public static Fruits generateRandomFruit() {
        Fruits[] values = Fruits.values();
        int length = values.length;
        int randIndex = new Random().nextInt(length);
        return values[randIndex];
    }

    /**
     * Generates the two hints
     * @param fruitCombination
     * @return
     */
    public static ArrayList<String> generateHints(ArrayList<Fruits> fruitCombination){
        ArrayList<String> hint = new ArrayList<>();
        StringBuilder hintPeel = new StringBuilder();
        StringBuilder hintSeed = new StringBuilder();
        for (int i = 0; i < fruitCombination.size(); i++) {
            Fruits fruit = (Fruits) fruitCombination.get(i);
            if(fruit.isM_bPeelable()){
                hintPeel.append("true ");
            } else {
                hintPeel.append("false ");
            }
            if(fruit.isM_bSeed()){
                hintSeed.append("true ");
            } else {
                hintPeel.append("false ");
            }
        }
        return hint;
    }

}
