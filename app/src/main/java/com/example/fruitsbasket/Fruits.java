package com.example.fruitsbasket;

public enum Fruits
{
    STRAWBERRY(false, false, "@drawable/strawberry" ),
    BANANA(false, true, "@drawable/banana" ),
    RASPBERRY(false, false, "@drawable/raspberry" ),
    KIWI(false, true, "@drawable/kiwi" ),
    ORANGE(true, true, "@drawable/orange" ),
    PLUM(true, false, "@drawable/plum" ),
    GRAPE(true, false, "@drawable/grape" ),
    LEMON(true, true, "@drawable/lemon" );

    boolean m_bSeed;
    boolean m_bPeelable;
    String m_sFruitIcon;

    Fruits(boolean m_bSeed, boolean m_bPeelable, String m_sFruitIcon) {
        this.m_bSeed = m_bSeed;
        this.m_bPeelable = m_bPeelable;
        this.m_sFruitIcon = m_sFruitIcon;
    }

    public boolean isM_bSeed() {
        return m_bSeed;
    }

    public boolean isM_bPeelable() {
        return m_bPeelable;
    }
}
