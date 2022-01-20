package com.example.fruitsbasket;

import android.sax.Element;

import java.util.HashMap;

public enum Fruits
{
    STRAWBERRY(false, false, R.drawable.strawberry, R.id.StrawberryIm ),
    BANANA(false, true, R.drawable.banana,  R.id.BananaIm),
    RASPBERRY(false, false, R.drawable.raspberry, R.id.RaspberryIm ),
    KIWI(false, true, R.drawable.kiwi, R.id.KiwiIm ),
    ORANGE(true, true, R.drawable.orange, R.id.OrangeIm ),
    PLUM(true, false, R.drawable.plum, R.id.PlumIm ),
    GRAPE(true, false, R.drawable.grape, R.id.GrapeIm ),
    LEMON(true, true, R.drawable.lemon,  R.id.LemonIm),
    EMPTY(false, false, R.drawable.empty, R.id.EmptyIm );

    public boolean m_bSeed;
    public boolean m_bPeelable;
    public int m_iFruitIcon;
    public int m_iMenuiId;

    Fruits(boolean p_bSeed, boolean p_bPeelable, int p_sFruitIcon, int p_iMenuiId) {
        this.m_bSeed = p_bSeed;
        this.m_bPeelable = p_bPeelable;
        this.m_iFruitIcon = p_sFruitIcon;
        this.m_iMenuiId = p_iMenuiId;
    }

    public String isM_bSeed() {
        if(this.m_bSeed){
            return "true";
        }else return "false";
    }

    public String isM_bPeelable() {
        if(this.m_bPeelable){
            return "true";
        }else return "false";
    }

    public int getFruitIcon() {
        return m_iFruitIcon;
    }

    public int getMenuiId() {
        return m_iMenuiId;
    }
}
