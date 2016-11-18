package com.company;

import com.company.enums.Role;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Table {
    public CardStack tablestack;
    public CardStack deckstack;
    public List<Player> playerList;
    public int pot = 0;
    public int tableBet = 0;
    private int Blind = 100;
    private int dealerpos;

    /**Weitere Playerlist für alle die Ausgestiegen sind...
    *Später eine Lobby für alle die kein Geld mehr hatten und wartende Spieler...
    */
    private int gamecounter = 0;     // Potausschüttungen
    private int roundcounter = 0;    // Runden

    public Table() {
        playerList = new LinkedList<>();
        tablestack = new CardStack(5);
        deckstack = new CardStack();
    }

    public int getTableBet() {
        return tableBet;
    }

    public void setTablestack(CardStack tablestack) {
        this.tablestack = tablestack;
    }

    public int getPot() {
        return pot;
    }

    public void setPot(int pot) {
        this.pot = pot;
    }

    public void distributeCards() {
        switch (roundcounter) {
            case 0:
                for(Player p : playerList) {
                    p.handstack.cards.add(deckstack.deal());
                    p.handstack.cards.add(deckstack.deal());
                }
                // Karten an Spieler
                break;
            case 1:
                for (int i = 0; i < 3; i++) {
                    tablestack.cards.add(deckstack.deal());
                }
                // Flop
                break;
            default:
                tablestack.cards.add(deckstack.deal());
                // Turn & River (1 Karte auf den Tisch)
                break;
        }
    }

    public void addPlayer(Player p) {
        playerList.add(p);
    }

    public void removePlayer(Player p) {
        dealerpos--;
        playerList.remove(p);
    }

    public void betround() {
        if (roundcounter == 0) {
            if (playerList.size() == 2) {
                playerList.get((dealerpos) % playerList.size()).playerInteraction(this, Blind / 2);     //DealerSmall
                playerList.get((dealerpos + 1) % playerList.size()).playerInteraction(this, Blind);     //Big
                for (int i = 0; i < playerList.size(); i++) {
                    playerList.get((dealerpos + 2 + i) % playerList.size()).toString(tableBet);
                    playerList.get((dealerpos + 2 + i) % playerList.size()).playerInteraction(this, betScanner());
                }   //Setzrunde mit Big als letztes
            } else {
                playerList.get((dealerpos + 1) % playerList.size()).playerInteraction(this, Blind / 2); //Small
                playerList.get((dealerpos + 2) % playerList.size()).playerInteraction(this, Blind);     //Big
                for (int i = 0; i < playerList.size(); i++) {
                    playerList.get((dealerpos + 3 + i) % playerList.size()).playerInteraction(this, betScanner());
                }   //Setzrunde mit Big als letztes
            }
        } else {
            //Setrunde mit Dealer als letztes
            for (int i = 0; i < playerList.size(); i++) {
                playerList.get((dealerpos + 1 + i) % playerList.size()).playerInteraction(this, betScanner());
            }
        }
        if (!finishBetround())
            betround();
    }

    public String toString() {
        String output = "";
        for (Card c : tablestack.cards) {
            output += c.toString() + "\n";
        }
        return output;
    }

    public boolean finishBetround() {
        boolean betroundfinished = true;
        for (Player p : playerList) {
            if (p.playerBet != tableBet) {
                betroundfinished = false;
            }
        }
        return betroundfinished;
    }

    public void nextRound() {
        roundcounter += 1;
        distributeCards();
    }

    public void nextGameRound() {
        for (int i = 0; i < playerList.size(); i++) {
            playerList.get(i).inGame = true;
            playerList.get(i).playerBet = 0;
            playerList.get(i).handstack.cards.clear();
        }
        deckstack = new CardStack();
        tablestack.cards.clear();
        roleDistribution();
        pot = 0;
        tableBet = 0;
        roundcounter = 0;
        gamecounter++;
    }

    public void roleDistribution() {
        if (gamecounter == 0) {
            dealerpos = new Random(System.currentTimeMillis()).nextInt(playerList.size());
        } else {
            dealerpos = (dealerpos + 1) % playerList.size();
        }
        if (playerList.size() > 2) {
            playerList.get(dealerpos).setRole(Role.DEALER);
            playerList.get((dealerpos + 1) % playerList.size()).setRole(Role.SMALL);
            playerList.get((dealerpos + 2) % playerList.size()).setRole(Role.BIG);
        } else {
            playerList.get(dealerpos).setRole(Role.DEALERSMALL);
            playerList.get((dealerpos + 1) % playerList.size()).setRole(Role.BIG);
        }
    }

    public int betScanner() {
        Scanner in = new Scanner(System.in);
        int num = in.nextInt();
        return num;
    }


    /* TODO
    Betround (Preflop, Flop, Turn, River)
    Verwaltung des aktuellen Pots
    Methoden
    Verteilung Sitzplätze
    Zuweisung wer dran ist
    Spiel vorbei->Auswertung

    */
}

