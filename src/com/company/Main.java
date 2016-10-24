package com.company;

public class Main {

    public static void main(String [] args){
        //Braucht ein Rank und Suit im CardStack();, kann damit allerdings nicht umgehen....
        CardStack stack1 = new CardStack(Card.Rank.TWO, Card.Suit.DIAMOND);
        stack1.shuffleStack();


        System.out.println("Karten entfernt");
        for(int i = 0; i<5; i++){
            stack1.deal();
        }
        stack1.print();
    }
}
