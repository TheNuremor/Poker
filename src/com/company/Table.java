package com.company;

import com.company.enums.Role;
import handChecker.HandValue;
import handChecker.PokerCard;

import java.io.ObjectOutputStream;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

class Table {
    public List<Player> playerList;
    private CardStack tablestack;
    private CardStack deckstack;
    private List<Player> winnerList;
    private GameGUI gameGUI;

    private int roundcounter = 0;    // Runden
    private int gamecounter = 0;    // Potausschüttungen
    private int Blind = 100;
    public int pot = 0;
    public int tableBet = 0;
    private int dealerpos;

    private HandValue maxValue = null;
    private boolean blindSet = false;


    Table() {
        playerList = new LinkedList<>();
        winnerList = new LinkedList<>();
        tablestack = new CardStack(5);
        deckstack = new CardStack();
        //gameGUI = new GameGUI();

    }

    public void startGame() {
        if (playerList.size() < 3)
            return;

        while (playerList.stream().anyMatch(player -> !player.clientThread.isFinished())) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        playerList.forEach((Player player1) -> {
            //player1.clientThread.sendData("Spiel gestartet\n");
        });

        roleDistribution();
        while (playerList.size() != 1) {

            while (roundcounter < 4) {
                if (roundcounter == 0) {
                    distributeCards();
                    betround();
                }else {
                    int count = 0;

                    for (Player p : playerList)
                        if (p.inGame) count++;

                    if (count != 1) betround();
                    else while(roundcounter < 2) nextRound();

                }
                nextRound();
            }
            nextRound();  //nextGameRound

        }

        playerList.forEach((Player player1) -> {
            //player1.clientThread.sendData("Spiel beendet\n");
        });
    }

    public boolean allPlayersFinished() {
        return playerList.stream().allMatch(Player::isFinished);
    }

    public int getTableBet() {
        return tableBet;
    }

    public void setTablestack(CardStack tablestack) {
        this.tablestack = tablestack;
    }

    public CardStack getTablestack() {
        return tablestack;
    }

    public int getPot() {
        return pot;
    }

    public void setPot(int pot) {
        this.pot = pot;
    }

    void distributeCards() {
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

    void addPlayer(Player p, List list) {
        list.add(p);
    }

    public void removePlayer(Player p) {
        dealerpos--;
        playerList.remove(p);
    }

    private void sendPlayerData() {
        playerList.forEach((Player p) -> {
            if (p.isInGame()) p.toString(this);
        });
    }

    void betround() {

        if (roundcounter == 0 ) {
            if (playerList.size() == 2) {
                if (!blindSet) {
                    playerList.get((dealerpos) % playerList.size()).playerInteraction(this, Blind / 2);     //DealerSmall
                    playerList.get((dealerpos + 1) % playerList.size()).playerInteraction(this, Blind);     //Big
                    blindSet = true;
                }
                sendPlayerData();
                for (int i = 0; i < playerList.size(); i++) {
                    if(playerList.get((dealerpos + 2 + i) % playerList.size()).inGame)
                        playerList.get((dealerpos + 2 + i) % playerList.size()).playerInteraction(this, betScanner(playerList.get((dealerpos + 2 + i) % playerList.size())));
                }   //Setzrunde mit Big als letztes
            } else {
                if (!blindSet) {
                    playerList.get((dealerpos + 1) % playerList.size()).playerInteraction(this, Blind / 2); //Small
                    playerList.get((dealerpos + 2) % playerList.size()).playerInteraction(this, Blind);     //Big
                    blindSet = true;
                }
                sendPlayerData();
                for (int i = 0; i < playerList.size(); i++) {
                    if(playerList.get((dealerpos + 3 + i) % playerList.size()).inGame) {
                        do playerList.get((dealerpos + 3 + i) % playerList.size()).playerInteraction(this, betScanner(playerList.get((dealerpos + 3 + i) % playerList.size())));
                        while (!playerList.get((dealerpos + 3 + i) % playerList.size()).betRight);
                    }
                }   //Setzrunde mit Big als letztes
            }
        } else {
            sendPlayerData();
            //Setrunde mit Dealer als letztes
            for (int i = 0; i < playerList.size(); i++) {
                if(playerList.get((dealerpos + 1 + i) % playerList.size()).inGame) {
                    do {
                        //System.out.println(playerList.get((dealerpos + 1 + i) % playerList.size()).toString(this));
                        playerList.get((dealerpos + 1 + i) % playerList.size()).playerInteraction(this, betScanner(playerList.get((dealerpos + 1 + i) % playerList.size())));
                    } while (!playerList.get((dealerpos + 1 + i) % playerList.size()).betRight);
                }
            }
        }
        if (!finishBetRound()) {
            betround();
        }
    }

    public String toString() {

        String output = "";
        for (PokerCard c : tablestack.cards) {
            output += c.toString() + "\n";
        }
        return output;
    }

    private boolean finishBetRound() {
        boolean betroundfinished = true;
        for (Player p : playerList) {
            if ((p.playerBet != tableBet) && (p.inGame)) {
                betroundfinished = false;
            }
        }
        return betroundfinished;
    }

    void nextRound() {
        if (roundcounter < 3) {
            if (roundcounter == 0) roleDistribution();
            roundcounter ++;
            distributeCards();
            //playerList.forEach((Player player1) -> player1.clientThread.sendData("-------------"));
        } else nextGameRound();
    }

    void nextGameRound() {
        decideWinner();
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
        blindSet = false;
        roundcounter = 0;
        gamecounter++;
        System.out.println("-----------------------------");
    }

    void roleDistribution() {
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

    private int betScanner(Player player) {
        //player.clientThread.sendData("/Bet");
        while(player.clientThread.bet == null) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        int output = Integer.parseInt(player.clientThread.bet.substring(8));
        player.clientThread.bet = null;

        playerList.forEach(player1 -> {
            //if (!player1.equals(player))
                //player1.clientThread.sendData("Spieler " + player.getName() + " hat " + output + " gesetzt.");
        });

        return output;
    }

    void decideWinner() {
        //Spieler im Spiel und Handvalue vergleichen
        if (winnerList != null) {
            winnerList.clear();
        }

        for (Player p : playerList) {
            if (p.inGame) {
                maxValue = p.getHandValue(p.getCompleteHandstack(tablestack));
            }
        }

        for (Player p : playerList) {
            if ((p.hv.compareTo(maxValue) == 1) && (p.inGame)) {
                maxValue = p.hv;
            }
        }
        //MaxValue Spieler zur gewinnerliste hinzufügen
        for (Player p : playerList) {
            if (p.hv.compareTo(maxValue) == 0) {
                addPlayer(p, winnerList);
            }
        }
        if (pot != 0) {
            potDistribution();
        }
    }

    private void potDistribution() {
        //String winner = "";
        int playerAllIn = 0;
        if (winnerList.size() == 1) {          // 1 Gewinner
            for (Player p : winnerList) {
                if (!p.isAllIn) {             //1.Fall: 1 Player und NICHT AllIn
                    p.cash += pot;
                    pot = 0;
                }else{                      //2.Fall: 1Player und AllIn
                    p.cash += p.playerBet * playerList.size();
                    pot -= p.playerBet * playerList.size();
                    p.inGame = false;
                }
            }
        } else {      // mehr als 1 Gewinner
            for (Player p : winnerList) {
                if (p.isAllIn) {
                    playerAllIn += 1;
                }
            }
            if (playerAllIn == 0) {
                for (Player p : winnerList) {
                    p.cash += pot / winnerList.size();
                }
                pot = 0;
            } else {
                for (Player p : winnerList) {
                    p.cash += p.playerBet * playerList.size();
                    pot -= p.playerBet * playerList.size();
                    p.inGame = false;
                }
            }
        }
        if (pot != 0) {
            decideWinner();
        }
        if (pot == 0) {
            /*winnerList.forEach((Player player1) -> {
                winner = winner + player1.getName();
                player1.clientThread.sendData("Spieler: "+ winner + " hat gewonnen!");
            });*/
            playerList.forEach((Player player1) -> {
                //player1.clientThread.sendData("\nEine neue Runde beginnt!\n");
            });
            nextGameRound();
        }
    }
}