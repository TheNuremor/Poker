package com.company;

import com.company.enums.Role;
import handChecker.HandChecker;
import handChecker.HandValue;
import handChecker.PokerCard;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Player {

    public CardStack handstack;
    public int playerBet = 0;
    public int cash = 10000;
    public boolean inGame = true;
    public boolean isAllIn = false;
    public int interactionNumber;
    public HandValue hv;
    private Role role = Role.DEFAULT;
    //Constructor
    public Player() {
        handstack = new CardStack(2);
        interactionNumber = 0;
    }

    public boolean isInGame() {
        return inGame;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public CardStack getHandstack() {
        return handstack;
    }

    public String toString(int tableBet) {
        return "Cash:\t" + cash + "\nRole:\t" + role.toString() + "\nPlayer Bet:\t" + playerBet + "\nTable Bet:\t" + tableBet;
    }

    public void playerInteraction(Table table, int bet) {
        if (inGame && (!isAllIn)) {
            if (bet > 0 && (playerBet + bet) < table.tableBet) {
                System.out.println("Falsche Eingabe");
            } else if (bet < 0) {
                // Fold
                inGame = false;
            } else { // Call -wenn bet = tableBet - bzw Raise
                if (cash > bet) { // enough money, no allin
                    playerBet += bet;
                    cash -= bet;
                    table.pot += playerBet;
                    if (playerBet > table.tableBet) { //effektiver Raise
                        table.tableBet = playerBet;
                    }
                } else {
                    //AllIn
                    isAllIn = true;
                    playerBet += cash;
                    if (table.tableBet < cash) {
                        table.tableBet = playerBet;
                    }
                    table.pot += cash;
                    cash = 0;
                }
            }
        }
    }

    public List<PokerCard> getCompleteHandstack(CardStack tablestack) {
        return Stream.concat(handstack.getCards().stream(), tablestack.getCards().stream()).collect(Collectors.toList());
    }

    public HandValue getHandValue(List<PokerCard> completestack) {
        if (hv == null) {
            HandChecker handChecker = new HandChecker();
            hv = handChecker.check(completestack);
        }
        return hv;
    }


}


