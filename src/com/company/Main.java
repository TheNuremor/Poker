package com.company;

public class Main {

    public static void main(String [] args){

        Table table = new Table();

        for (int i = 0; i < 4; i++) {
            table.addPlayer(new Player());
        }

        //table.removePlayer(Player p);

        System.out.println(table.deckstack.toString());

        System.out.println("Gedealt");

        table.nextRound();
        table.nextRound();
        table.nextRound();
        table.nextRound();

        System.out.println(table.tablestack.toString());

        System.out.println("Gedealt");

        System.out.println(table.deckstack.toString());

        int tets = 0;
    }
}
