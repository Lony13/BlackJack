package sample;

/**
 * Created by Lony13 on 2016-12-27.
 */
public class Card {
    private Suit suit;

    private int value;

    public Card(Suit suit, int value) {
        this.suit = suit;
        if(value >= 1 && value <= 13) {
            this.value = value;
        } else{
            System.err.println(value + " is not a valid Card number");
            System.exit(1);
        }
    }

    public int getCardNumber(){
        int suitValue;
        if(suit == Suit.Spades) suitValue = 0;
        else if(suit == Suit.Hearts) suitValue = 1;
        else if(suit == Suit.Diamonds) suitValue = 2;
        else suitValue = 3;
        return 13*suitValue + value;
    }

    public Suit getSuit() {
        return suit;
    }

    public int getValue() {
        return value;
    }
}