package com.example.fruitsbasket;

public enum Fruits
{
    STRAWBERRY(false, false, "@drawable/strawberry" ),
    BANANA(false, true, "@drawable/banana" ),
    RASPBERRY(false, false, "@drawable/raspberry" ),
    KIWI(false, true, "@drawable/kiwi" ),
    ORANGE(true, true, "@drawable/orange" ),
    PRUNE(true, false, "@drawable/plum" ),
    GRAPE(true, false, "@drawable/grape" ),
    LEMON(true, true, "@drawable/lemon" );

    public boolean m_bSeed;
    public boolean m_bPeelable;
    public String m_sFruitIcon;

    Fruits(boolean m_bSeed, boolean m_bPeelable, String m_sFruitIcon) {
    }
}
