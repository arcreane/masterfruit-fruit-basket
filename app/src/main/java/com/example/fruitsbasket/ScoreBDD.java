package com.example.fruitsbasket;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class ScoreBDD {

    private static final int VERSION = 1;
    private static final String NOM_BDD = "score.db";

    private static final String TABLE_SCORE = "table_scores";
    private static final String COL_ID = "ID";
    private static final int NUM_COL_ID = 0;
    private static final String COL_NAME = "Name";
    private static final int NUM_COL_NAME = 1;
    private static final String COL_WONS = "Wons";
    private static final int NUM_COL_WONS = 2;
    private static final String COL_SCORE = "Scores";
    private static final int NUM_COL_SCORE = 3;

    private static final String CREATE_BDD = "CREATE TABLE "+ TABLE_SCORE +
            " (" + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "+
            COL_NAME + " TEXT NOT NULL, " + COL_WONS + " INTEGER, " +
            COL_SCORE + " INTEGER); ";

    private SQLiteDatabase bdd;
    private ScoreBaseSQLite scores;

    public ScoreBDD(Context context){
        scores = new ScoreBaseSQLite(context, NOM_BDD, null, VERSION);
    }

    public void openForWrite(){
        bdd = scores.getWritableDatabase();
    }

    public void openForRead(){
        bdd = scores.getReadableDatabase();
    }

    public void close(){
        bdd.close();
    }

    public SQLiteDatabase getBdd(){
        return bdd;
    }

    /**
     * Insert a new line of score in the Base
     * @param score
     * @return long
     */
    public long insertScore(RegisteredScore score){
        ContentValues content = new ContentValues();
        content.put(COL_WONS, score.getWons());
        content.put(COL_NAME, score.getName());
        content.put(COL_SCORE, score.getScore());
        return bdd.insert(TABLE_SCORE, null, content);
    }

    /**
     * Updates the base with new score
     * @param id
     * @param score
     * @return long
     */
    public long updateScore(int id, RegisteredScore score){
        ContentValues content = new ContentValues();
        content.put(COL_WONS, score.getWons());
        content.put(COL_NAME, score.getName());
        content.put(COL_SCORE, score.getScore());
        return bdd.update(TABLE_SCORE, content, COL_ID+" = "+ id,null);
    }

    /**
     * To delete a line of score in the BDD
     * @param name
     * @return int
     */
    public int removeScore(String name){
        return bdd.delete(TABLE_SCORE, COL_NAME + " = \"" + name + "\"", null);
    }

    /**
     * Places the cursor at desired position in the Table
     * @param name
     * @return RegisteredScore class object
     */
    public RegisteredScore getScore(String name){
        Cursor c = bdd.query(TABLE_SCORE, new String[] {
                COL_ID, COL_NAME, COL_WONS, COL_SCORE}, COL_NAME + " LIKE \"" + name + "\""+
                null, null, null, null, COL_NAME);
        return cursorToScore(c);
    }

    /**
     * Reads the database at line cursor position
     * @param c cursor of bdd
     * @return RegisteredScore class object
     */
    private RegisteredScore cursorToScore(Cursor c) {
        if(c.getCount() == 0){
            c.close();
            return null;
        }
        RegisteredScore score = createRegisteredScore(c);
        c.close();
        return score;
    }

    public ArrayList<RegisteredScore> getAllScores(){
        Cursor c = bdd.query(TABLE_SCORE, new String[]{
                COL_ID, COL_NAME, COL_WONS, COL_SCORE}, null, null, null, null, COL_SCORE + " DESC");
        if(c.getCount() == 0){
            c.close();
            return null;
        }
        ArrayList<RegisteredScore> scoreList = new ArrayList<RegisteredScore>();
        while(c.moveToNext()){
            RegisteredScore score = createRegisteredScore(c);
            scoreList.add(score);
        }
        c.close();
        return scoreList;
    }

    private RegisteredScore createRegisteredScore(Cursor c){
        RegisteredScore score = new RegisteredScore();
        score.setId(c.getInt(NUM_COL_ID));
        score.setWons(c.getInt(NUM_COL_WONS));
        score.setName(c.getString(NUM_COL_NAME));
        score.setScore(c.getInt(NUM_COL_SCORE));
        return score;
    }



}
