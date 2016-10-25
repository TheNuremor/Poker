package com.company;

import com.company.enums.Rank;
import com.company.enums.Suit;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CardStack{


    private final List<Card> cards;
    private int STACK_SIZE = 52;

    //Constuctor
    public CardStack(){
        cards = new ArrayList<>(STACK_SIZE);
        //Umschreiben??Bzw. ist das richtig so?
        for (Suit suit : Suit.values()) {
            for (Rank rank : Rank.values()) {
                cards.add(new Card(rank, suit));
                System.out.printf("%s %s\n",suit.name(), rank.name());
            }
        }

    }
//Schuffle-Methode
    public void shuffleStack(){
        Collections.shuffle(cards);
    }


//Deal-Methode sie braucht noch eine Zuordnung, wer die Karte bekommen soll
    public Card deal(){
        System.out.printf("Rang:  %s und Farbe: %s\n", cards.get(0).getRank().toString(), cards.get(0).getSuit().toString());
        return cards.remove(0);
    }

    public void print(){
        int x=0;
        for (int i = 0; i < cards.size(); i++, x++) {
            System.out.print(cards.get(i).getRank().toString() + " " + cards.get(i).getSuit().toString());
            System.out.printf("\n");
            System.out.println(x);
        }
    }
}