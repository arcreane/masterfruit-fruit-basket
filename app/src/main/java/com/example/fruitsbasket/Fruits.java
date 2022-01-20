package com.example.fruitsbasket;

public enum Fruits
{
    STRAWBERRY(false, false, "&drawable/strawberry" ),
    BANANA(false, true, "&drawable/banana" ),
    RASPBERRY(false, false, "&drawable/raspberry" ),
    KIWI(false, true, "&drawable/kiwi" ),
    ORANGE(true, true, "&drawable/orange" ),
    PLUM(true, false, "&drawable/plum" ),
    GRAPE(true, false, "&drawable/grape" ),
    LEMON(true, true, "&drawable/lemon" ),
    EMPTY(false, false, "&drawable/empty" );

    public boolean m_bSeed;
    public boolean m_bPeelable;
    public String m_sFruitIcon;

    Fruits(boolean m_bSeed, boolean m_bPeelable, String m_sFruitIcon) {
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
}
