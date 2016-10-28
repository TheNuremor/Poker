package com.company;

import com.company.enums.Rank;
import com.company.enums.Suit;
import handChecker.PokerCard;

public class Card implements PokerCard{
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

    @Override
    public Color getColor() {
        return Color.valueOf(suit.toString());
    }

    @Override
    public Value getValue() {
        return Value.values()[rank.rank()];
    }
}