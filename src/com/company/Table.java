package com.company;

import com.company.enums.Role;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Table {
    public int pot = 0;
    public CardStack tablestack;
    public CardStack deckstack;
    public List<Player> playerList;
    private int dealerpos;

    //Weitere Playerlist für alle die Ausgestiegen sind...
    //Später eine Lobby für alle die kein Geld mehr hatten und wartende Spieler...

    private int gamecounter = 0;     // Potausschüttungen
    private int roundcounter = 0;    // Runden

    public Table() {
        playerList = new LinkedList<>();
        tablestack = new CardStack(5);
        deckstack = new CardStack();
    }

    public void distributeCards() {
        switch (roundcounter) {
            case 1:
                for(Player p : playerList) {
                    //TODO würde das wahrscheinlich mit funktionen realisieren
                    p.handstack.cards.add(deckstack.deal());
                    p.handstack.cards.add(deckstack.deal());
                }
                // Karten an Spieler
                break;
            case 2:
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
        playerList.remove(p);
    }

    public String toString() {
        String output = "";
        for (Card c : tablestack.cards) {
            output += c.toString() + "\n";
        }
        return output;
    }

    public void nextRound() {
        roundcounter += 1;
        distributeCards();
    }

    public void roleDistribution() {
        if (gamecounter == 0) {
            dealerpos = new Random(System.currentTimeMillis()).nextInt(playerList.size())
        } else {
            dealerpos++;
        }
        if (playerList.size() > 2) {
            playerList.get(dealerpos).setRole(Role.DEALER);
            playerList.get(dealerpos + 1).setRole(Role.SMALL);
            playerList.get(dealerpos + 2).setRole(Role.BIG);
        } else {
            playerList.get(dealerpos).setRole(Role.DEALERSMALL);
            playerList.get(dealerpos).setRole(Role.BIG);
        }
    }

    /* TODO
    TableStack
    Betround (Preflop, Flop, Turn, River)
    Verwaltung des aktuellen Pots
    Methoden
    Verteilung Dealer und Blinds
    Verteilung Sitzplätze
    Verteilung Karten
    Zuweisung wer dran ist
    Spiel vorbei->Auswertung

    */
}

