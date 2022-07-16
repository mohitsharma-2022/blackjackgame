package com.game;

public abstract class DecisionMakingStrategy {

    protected abstract String hitOrStand();

    private Player player;

    public DecisionMakingStrategy(){

    }
    public DecisionMakingStrategy(Player player){
        this.player = player;
    }

    protected void setPlayer(Player player) {
        this.player = player;
    }

    public int playersPoint(){
        int total1 = 0;
        int total2 = 0;
        int[] outputArray = {total1, total2};
        int output;

        for (Card i : player.cardsInHand)
        // traverses hand ArrayList
        {
            if (i.rank.equalsIgnoreCase("Ace"))
            {
                // if the card is an Ace
                total1 += 1; // total using Ace as 1
                total2 += 11; // total using Ace as 11
            } // end if statement
            else if (i.rank.equalsIgnoreCase("Jack") || i.rank.equalsIgnoreCase( "Queen") || i.rank.equalsIgnoreCase( "King"))
            {
                // if card is a face card
                // all face cards are worth 10
                total1 += 10;
                total2 += 10;
            } // end elseif statement
            else
            {
                total1 += Integer.parseInt(i.rank);
                total2 += Integer.parseInt(i.rank);
            } // end else statement
        } // end for loop
        // a negative value in the output means that total value is a bust (over 21)

        // total 1
        boolean totalsEqual = total1 == total2;
        boolean trueTotal1 = total1 <= 21;
        boolean trueTotal2 = total2 <= 21;
        boolean bothTotalsViable = total1 <= 21 && total2 <= 21;

        if (totalsEqual)
        {
            return total1;
        }
        else
        {
            if (bothTotalsViable)
            {
                return Math.max(total1, total2);
            }
            else if (trueTotal1)
            {
                return total1;
            }
            else if (trueTotal2)
            {
                return total2;
            }
            else
            {
                return -1;
            } //if you get this you messed up

        }
    }

}
