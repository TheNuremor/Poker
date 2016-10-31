package com.company;

public class Player {

    public double cash = 10000;
    //Warum als einzelne Variablen? Ein Int oder Enum reicht doch
    public boolean finish = false;
    public boolean dealer = false;
    public boolean smallBlind = false;
    public boolean bigBlind = false;

    //TODO Eure eigene Vorstellung von der Hand implementieren
    public CardStack handstack;

    public Player(){
        handstack = new CardStack(2);

        //Warum?! Das macht gar nichts
        cash = cash;
        finish = finish;
        dealer = dealer;
        smallBlind = smallBlind;
        bigBlind = bigBlind;
    }

    /* TODO:

    HandStack
    Playerkonto
    Dealer,Small, Big Blind
    Sitzplatzzuweisung von Table



    Methoden(Interaktionen)
        Raise
            Betrag
            andere finishif auf false
        Bet
            Betrag
        Check
            Betrag von vorher
        Call
        Fold
            Exit für Runde
        All in
            Sieg -> Pot eventuelle Aufteilung der Pots
            Verliert -> Exit Game
    */
}

