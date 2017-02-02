package com.company.Game;

import handChecker.PokerCard;

import java.io.Serializable;

public class Card implements PokerCard, Serializable{
    private Color color;
    private Value value;

    //Konstruktor
    Card(Value value, Color color) {
        this.value = value;
        this.color = color;
    }

    public String toString(){
        return value + "_" + color;
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
