package com.example.fruitsbasket;

public class RegisteredScore {
    private int id;
    int score;
    private String name;
    private String date;

    public RegisteredScore(){}

    public RegisteredScore(String p_name, String p_date, int p_score) {
        this.name = p_name;
        this.date = p_date;
        this.score = p_score;
    }

    //Getters
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDate() {
        return date;
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

    public void setDate(String date) {
        this.date = date;
    }


    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append(date +" - Player's name: "+ name + "\nScore: "+ score);
        return sb.toString();
    }
}
