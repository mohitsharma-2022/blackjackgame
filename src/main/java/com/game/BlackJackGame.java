package com.game;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BlackJackGame {

    List<Player> players;

    Dealer dealer;

    CardsDeck cardsDeck;

    List<String> gameStatus = new ArrayList<>();

    public BlackJackGame(List<Player> players){
        this.players = players;
        this.dealer = new Dealer(new DealerDecisionMakingStrategy());
        this.dealer.setName("computer");
        this.players.add(this.dealer);
        this.cardsDeck = new CardsDeck();
    }

    public BlackJackGame(Integer noOfPlayers){
        this.initPlayers(noOfPlayers);
        this.dealer = new Dealer(new DealerDecisionMakingStrategy());
        this.dealer.setName("computer");
        this.players.add(this.dealer);
        this.cardsDeck = new CardsDeck();
    }

    private void initPlayers(Integer noOfPlayers){
        players = new ArrayList<>();
        for(int i =0; i < noOfPlayers ; i++){
            Player player = new Player(new PlayerDecisionMakingStrategy());
            player.setName("Player "+(i+1));
            players.add(player);
        }
    }

    public void startGame(){
        this.cardsDeck.shuffle();
        boolean isGameOver = false;
        for(Player player : players){
            Card card = this.cardsDeck.getCard();
            player.addCard(card);
            System.out.println("Dealing to "+player.getName()+", card: "+getCards(player));
        }
        do{
             int activePlayersCount = 0;
             for(Player player : players){
                 if(!player.isActive()){
                     activePlayersCount++;
                     continue;
                 }
                 if(!player.equals(dealer)) {
                     System.out.println("Dealing to " + player.getName() + ", card: " + getCards(player) + ". Hit or Stand?");
                 }
                 boolean isHit = player.isHit();

                 if(isHit){
                     player.addCard(this.cardsDeck.getCard());
                     if(player.equals(dealer)){
                         System.out.println("Dealing to " + player.getName() + ", card: " + getCards(player) + ". Dealer hits.");
                     }else {
                         System.out.println("Dealing to " + player.getName() + ", card: " + getCards(player) + "." + (player.getTotalValue() > 21 ? "Busted over 21." : ""));
                     }
                 }else{
                     player.setActive(false);
                 }
             }
             isGameOver = (activePlayersCount == players.size());
        }while(isGameOver);
        checkPlayersScores();
    }

    private String getCards(Player player){
        String cards = "";
        return player.cardsInHand.stream().map(card -> {
            return card.rank+" "+card.suit;
        }).collect(Collectors.joining(","));
    }
    private void checkPlayersScores(){
        this.players.remove(dealer);

        for(Player player : players){
            displayScores(player);
        }
    }

    private String displayScores(Player player){
        String scoreMessage = null;
        if(dealer.getTotalValue() > 21 && player.getTotalValue() < 21){
            scoreMessage = "Scoring "+player.getName()+" has "+player.getTotalValue()+", Dealer busted."+player.getName()+" wins.";
        }else if(dealer.getTotalValue() > 21 && player.getTotalValue() > 21){
            scoreMessage = "Scoring "+player.getName()+". Dealer busted. Nobody wins.";
        }else if(dealer.getTotalValue() < 21 && player.getTotalValue() > 21){
            scoreMessage = "Scoring "+player.getName()+"busted, Dealer wins.";
        }else if(dealer.getTotalValue() < 21 && player.getTotalValue() < 21 && dealer.getTotalValue() > player.getTotalValue()){
            scoreMessage = "Scoring "+player.getName()+" has "+player.getTotalValue()+", Dealer has "+dealer.getTotalValue()+". Dealer wins";
        }else if(dealer.getTotalValue() < 21 && player.getTotalValue() < 21 && dealer.getTotalValue() < player.getTotalValue()){
            scoreMessage = "Scoring "+player.getName()+" has "+player.getTotalValue()+", Dealer has "+dealer.getTotalValue()+"."+player.getName()+" wins";
        }else if(dealer.getTotalValue() < 21 && player.getTotalValue() < 21 && dealer.getTotalValue() == player.getTotalValue()){
            scoreMessage = "Scoring "+player.getName()+" has "+player.getTotalValue()+". Dealer has "+dealer.getTotalValue()+". Tie between dealer and "+player.getName()+". Dealer wins.";
        }
        System.out.println(scoreMessage);
        gameStatus.add(scoreMessage);
        return scoreMessage;
    }

    public List<String> getGameStatus() {
        return gameStatus;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public static void main(String args[]){

          BlackJackGame blackJackGame = new BlackJackGame(3);
          blackJackGame.startGame();
    }
}
