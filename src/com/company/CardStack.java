package com.company;

import com.company.enums.Rank;
import com.company.enums.Suit;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CardStack {

    public List<Card> cards;

    //Constructor
    public CardStack(int STACK_SIZE) {
        cards = new ArrayList<>(STACK_SIZE);
    }

    //Constructor with shuffle
    public CardStack() {
        this(52);
        for (Suit suit : Suit.values()) {
            for (Rank rank : Rank.values()) {
                cards.add(new Card(rank, suit));
                System.out.printf("%s %s\n",suit.name(), rank.name());
            }
        }
        Collections.shuffle(cards);
    }

    public Card deal(){
        return cards.remove(0);
    }

    public String toString() {
        String output = "";
        for (int i = 0; i < cards.size(); i++) {
            output += cards.get(i).toString() + "\n";
        }
        return output;
    }


}