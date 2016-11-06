package com.company;

import com.company.enums.Role;

public class Player {

    public CardStack handstack;
    public int playerBet = 0;
    public int cash = 10000;
    public boolean inGame = true;
    public boolean isAllIn = false;
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

    public void fold() {
        inGame = false;
    }

    /* TODO
    public void call() {
        int x = Table.tableBet - playerBet;
        if (cash - x > 0) {
            cash -= x;
            Table.pot += x;
            playerBet = Table.tableBet;
        }else{
            allIn;
        }
    }

    public void raise(int x) {
        if (playerBet + x > Table.tableBet) {
            if (cash - x > 0) {
                cash -= x;
                Table.pot += x;
                playerBet *= x;
            } else {
                allIn;
            }
        }
    }
    */



    /* TODO:

    Methoden(Interaktionen)
        Raise
            Betrag
            andere finishif auf false
        Check
            Betrag von vorher
        Call
        All in
            Sieg -> Pot eventuelle Aufteilung der Pots
            Verliert -> Exit Game
    */
}

