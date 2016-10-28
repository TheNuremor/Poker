package com.company;

import com.company.enums.Rank;
import com.company.enums.Suit;

public class Card implements Comparable<Card> {
    public Suit suit;
    public Rank rank;

    //Konstruktor
    public Card(Rank rank, Suit suit){
        this.rank = rank;
        this.suit = suit;
    }

    public Rank getRank() {
        return rank;
    }

    public Suit getSuit() {
        return suit;
    }

    public String toString(){
        return "" + rank + " "+ suit;
    }

    public int compareTo(Card card) {
        return this.rank.compareTo(card.rank);
    }
}