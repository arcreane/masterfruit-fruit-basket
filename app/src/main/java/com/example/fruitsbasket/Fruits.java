package com.example.fruitsbasket;

public enum Fruits
{
    STRAWBERRY(false, false, "&drawable/strawberry" ),
    BANANA(false, false, "&drawable/banana" ),
    RASPBERRY(false, false, "&drawable/raspberry" ),
    KIWI(false, false, "&drawable/kiwi" ),
    ORANGE(false, false, "&drawable/orange" ),
    PLUM(false, false, "&drawable/plum" ),
    GRAPE(false, false, "&drawable/grape" ),
    LEMON(false, false, "&drawable/lemon" ),
    EMPTY(false, false, "&drawable/empty" );

    public boolean m_bSeed;
    public boolean m_bPeelable;
    public String m_sFruitIcon;

    Fruits(boolean m_bSeed, boolean m_bPeelable, String m_sFruitIcon) {
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
}
