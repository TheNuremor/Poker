package com.company;

import com.company.enums.Role;

import java.util.Random;

public class Player {

    public CardStack handstack;
    public int playerBet = 0;
    public int cash = 10000;
    public boolean inGame = true;
    public boolean isAllIn = false;
    public int interactionNumber;
    private Role role = Role.DEFAULT;

    //Constructor
    public Player(){
        handstack = new CardStack(2);
        interactionNumber = 0;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public int getInteractionNumber() {
        return interactionNumber;
    }

    public void setInteractionNumber(int interactionNumber) {
        this.interactionNumber = interactionNumber;
    }

    public void playerInteraction(Table table, int interactionNumber, int bet) {
        if (inGame == true) {
            switch (interactionNumber) {
                case 1:     //Fold
                    inGame = false;
                    playerBet = 0;
                    break;
                case 2:     //Check
                    if (table.tableBet != playerBet) {
                        System.out.println("Sie können nicht checken, Sie können entweder Folden, Raisen");
                        playerInteraction(table, interactionNumber, bet);
                    }
                    break;
                case 3:     //Call
                    int x = table.tableBet - playerBet;
                    if (cash - x > 0) {
                        cash -= x;
                        table.pot += x;
                        playerBet = table.tableBet;
                    } else {
                        System.out.println("Sie haben nicht genug Geld, Sie können entweder Folden oder All In gehen.");
                        playerInteraction(table, interactionNumber, bet);
                    }

                    break;
                case 4:     //Raise
                    bet = new Random(System.currentTimeMillis()).nextInt(cash);

                    if (playerBet + bet > table.tableBet) {
                        if (cash - bet > 0) {
                            cash -= bet;
                            table.pot += bet;
                            playerBet += bet;
                            table.tableBet = playerBet;
                        } else {
                            System.out.println("Sie haben nicht genug Geld, Sie können entweder Checken, Folden oder All In gehen.");
                            playerInteraction(table, interactionNumber, bet);
                        }
                    }

                    break;
                case 5:     //AllIn
                    isAllIn = true;
                    playerBet += cash;
                    if (table.tableBet <= cash) {
                        table.tableBet = playerBet;
                    }
                    table.pot += cash;
                    cash = 0;

                    break;
                default:    //Catch all errors
                    System.out.println("Eingabe bitte wiederholen");
                    playerInteraction(table, interactionNumber, bet);
                    break;
            }
        }
    }


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

