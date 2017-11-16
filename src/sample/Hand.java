package sample;

/**
 * Created by Lony13 on 2016-12-27.
 */
public class Hand {
    private Card[] theHand = new Card[11];
    private int numberOfCards;

    public Hand(){
        emptyHand();
    }

    public void emptyHand(){
        for(int i = 0; i < 11; i++){
            this.theHand[i] = null;
        }
        this.numberOfCards = 0;
    }

    public boolean addCard(Card theCard){
        if(this.numberOfCards == 11){
            System.err.println("Can't add another card");
            System.exit(1);
        }

        this.theHand[this.numberOfCards] = theCard;
        this.numberOfCards++;

        return (this.getHandSum() <= 21);
    }

    public int getHandSum(){
        int handSum = 0;
        int cardValue;
        int numberOfAces = 0;
        for(int i = 0; i < this.numberOfCards; i++) {
            cardValue = this.theHand[i].getValue();

            if (cardValue == 1) {
                numberOfAces++;
                handSum = handSum + 11;
            } else if (cardValue > 10) {
                handSum = handSum + 10;
            } else {
                handSum = handSum + cardValue;
            }
        }

        while(handSum > 21 && numberOfAces > 0){
            handSum = handSum - 10;
            numberOfAces--;
        }

        return handSum;
    }

    public Card[] getTheHand() {
        return theHand;
    }

    public int getNumberOfCards() {
        return numberOfCards;
    }
}