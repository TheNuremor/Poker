package com.company;

import handChecker.PokerCard;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class CardStack {

    List<PokerCard> cards;

    //Constructor
    CardStack(int STACK_SIZE) {
        cards = new ArrayList<>(STACK_SIZE);
    }

    //Constructor with shuffle
    CardStack() {
        this(52);
        for (PokerCard.Color color : PokerCard.Color.values()) {
            for (PokerCard.Value value : PokerCard.Value.values()) {
                cards.add(new Card(value, color));
                //cards.add(new Card(PokerCard.Value.ASS, PokerCard.Color.DIAMONDS));
                //System.out.printf("%s %s\n",color.name(), value.name());
            }
        }

        Collections.shuffle(cards);
    }

    List<PokerCard> getCards() {
        return cards;
    }

    PokerCard deal() {
        return cards.remove(0);
    }

    public String toString() {
        String output = "";
        for (PokerCard c : cards) {
            output += c.toString() + "\n";
        }
        return output;
    }
}