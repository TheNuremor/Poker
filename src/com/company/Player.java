package com.company;

import com.company.enums.Role;

public class Player {

    public double cash = 10000;
    //TODO Eure eigene Vorstellung von der Hand implementieren
    public CardStack handstack;
    private Role role = Role.DEFAULT;

    //Constructor
    public Player(){
        handstack = new CardStack(2);

    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
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

