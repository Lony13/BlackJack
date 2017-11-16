package sample;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

/**
 * Created by Lony13 on 2016-12-27.
 */
public class Player {
    private String name;
    private Hand theHand;
    private int money;

    public Player(String name, int money) {
        if(money < 0) {
            System.err.println(money + " is not a valid value of money");
            System.exit(1);
        }
        this.name = name;
        theHand = new Hand();
        this.money = money;
    }

    public void printHand(Controller controller){
        ImageView[] imagesArray = controller.getPlayerImages();
        for(int iterator = 0; iterator < this.theHand.getNumberOfCards(); iterator++){
            Card card = theHand.getTheHand()[iterator];
            int cardNumber = card.getCardNumber();

            imagesArray[iterator].setImage(new Image("cards/" + cardNumber + ".png"));
        }
    }

    public void winMoney(int bet){
        if(theHand.getHandSum() == 21)
            money = (int) (money + bet*1.5);
        else money = money + bet;
    }

    public void lostMoney(int bet){
        money = money - bet;
    }

    public boolean checkMoney(Controller controller) {
        if (money > 0) return true;
        else {
            controller.getTextArea().appendText("You don't have more money :(");
            return false;
        }
    }

    public Hand getTheHand() {
        return theHand;
    }

    public int getMoney() {
        return money;
    }

    public String getName() {
        return name;
    }
}