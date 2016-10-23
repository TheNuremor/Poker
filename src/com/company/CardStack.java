package com.company;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CardStack{

    private int STACK_SIZE = 52;

    private final List<Card> cards;

    //Constuctor
    public CardStack(){
        cards = new ArrayList<Card>(STACK_SIZE);

        for(Card.Suit suit : Card.Suit.values()){
            for(Card.Rank rank : Card.Rank.values()){
                cards.add(new Card(rank, suit));
                System.out.printf("Rang:  %s und Farbe: %s\n",rank.name(), suit.name());
            }
        }

    }

    public void shuffleDeck(){
        Collections.shuffle(cards);
    }

    public Card deal(){
        System.out.printf("Rang:  %s und Farbe: %s\n",Card.Rank.ACE,Card.Suit.HEART);
        System.out.printf("%d", STACK_SIZE);
        return cards.remove(0);
    }

}