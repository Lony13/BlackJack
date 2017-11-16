package sample;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Created by Lony13 on 2016-12-27.
 */
public class Dealer {
    private static final String name = "Dealer";
    private Hand theHand;

    public Dealer(){
        theHand = new Hand();
    }

    public void printHand(boolean showFirstCard, Controller controller){
        ImageView[] imagesArray = controller.getDealerImages();

        for(int iterator = 0; iterator < this.theHand.getNumberOfCards(); iterator++){
            Card card = theHand.getTheHand()[iterator];
            int cardNumber = card.getCardNumber();

            if(iterator == 0 && !showFirstCard){
                imagesArray[iterator].setImage(new Image("cards/0.png"));
            } else {
                imagesArray[iterator].setImage(new Image("cards/" + cardNumber + ".png"));
            }
        }
    }

    public int compareHands(Hand player){
        int playerSum = player.getHandSum();
        int dealerSum = theHand.getHandSum();

        if(playerSum == 21 || (playerSum < 21 && dealerSum > 21) || (playerSum > dealerSum && playerSum < 21)){
            return 1;
        } else if(playerSum > 21 || (playerSum < dealerSum && dealerSum <= 21)){
            return -1;
        } else{
            return 0;
        }
    }

    public String getName() {
        return name;
    }

    public Hand getTheHand() {
        return theHand;
    }
}