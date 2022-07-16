package com.game;

public class DealerDecisionMakingStrategy extends DecisionMakingStrategy{

    public DealerDecisionMakingStrategy(){
        super();
    }
    @Override
    protected String hitOrStand() {
        double value;
        int playersPoint = playersPoint();
        if (2 <= playersPoint && playersPoint <= 10)
        {
            value = 1.00;
        }
        else if (11 <= playersPoint && playersPoint <= 14)
        {
            value = 0.66;
        }
        else if (15 <= playersPoint && playersPoint <= 17)
        {
            value = 0.33;
        }
        else if (18 <= playersPoint && playersPoint <= 19)
        {
            value = 0.05;
        }
        else if (playersPoint == 20)
        {
            value = 0.01;
        }
        else
        {
            value = 0.00;
        }

        //Hit
        if (Math.random() < value)
        {
            return "hit";
        }
        else
        {
            return "stand";
        }
    }
}
