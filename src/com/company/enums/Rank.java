package com.company.enums;

public enum Rank {
    TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING, ACE;

    public int rank() {
        return this.ordinal() + 2;
    }
}
