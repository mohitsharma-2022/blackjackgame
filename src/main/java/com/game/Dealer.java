package com.game;

import java.util.List;

public class Dealer extends Player {

    List<Card> cardsInHand;

    public Dealer(DecisionMakingStrategy decisionMakingStrategy){
       super(decisionMakingStrategy);
    }
}
