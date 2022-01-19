package com.example.fruitsbasket;

import java.util.ArrayList;
import java.util.Random;

public class Functions {
    ArrayList<Fruits> listToFind;

    /**
     * Function which returns an ArrayList of 4 different fruits
     * @return ArrayList of enum
     */
    public static ArrayList<Fruits> generateFruitCombination(){
        ArrayList<Fruits> combination = new ArrayList<>();
        int count = 0;

        while (count<4) {
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

}
