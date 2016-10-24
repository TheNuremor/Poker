package com.company;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CardStack extends Card{


    private int STACK_SIZE = 52;

    private final List<Card> cards;


/*Also ich habe Card als Oberklasse an Cardstacks vererbt.
* Dafür musste im Konstruktor von CardStack die super() Funktion dazukommen
* und der Konstrukter muss die Variablen Rank rank, Suit suit als Referenz haben.
* Dann kann man alles weitere einfach so nutzen...
* Jetzt muss ich nur noch die for-Schleifen umändern...*/


    //Constuctor
    public CardStack(Rank rank, Suit suit){
        super(rank, suit);
        cards = new ArrayList<Card>(STACK_SIZE);

        for(Card.Suit suit1 : Card.Suit.values()){
            for(Card.Rank rank1 : Card.Rank.values()){
                cards.add(new Card(rank1, suit1));
                System.out.printf("Rang:  %s und Farbe: %s\n",rank.name(), suit.name());
            }
        }

    }

    public void shuffleStack(){
        Collections.shuffle(cards);
    }

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