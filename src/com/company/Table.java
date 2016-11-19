package com.company;

import com.company.enums.Role;
import handChecker.PokerCard;
import java.util.*;


public class Table {
    public CardStack tablestack;
    public CardStack deckstack;
    public List<Player> playerList;
    public List<Player> winnerList;
    public int pot = 0;
    public int tableBet = 0;
    public int roundcounter = 0;    // Runden
    private int Blind = 100;
    private int dealerpos;
    public int maxValue = 0;
    public int lowestCash = 1000000000;

    /**Weitere Playerlist für alle die Ausgestiegen sind...
    *Später eine Lobby für alle die kein Geld mehr hatten und wartende Spieler...
    */
    private int gamecounter = 0;     // Potausschüttungen

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

    public void addPlayer(Player p, List list) {
        list.add(p);
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
                    System.out.println(playerList.get((dealerpos + 2 + i) % playerList.size()).toString(tableBet));
                    playerList.get((dealerpos + 2 + i) % playerList.size()).playerInteraction(this, betScanner());
                }   //Setzrunde mit Big als letztes
            } else {
                playerList.get((dealerpos + 1) % playerList.size()).playerInteraction(this, Blind / 2); //Small
                playerList.get((dealerpos + 2) % playerList.size()).playerInteraction(this, Blind);     //Big
                for (int i = 0; i < playerList.size(); i++) {
                    System.out.println(playerList.get((dealerpos + 3 + i) % playerList.size()).toString(tableBet));
                    playerList.get((dealerpos + 3 + i) % playerList.size()).playerInteraction(this, betScanner());
                }   //Setzrunde mit Big als letztes
            }
        } else {
            //Setrunde mit Dealer als letztes
            for (int i = 0; i < playerList.size(); i++) {
                System.out.println(playerList.get((dealerpos + 1 + i) % playerList.size()).toString(tableBet));
                playerList.get((dealerpos + 1 + i) % playerList.size()).playerInteraction(this, betScanner());
            }
        }
        if (!finishBetRound())
            betround();
    }

    public String toString() {
        String output = "";
        for (PokerCard c : tablestack.cards) {
            output += c.toString() + "\n";
        }
        return output;
    }

    public boolean finishBetRound() {
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
        System.out.println("-------------");
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

    public List<Player> decideWinner() {
        //Spieler im Spiel und Handvalue vergleichen
        winnerList.clear();
        for(Player p : playerList){
              if(p.handvalue > maxValue&&p.inGame) { maxValue=p.handvalue; }
        }
        //MaxValue Spieler zur gewinnerliste hinzufügen
        for(Player p : playerList) {
             if (p.handValue==maxValue){
                 addPlayer(p, winnerList);
                 if(p.cash < lowestCash){
                     lowestCash = cash;
                 }
             }
         }

         //Ist dieses Return noch notwendig??
        //Teilmenge playerList mit maximalen Werten (all in Round)
        return playerList.stream().filter(Player::isInGame).collect(winnerList(Comparator.comparing(p -> p.getHandValue(tablestack.getCards()))));
    }
    public void potDistribution(){
        if(winnerList.size()==1){
            for (Player p:winnerList) {
                if(!p.isAllIn){             //1.Fall: 1Player und NICHT AllIn
                    p.cash += pot;
                    pot = 0;
                    nextGameRound();
                }else{                      //2.Fall: 1Player und AllIn
                    p.cash += p.playerBet * playerList.size();
                    pot -= p.playerBet * playerList.size();
                    p.inGame = false;
                    decideWinner();
                }
            }
        }else{

        }
        /*TODO
            3.Fall: >1Player und AllIn
            4.Fall: >1Player und Keiner AllIn
                Pot durch playerlist.size()
                forEachPlayer cash +=potanteil
                wenn es einen rest gibt, dann bekommt den der spieler mit niedrigstem cash
         */
    }


    /* TODO
    Spiel vorbei->Auswertung
    */
}

