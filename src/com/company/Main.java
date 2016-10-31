package com.company;

public class Main {

    public static void main(String [] args){
        /*
        Player klaus = new Player();
        Player dieter = new Player();
        Player hans = new Player();
        Player fritz = new Player();
        */

        //Ihr habt nie einen Table erstellt :D
        Table table = new Table();

        for (int i = 0; i < 4; i++) {
            table.addPlayer(new Player());
        }

        //Unnötig?!
        /*
        CardStack deckstack = new CardStack();
        CardStack tablestack = new CardStack(5);
        */

        /*
        System.out.println(deckstack);
        System.out.println("Gedealt");
        System.out.println(deckstack.deal());
        */
        table.distributeCards();

        System.out.println(table.tablestack.toString());
    }
}
