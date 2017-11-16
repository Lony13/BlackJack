package sample;
import java.util.Random;

/**
 * Created by Lony13 on 2016-12-27.
 */
public class Deck {
    private Card[] deckOfCards;
    private int numberOfCards;

    public Deck(){
        this(1, false);
    }

    public Deck(int numberOfDecks, boolean toShuffle) {
        this.numberOfCards = numberOfDecks * 52;
        this.deckOfCards = new Card[this.numberOfCards];

        int cardIndex = 0;
        for(int deckNumber = 0; deckNumber < numberOfDecks; deckNumber++){
            for(int cardColor = 0; cardColor < 4; cardColor++){
                for(int cardValue = 1; cardValue <= 13; cardValue++){
                    this.deckOfCards[cardIndex] = new Card(Suit.values()[cardColor], cardValue);
                    cardIndex++;
                }
            }
        }

        if(toShuffle){
            this.shuffle();
        }
    }

    public void shuffle(){
        Random rand = new Random();
        Card tmp;

        int randomCard;
        for(int index = 0; index < this.numberOfCards; index++){
            randomCard = rand.nextInt(this.numberOfCards);
            tmp = this.deckOfCards[index];
            this.deckOfCards[index] = this.deckOfCards[randomCard];
            this.deckOfCards[randomCard] = tmp;
        }
    }

    public Card dealNextCard(){
        Card top = this.deckOfCards[numberOfCards - 1];
        this.numberOfCards--;
        return top;
    }

    public int getNumberOfCards() {
        return numberOfCards;
    }
}