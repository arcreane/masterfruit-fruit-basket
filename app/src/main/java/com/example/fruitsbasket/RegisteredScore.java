package com.example.fruitsbasket;

public class RegisteredScore {
    private int id;
    int score;
    private String name;
    private int wons;


    public RegisteredScore(){}

    public RegisteredScore(String p_name, int p_wons, int p_score) {
        this.name = p_name;
        this.wons = p_wons;
        this.score = p_score;
    }

    //Getters
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getWons() {
        return wons;
    }

    public int getScore() {
        return score;
    }

    //Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setWons(int wons) {
        this.wons = wons;
    }


    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append(name +" "+ wons + " "+ score);
        return sb.toString();
    }
}
