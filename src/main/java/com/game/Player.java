package com.game;

import java.util.ArrayList;
import java.util.List;

public class Player {

    String name;
    List<Card> cardsInHand;

    private DecisionMakingStrategy decisionMakingStrategy;

    private boolean active = true;

    public Player(){
        cardsInHand = new ArrayList<>();
        decisionMakingStrategy = new PlayerDecisionMakingStrategy(this);
    }

    public Player(DecisionMakingStrategy decisionMakingStrategy){
        cardsInHand = new ArrayList<>();
        this.decisionMakingStrategy = decisionMakingStrategy;
        this.decisionMakingStrategy.setPlayer(this);
    }

    public void addCard(Card card){
        this.cardsInHand.add(card);
    }

    protected boolean isHit(){
        String hitOrStand = decisionMakingStrategy.hitOrStand();
        if("hit".equalsIgnoreCase(hitOrStand)){
            return true;
        }else{
            return false;
        }
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public int getTotalValue(){
       return this.decisionMakingStrategy.playersPoint();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
