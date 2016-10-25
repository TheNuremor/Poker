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
//Umschreiben??Bzw. ist das richtig so?
        for(Card.Suit suit : Card.Suit.values()){
            for(Card.Rank rank : Card.Rank.values()){
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
        System.out.printf("Rang:  %s und Farbe: %s\n",cards.get(0),cards.get(0));
        return cards.remove(0);
    }

    public void print(){
        int x=0;
        for(int i=0; i < cards.size();i++){
            System.out.print(cards.get(i));
            System.out.printf("\n");
            x++;
            System.out.println(x);
        }
    }
}