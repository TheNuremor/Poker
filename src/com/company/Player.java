package com.company;

import com.company.enums.Role;
import com.sun.corba.se.impl.presentation.rmi.ExceptionHandlerImpl;

public class Player {

    public CardStack handstack;
    public int playerBet = 0;
    public int cash = 10000;
    public boolean inGame = true;
    public boolean isAllIn = false;
    private Role role = Role.DEFAULT;

    public int interactionNumber;


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

    /**interactionNumber ist die Nummer es Buttons der gedrückt wurde..
     * Damit ist es nur möglich eine Taste gleichzeitig zu drücken und
     * der Zugriff auf Variablen dürfte auch kein Problem mehr sein.
     */
    public void plyerInteraction() throws Exception {
        switch (interactionNumber){
            case 1:     //Fold
                inGame = false;
                break;
            case 2:     //Check

                break;
            case 3:     //Call
                int x = Table.tableBet - playerBet;
                if (cash - x > 0) {
                    cash -= x;
                    Table.pot += x;
                    playerBet = Table.tableBet;
                }else{
                    interactionNumber =6;
                    throw new Exception("Sie haben nicht genug Geld, sie können entweder Folden oder All In gehen.");
                }

                break;
            case 4:     //Raise
                if (playerBet + x > Table.tableBet) {
                    if (cash - x > 0) {
                        cash -= x;
                        Table.pot += x;
                        playerBet *= x;
                    } else{
                        interactionNumber =6;
                        throw new Exception("Sie haben nicht genug Geld, sie können entweder Checken, Folden oder All In gehen.");
                    }
                }

                break;
            case 5:     //AllIn
                isAllIn = true;
                break;
            default:    //Catch all errors
                while (interactionNumber>5){
                    plyerInteraction();
                }
                break;
        }
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

