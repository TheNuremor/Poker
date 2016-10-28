package com.company;

public class Main {

    public static void main(String [] args){

        Player Klaus = new Player();
        Player Dieter = new Player();
        Player Hans = new Player();
        Player Fritz = new Player();

        CardStack deckstack = new CardStack(52);
        deckstack.shuffleStack();


        System.out.println("Karten entfernt");
        for(int i = 0; i<5; i++){
            System.out.printf(String.valueOf(deckstack.deal()).toString());
            System.out.println("\n");
        }
    }
}
