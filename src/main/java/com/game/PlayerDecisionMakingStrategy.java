package com.game;

import java.util.Scanner;

public class PlayerDecisionMakingStrategy extends DecisionMakingStrategy{

    private Player player;

    Scanner scanner = new Scanner(System.in);

    public PlayerDecisionMakingStrategy(Player player){
        super(player);
    }

    public PlayerDecisionMakingStrategy(){
        super();
    }
    @Override
    protected String hitOrStand() {
        String decision = scanner.nextLine();
        return decision;
    }
}
