package com.company.Game;

import com.company.enums.Role;
import handChecker.HandValue;
import handChecker.PokerCard;

import java.util.*;

public class Table extends Thread{
    public List<Player> playerList;
    private CardStack tablestack;
    private CardStack deckstack;
    private List<Player> winnerList;

    private int roundcounter = 0;    // Runden
    private int gamecounter = 0;    // Potausschüttungen
    private int Blind = 100;
    public int pot = 0;
    public int maxBet = 0;
    private int dealerpos;

    private boolean blindSet = false;

    public Table() {
        playerList = new LinkedList<>();
        winnerList = new LinkedList<>();
        tablestack = new CardStack(5);
        deckstack = new CardStack();
    }

    @Override
    public void run() {
        startGame();
    }

    //Server

    void broadcastToAllPlayers(String header, Object payload) {
        playerList.forEach(player -> player.sendMessageToClient(header, payload));
    }

    public void startGame() {
        while (!playerList.stream().allMatch(Player::isFinished)) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        broadcastToAllPlayers("startGame", null);
        nextRound();

        broadcastToAllPlayers("endGame", null);
    }

    //Für Tim (nextTurn)
    void nextRound() {
        if (roundcounter < 4) {
            if (roundcounter == 0)
                roleDistribution();
            distributeCards();
            betRound();
            roundcounter++;
        } else
            nextGameRound();
    }

    //Für Tim (nextRound)
    void nextGameRound() {
        decideWinner();

        for (int i = 0; i < playerList.size(); i++) {
            playerList.get(i).inGame = true;
            playerList.get(i).playerBet = 0;
            playerList.get(i).handstack.cards.clear();
        }

        deckstack = new CardStack();
        tablestack.cards.clear();
        pot = 0;
        maxBet = 0;
        blindSet = false;
        roundcounter = 0;
        gamecounter++;

        broadcastToAllPlayers("nextGameRound", null);
    }

    void distributeCards() {
        switch (roundcounter) {
            case 0:
                for(Player p : playerList) {
                    for (int i = 0; i < 2; i++) {
                        PokerCard card = deckstack.deal();
                        p.handstack.cards.add(card);
                        p.sendMessageToClient("handCard", card);
                    }
                }
                break;
            case 1:
                for (int i = 0; i < 3; i++) {
                    PokerCard card = deckstack.deal();
                    tablestack.cards.add(card);
                    broadcastToAllPlayers("openCard", card);
                }
                break;
            default:
                PokerCard card = deckstack.deal();
                tablestack.cards.add(card);
                broadcastToAllPlayers("openCard", card);
                break;
        }
    }

    public void addPlayer(Player p) {
        p.start();
        playerList.add(p);
    }

    //TODO remove player after ALLIN failed
    public void removePlayer(Player p) {
        dealerpos--;
        p.close();
        playerList.remove(p);
    }

    void betRound() {
        Map<String, Integer> information = new HashMap<>();
        for (Player p : playerList) {
            information.put(p.getPlayerName(), p.getCash());
        }
        broadcastToAllPlayers("chips", information);

        if (roundcounter == 0 ) {
            if (playerList.size() == 2) {

                playerList.get(dealerpos % playerList.size()).playerInteraction(this, Blind / 2);     //DealerSmall
                playerList.get((dealerpos + 1) % playerList.size()).playerInteraction(this, Blind);     //Big
                blindSet = true;

                for (int i = 0; i < playerList.size(); i++) {
                    if (playerList.get((dealerpos + 2 + i) % playerList.size()).inGame) {
                        do {
                            playerList.get((dealerpos + 2 + i) % playerList.size()).playerInteraction(this, betScanner(playerList.get((dealerpos + 2 + i) % playerList.size())));
                            broadcastToAllPlayers("chips", new HashMap<String, Integer>().put(playerList.get((dealerpos + 2 + i) % playerList.size()).getPlayerName(), playerList.get((dealerpos + 2 + i) % playerList.size()).getCash()));
                        } while (!playerList.get((dealerpos + 2 + i) % playerList.size()).betRight);
                    }
                }   //Setzrunde mit Big als letztes

            } else {
                if (!blindSet) {
                    playerList.get((dealerpos + 1) % playerList.size()).playerInteraction(this, Blind / 2); //Small
                    playerList.get((dealerpos + 2) % playerList.size()).playerInteraction(this, Blind);     //Big
                    blindSet = true;
                }
                for (int i = 0; i < playerList.size(); i++) {
                    if(playerList.get((dealerpos + 3 + i) % playerList.size()).inGame) {
                        do {
                            playerList.get((dealerpos + 3 + i) % playerList.size()).playerInteraction(this, betScanner(playerList.get((dealerpos + 3 + i) % playerList.size())));
                            broadcastToAllPlayers("chips", new HashMap<String, Integer>().put(playerList.get((dealerpos + 3 + i) % playerList.size()).getPlayerName(), playerList.get((dealerpos + 3 + i) % playerList.size()).getCash()));
                        } while (!playerList.get((dealerpos + 3 + i) % playerList.size()).betRight);
                    }
                }   //Setzrunde mit Big als letztes
            }
        } else {
            //Setrunde mit Dealer als letztes
            for (int i = 0; i < playerList.size(); i++) {
                if(playerList.get((dealerpos + 1 + i) % playerList.size()).inGame) {
                    do {
                        playerList.get((dealerpos + 1 + i) % playerList.size()).playerInteraction(this, betScanner(playerList.get((dealerpos + 1 + i) % playerList.size())));
                        broadcastToAllPlayers("chips", new HashMap<String, Integer>().put(playerList.get((dealerpos + 1 + i) % playerList.size()).getPlayerName(), playerList.get((dealerpos + 1 + i) % playerList.size()).getCash()));
                    } while (!playerList.get((dealerpos + 1 + i) % playerList.size()).betRight);
                }
            }
        }
        if (!finishBetRound()) {
            betRound();
        }
    }

    @Override
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
            if ((p.playerBet != maxBet) && (p.inGame)) {
                betroundfinished = false;
            }
        }
        return betroundfinished;
    }

    void roleDistribution() {
        playerList.forEach(player -> player.setRole(Role.DEFAULT));
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

        //Server
        Map<String, Role> information = new HashMap<>();
        for (Player player : playerList) {
            information.put(player.getPlayerName(), player.getRole());
        }
        broadcastToAllPlayers("role", information);
    }

    private int betScanner(Player player) {
        player.sendMessageToClient("bet", null);
        while(player.playerBet == null) {
            try {
                wait();
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }
        int output = player.playerBet;
        player.playerBet = null;

        return output;
    }

    void decideWinner() {
        if (pot != 0) {
            if (playerList.stream().filter(Player::isInGame).count() == 1) {
                for (Player p : playerList)
                    if (p.isInGame())
                        winnerList.add(p);
            } else {
                //Spieler im Spiel und Handvalue
                HandValue maxValue = playerList.get(0).getHandValue(playerList.get(0).getCompleteHandstack(tablestack));
                winnerList.clear();

                for (Player p : playerList) {
                    if ((p.hv.compareTo(maxValue) == 1) && (p.inGame)) {
                        maxValue = p.hv;
                    }
                }

                //MaxValue Spieler zur gewinnerliste hinzufügen
                for (Player p : playerList) {
                    if (p.hv.compareTo(maxValue) == 0) {
                        winnerList.add(p);
                    }
                }

            }
            potDistribution();
            //TODO optional send packet
            //broadcastToAllPlayers("winners", winnerList);
        }
    }

    private void potDistribution() {
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
        } else {
            nextGameRound();
        }
    }
}