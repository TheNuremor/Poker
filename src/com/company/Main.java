package com.company;

public class Main {

    public static void main(String [] args){

        Table table = new Table();

        for (int i = 0; i < 4; i++) {
            table.addPlayer(new Player());
        }

        table.roleDistribution();
        for (int i = 0; i < 4; i++) {
            table.betround();
            table.nextRound();
        }

        table.nextGameRound();

    }
}
