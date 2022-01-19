package com.example.fruitsbasket;

public enum Fruits
{
    STRAWBERRY(false, true, "Fraise.jpg"),
    BANANA(false, false, "Banane.jpg"),
    RASPBERRY(false, false, "Framboise.jpg"),
    KIWI(false, true, "Kiwi.jpg"),
    ORANGE(true, true, "Orange.jpg"),
    PLUM(true, false, "Prune.jpg"),
    GRAPE(true, false, "Raisin.jpg"),
    LEMON(true, true, "Citron.jpg");

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
