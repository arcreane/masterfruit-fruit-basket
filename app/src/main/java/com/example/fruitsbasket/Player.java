package com.example.fruitsbasket;

import java.util.ArrayList;

public class Player {
    String player_name;
    int nbGames_won;
    int point_Player;
    int tries;
    int score;
    boolean hint1;
    boolean hint2;

    //Add point to the player
    public void winManagement(int p_player_point) {
        point_Player+= p_player_point;
        System.out.println(player_name +
                "Your find all fruit ! You have " + point_Player +"");
    }

    // if the player wants hint in exchange of tries
    void chooseHint (int indice){
        String HintOrNot="";
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
                System.out.println("You have chosen to have one clue against tree attempts you have"+tries+"tries");
            } else {
                System.out.println("Hint already given");
            }
        }else{
            System.out.println("Hint value not manage");
        }
    }

    public int getScore() {
        return score;
    }


}
