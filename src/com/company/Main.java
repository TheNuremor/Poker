package com.company;

public class Main {

    public static void main(String [] args){

        Table table = new Table();

        for (int i = 0; i < 4; i++) {
            table.addPlayer(new Player(),table.playerList);
        }

        while (true) {

            table.roleDistribution();
            table.distributeCards();

            for (int i = 0; i < 3; i++) {
                table.betround();
                table.nextRound();
            }
            table.betround();

            table.decideWinner();
            table.nextGameRound();
        }
        /*for (int i = 0; i < 4; i++) {
            table.distributeCards();
            table.roundcounter++;
        }
        table.decideWinner();
        */
    }
}
