package sample;

import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Created by Lony13 on 2016-12-27.
 */
public class Game {
    private boolean gameStart;
    private int currentBet;
    private Deck theDeck;
    private Player me;
    private Dealer dealer;
    private Controller controller;

    private Button buttonStartGame;
    private Button buttonHit;
    private Button buttonStand;
    private Button buttonPlayAgain;
    private Button buttonBet;
    private TextArea textArea;
    private Slider slider;
    private TextArea textAreaMoney;

    public Game(Controller controller){
        this.controller = controller;
        gameStart = false;

        initializeGame();
        initializeComponents();
        start();
    }

    private void initializeGame(){
        theDeck = new Deck(6, true);

        me = new Player("Player", 1000);
        dealer = new Dealer();
    }


    private void initializeComponents(){
        ImageView imageTlo = controller.getImageTlo();
        buttonStartGame = controller.getButtonStartGame();
        buttonHit = controller.getButtonHit();
        buttonStand = controller.getButtonStand();
        buttonPlayAgain = controller.getButtonPlayAgain();
        buttonBet = controller.getButtonBet();
        textArea = controller.getTextArea();
        textAreaMoney = controller.getTextAreaMoney();

        slider = controller.getSlider();
        slider.setMin(0);
        slider.setMax(me.getMoney());
        slider.setValue(100);
        slider.setShowTickLabels(true);
        slider.setShowTickMarks(true)  ;
        slider.setMajorTickUnit(100);
        slider.setMinorTickCount(10);
        slider.setBlockIncrement(25);

        imageTlo.setImage(new Image("images/tlo.jpg"));

        buttonHit.setVisible(false);
        buttonStand.setVisible(false);
        buttonPlayAgain.setVisible(false);
        textAreaMoney.setVisible(true);
    }

    private void start(){
        buttonStartGame.setOnAction(event -> {
            if(!gameStart){
                gameStart = true;
                bet();
        }});
    }

    private void bet(){
        textArea.appendText("You have " + me.getMoney() + " money\n");
        textArea.appendText("Choose how much do you\nwant to bet");

        slider.setMax(me.getMoney());

        if(me.getMoney() >= 100) {
            slider.setValue(100);
            textAreaMoney.setText("100");
            currentBet = 100;
        } else{
            slider.setValue(me.getMoney());
            textAreaMoney.setText(String.valueOf(me.getMoney()));
            currentBet = me.getMoney();
        }

        slider.valueProperty().addListener((observable, oldValue, newValue) -> {
            textAreaMoney.setText(String.valueOf(newValue.intValue()));
            currentBet = (int)slider.getValue();
        });

        buttonBet.setOnAction(event -> checkBetAndStart());
    }

    private void checkBetAndStart(){
        checkBet();
        changeVisibleAfterBet();
        startDeal();
    }

    private void checkBet(){
        int valueOfBet = (int)slider.getValue();
        if(valueOfBet == 0){
            currentBet = 1;
            valueOfBet = 1;
        }

        textArea.setText("You have " + me.getMoney() + " money\n");
        textArea.appendText("You bet " + currentBet);
    }

    private void startDeal() {
        if(theDeck.getNumberOfCards() < 16){
            theDeck = new Deck(6,true);
        }

        me.getTheHand().addCard(theDeck.dealNextCard());
        dealer.getTheHand().addCard(theDeck.dealNextCard());
        me.getTheHand().addCard(theDeck.dealNextCard());
        dealer.getTheHand().addCard(theDeck.dealNextCard());

        me.printHand(controller);
        dealer.printHand(false, controller);

        if(me.getTheHand().getHandSum() >= 21) endDeal();

        buttonHit.setOnAction(event -> hit());
        buttonStand.setOnAction(event -> endDeal());
    }

    private void hit() {
        me.getTheHand().addCard(theDeck.dealNextCard());
        me.printHand(controller);

        if(me.getTheHand().getHandSum() >= 21) endDeal();
    }

    private void endDeal(){
        endDealerHand();
        clearTextArea();
        checkWinner();
        changeVisibleAfterDeal();
        playAgain();
    }

    private void endDealerHand(){
        boolean dealerDone = false;
        while(!dealerDone) {
            if (dealer.getTheHand().getHandSum() < 17) {
                dealerDone = !dealer.getTheHand().addCard(theDeck.dealNextCard());
            } else {
                dealerDone = true;
            }
        }
    }

    private void checkWinner(){
        me.printHand(controller);
        dealer.printHand(true, controller);

        int mySum = me.getTheHand().getHandSum();
        int dealerSum = dealer.getTheHand().getHandSum();

        if (dealer.compareHands(me.getTheHand()) == 1) {
            me.winMoney(currentBet);
            textArea.appendText("You win!\n" + mySum + " : " + dealerSum + "\n");
            if(mySum == 21){
                textArea.appendText("You bet: " + currentBet + "\nand you have BlackJack");
                currentBet = (int)(currentBet * 1.5);
                textArea.appendText(" so\nyou win your bet * 1.5: " + currentBet);
            }
            else {
                textArea.appendText("You bet: " + currentBet + "\nSo you win another: " + currentBet);
            }
            textArea.appendText("\nYou have now: " + me.getMoney() + " money");
        } else if (dealer.compareHands(me.getTheHand()) == -1){
            me.lostMoney(currentBet);
            textArea.appendText("Dealer win!\n" + mySum + " : " + dealerSum + "\n");
            textArea.appendText("You bet " + currentBet + " and you lost.\n");
            textArea.appendText("You have now: " + me.getMoney() + " money\n");
        } else{
            textArea.appendText("It's a tie: " + mySum + " : " + dealerSum + "\n");
            textArea.appendText("You have now: " + me.getMoney() + " money\n");
        }
    }

    private void playAgain(){
        me.checkMoney(controller);

        textArea.appendText("\nDo you want to play again?");

        buttonPlayAgain.setVisible(true);
        buttonPlayAgain.setOnAction(event -> startAgain());
    }

    private void startAgain(){
        if(me.getMoney() == 0) me.winMoney(1000);

        me.getTheHand().emptyHand();
        dealer.getTheHand().emptyHand();

        changeVisibleBeforeNewDeal();
        clearTextArea();
        clearImages();
        bet();
    }

    private void changeVisibleBeforeNewDeal(){
        textAreaMoney.setVisible(true);
        slider.setVisible(true);
        buttonBet.setVisible(true);
        buttonPlayAgain.setVisible(false);
    }

    private void changeVisibleAfterDeal(){
        slider.setMax(me.getMoney());
        buttonHit.setVisible(false);
        buttonStand.setVisible(false);
    }

    private void changeVisibleAfterBet(){
        textAreaMoney.setVisible(!(textAreaMoney.isVisible()));
        slider.setVisible(!(slider.isVisible()));
        buttonBet.setVisible(!(buttonBet.isVisible()));
        buttonStand.setVisible(!(buttonStand.isVisible()));
        buttonHit.setVisible(!(buttonHit.isVisible()));
    }

    private void clearImages(){
        ImageView[] playerImages = controller.getPlayerImages();
        for(ImageView x : playerImages){
            x.setImage(null);
        }
        ImageView[] dealerImages = controller.getDealerImages();
        for(ImageView x : dealerImages){
            x.setImage(null);
        }
    }

    private void clearTextArea(){
        textArea.setText("");
    }
}