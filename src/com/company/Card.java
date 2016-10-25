package com.company;

import com.company.enums.Rank;
import com.company.enums.Suit;

public class Card {
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

}

/*
public class Card implements Comparable<Card>{


    public int compareTo(Card card){
        return this.rank.compareTo(card.rank);
    }


    public String toString(){
        return "" + rank + " "+ suit;
    }


    public static void main(String [] args){
        Card c = new Card(Rank.TWO, Suit.HEART);
        Card c2 = new Card(Rank.TEN,Suit.HEART);

        System.out.println(c);
        System.out.println(c.rank.compareTo(c2.rank));
    }
}*/