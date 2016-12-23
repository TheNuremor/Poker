package com.company;

import handChecker.PokerCard;

class Card implements PokerCard{
    private Color color;
    private Value value;

    //Konstruktor
    Card(Value value, Color color) {
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