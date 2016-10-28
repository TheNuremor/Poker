package com.company;
import java.util.LinkedList;
import java.util.List;

public class Table {
    public int pot = 0;
    public CardStack tablestack;
    public CardStack deckstack;
    public List<Player> playerList;
    public int Gamecounter = 0;     // Potausschüttungen
    public int Roundcounter = 1;    // Runden

    public Table() {
        playerList = new LinkedList<>();
        tablestack = new CardStack(5);
        deckstack = new CardStack();
    }

    public void distributecards() {
        switch (Roundcounter) {
            case 0:
                /*for (int i = 0; i < playerList.size(); i++) {
                    playerList.
                }*/
                // Karten an Spieler
                break;
            case 1:
                for (int i = 0; i < 3; i++) {
                    tablestack.cards.add(deckstack.deal());
                }
                // Flop (3 Karten auf den Tisch)
                // tablestack.add(CardStack.deal());
                break;
            case 2:
            case 3:
                tablestack.cards.add(deckstack.deal());
                // Turn & River (1 Karte auf den Tisch)
            default:
        }
    }

    public String toString() {
        String output = "";
        for (int i = 0; i < tablestack.cards.size(); i++) {
            output += tablestack.cards.get(i).toString() + "\n";
        }
        return output;
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

