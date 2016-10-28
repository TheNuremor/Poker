package com.company;

public class Main {

    public static void main(String [] args){
        Player klaus = new Player();
        Player dieter = new Player();
        Player hans = new Player();
        Player fritz = new Player();

        CardStack deckstack = new CardStack();
        CardStack tablestack = new CardStack(5);

        System.out.println(deckstack);
        System.out.println("Gedealt");
        System.out.println(deckstack.deal());

        tablestack.toString();
    }
}
