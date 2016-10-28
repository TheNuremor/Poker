package com.company;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Table {
    private final List<Card> Tablestack;
    public int pot = 0;
    public List<Player> playerList;
    public int Gamecounter = 0;     // Potausschüttungen
    public int Roundcounter = 0;    // Runden

    public Table() {
        pot = pot;
        playerList = new LinkedList<>();
        Tablestack = new ArrayList<>(5);
    }

    public void ditributecards() {
        switch (Roundcounter) {
            case 0:

                // Karten an Spieler
                break;
            case 1:
                // Flop (3 Karten auf den Tisch)
                // Tablestack.add(CardStack.deal());
                break;
            case 2:
            case 3:
                // Turn & River (1 Karte auf den Tisch)
            default:
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

