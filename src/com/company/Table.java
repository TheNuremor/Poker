package com.company;
import java.util.LinkedList;
import java.util.List;

public class Table {
    public int pot = 0;
    public CardStack tablestack;
    public CardStack deckstack;
    public List<Player> playerList;

    //Weitere Playerlist für alle die Ausgestiegen sind...
    //Später eine Lobby für alle die kein Geld mehr hatten und wartende Spieler...

    public int gamecounter = 0;     // Potausschüttungen
    public int roundcounter = 2;    // Runden

    public Table() {
        playerList = new LinkedList<>();
        tablestack = new CardStack(5);
        deckstack = new CardStack();
    }

    public void distributeCards() {
        switch (roundcounter) {
            //Vorher war hier eine 0, tritt nie ein, da roundcounter auf 0 initialisiert ist
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
                //TODO Karten aus deckstack nehmen nicht random neue erschaffen...
                // Flop (3 Karten auf den Tisch)
                // tablestack.add(CardStack.deal());
                break;
            default:
                //Andere cases nicht nötig da die runden nach 2 indentisch sind
                tablestack.cards.add(deckstack.deal());
                // Turn & River (1 Karte auf den Tisch)
                break;
        }
    }

    public void addPlayer(Player p) {
        playerList.add(p);
    }
    //public void removePlayer (Player p) { playerList.remove(p);}

    public String toString() {
        String output = "";
        for (Card c : tablestack.cards) {
            output += c.toString() + "\n";
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

