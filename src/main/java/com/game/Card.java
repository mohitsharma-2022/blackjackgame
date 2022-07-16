package com.game;

public class Card {

    String suit;

    String rank;

    public Card(String suit, String rank){
        this.suit = suit;
        this.rank = rank;
    }

    public String getSuit() {
        return suit;
    }

    public String getRank() {
        return rank;
    }

    public String showCard(){
        return "[" + rank + " of " + suit + "]";
        // returns the card in the format [3 of ♥]
    }
}
