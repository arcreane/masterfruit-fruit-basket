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

    public Player(){

    }

    public Player(String player_name, int score) {
        this.player_name = player_name;
        this.score = score;
        this.nbGames_won = 0;
    }

    public String getPlayer_name() {
        return player_name;
    }

    public void setPlayer_name(String player_name) {
        this.player_name = player_name;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void addScore(int score) {
        this.score += score;
        if(score !=0)
            this.nbGames_won ++;
    }

    public int getScore() {
        return score;
    }


}
