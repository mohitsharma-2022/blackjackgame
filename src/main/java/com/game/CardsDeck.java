package com.game;

import java.util.ArrayList;
import java.util.List;

public class CardsDeck {

    String[] SUITS = {
            "Clubs", "Diamonds", "Hearts", "Spades"
    };

    String[] RANKS = {
            "2", "3", "4", "5", "6", "7", "8", "9", "10",
            "Jack", "Queen", "King", "Ace"
    };

   List<Card> cards;

    public CardsDeck(){
        initialise();
    }


    public void reset(){
        initialise();
    }

    private void initialise(){
        cards = new ArrayList<>();
        for (int i = 0; i < RANKS.length; i++) {
            for (int j = 0; j < SUITS.length; j++) {
                Card card = new Card(SUITS[j], RANKS[i]);
                cards.add(card);
            }
        }
    }

    public void shuffle(){
        List<Card> shuffledCards = new ArrayList<>();
        for (int i = 0; i < cards.size(); i++) {
            int r = i + (int) (Math.random() * (cards.size()-i));
            shuffledCards.add(cards.remove(r));
        }
        cards = shuffledCards;
    }

    public Card getCard(){
        return this.cards.remove((int) (Math.random()*this.cards.size()));
    }
}
