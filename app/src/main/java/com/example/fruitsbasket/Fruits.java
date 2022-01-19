package com.example.fruitsbasket;

public enum Fruits
{
    STRAWBERRY(false, false, "Fraise.jpg"),
    BANANA(false, false, "Banane.jpg"),
    RASPBERRY(false, false, "Framboise.jpg"),
    KIWI(false, false, "Kiwi.jpg"),
    ORANGE(false, false, "Orange.jpg"),
    PRUNE(false, false, "Prune.jpg"),
    GRAPE(false, false, "Raisin.jpg"),
    LEMON(false, false, "Citron.jpg");

    public boolean m_bSeed;
    public boolean m_bPeelable;
    public String m_sFruitIcon;

    Fruits(boolean m_bSeed, boolean m_bPeelable, String m_sFruitIcon) {
    }


}
