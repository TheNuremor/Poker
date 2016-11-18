package com.company;

import handChecker.PokerCard;

public class Card implements PokerCard{
    public Color color;
    public Value value;

    //Konstruktor
    public Card(Value value, Color color) {
        this.value = value;
        this.color = color;
    }

    public String toString(){
        return "" + value + " " + color;
    }

    @Override
    public Color getColor() {
        return color;
    }

    @Override
    public Value getValue() {
        return value;
    }
}