package com.company;

public class Main {

    public static void main(String [] args){

        Table table = new Table();

        for (int i = 0; i < 4; i++) {
            table.addPlayer(new Player());
        }

        //table.removePlayer(Player p);

        System.out.println(table.deckstack.toString());

        table.nextRound();
        table.nextRound();
        table.nextRound();
        /*for(Player p : table.playerList) {
            p.interactionNumber = new Random(System.currentTimeMillis()).nextInt(4) + 1;
            p.playerInteraction(table, p.interactionNumber, 0);
        }*/
        table.playerList.get(0).playerInteraction(table, 4, 300);
        table.playerList.get(1).playerInteraction(table, 3, 0);
        table.playerList.get(2).playerInteraction(table, 1, 0);
        table.playerList.get(3).playerInteraction(table, 2, 0);

        table.nextRound();

        table.nextGameRound();

    }
}
