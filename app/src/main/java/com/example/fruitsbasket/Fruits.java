package com.example.fruitsbasket;

import android.sax.Element;

import java.util.HashMap;

public enum Fruits
{
    STRAWBERRY(false, false, R.drawable.strawberry ),
    BANANA(false, true, R.drawable.banana ),
    RASPBERRY(false, false, R.drawable.raspberry ),
    KIWI(false, true, R.drawable.kiwi ),
    ORANGE(true, true, R.drawable.orange ),
    PLUM(true, false, R.drawable.plum ),
    GRAPE(true, false, R.drawable.grape ),
    LEMON(true, true, R.drawable.lemon ),
    EMPTY(false, false, R.drawable.empty );

    public boolean m_bSeed;
    public boolean m_bPeelable;
    public int m_sFruitIcon;

    Fruits(boolean m_bSeed, boolean m_bPeelable, int m_sFruitIcon) {
        this.m_bSeed = m_bSeed;
        this.m_bPeelable = m_bPeelable;
        this.m_sFruitIcon = m_sFruitIcon;
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

    public int getM_sFruitIcon() {
        return m_sFruitIcon;
    }
}
