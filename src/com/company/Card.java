package com.company;

public class Card {
    private final Suit suit;
    private final Rank rank;


    public enum Suit{
        CLUB, SPADE, HEART, DIAMOND;
    }
    public enum Rank{
        TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING, ACE;

        public int rank(){
            return this.ordinal() + 2;
        }
    }

    public Card(Rank rank, Suit suit){
        this.rank = rank;
        this.suit = suit;
    }
}

/*package card;

public class Card implements Comparable<Card>{

    private final Suit suit;
    private final Rank rank;


    public enum Suit{
        HEART, DIAMOND, CLUB, SPADE;
    }
    public enum Rank{
        TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING, ACE;

        public int rank(){
            return this.ordinal() + 2;
        }
    }


    public Card(Rank rank, Suit suit){
        this.rank = rank;
        this.suit = suit;
    }

    public int compareTo(Card card){
        return this.rank.compareTo(card.rank);
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


    public static void main(String [] args){
        Card c = new Card(Rank.TWO, Suit.HEART);
        Card c2 = new Card(Rank.TEN,Suit.HEART);

        System.out.println(c);
        System.out.println(c.rank.compareTo(c2.rank));
    }
}*/