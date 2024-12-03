import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class BlackjackTest {

    @Test
    public void testHandValue() {
        Card twoOfHearts = new Card("2","H");
        Card threeOfDiamonds = new Card("3","D");
        for (int i = 2; i <= 10; i++) {
            Card c = new Card(String.valueOf(i), "H");
            Assert.assertEquals(i, c.getValue());
        }
        Card jack = new Card("J","D");
        Assert.assertEquals(10, jack.getValue());
        Card queen = new Card("Q","S");
        Assert.assertEquals(10, queen.getValue());
        Card ace = new Card("A","H");
        Assert.assertEquals(11, ace.getValue());
    }

    @Test
    public void testSimpleBlackjack() {
        Blackjack game = new Blackjack();
        Card twoOfHearts = new Card("2","H");
        Card threeOfDiamonds = new Card("3","D");
        List<Card> cards = new ArrayList<Card>();
        cards.add(twoOfHearts);
        cards.add(threeOfDiamonds);
        int score = game.calculateHand(cards);
        Assert.assertEquals(5, game.calculateHand(cards));
    }

    @Test
    public void testAce() {
        Blackjack game = new Blackjack();
        Card aceOfSpades = new Card("A","S");
        Card aceOfHearts = new Card("A","H");
        List<Card> cards = new ArrayList<>();
        cards.add(aceOfSpades);
        cards.add(aceOfHearts);
        Assert.assertEquals(12, game.calculateHand(cards));
    }

    @Test
    public void testMultipleAces() {
        Blackjack game = new Blackjack();
        Card aceOfSpades = new Card("A","S");
        Card aceOfHearts = new Card("A","H");
        Card aceOfDiamonds = new Card("A","D");
        Card aceOfClubs = new Card("A","C");
        Card kingOfHearts = new Card("K","H");
        //Card kingOfDiamonds = new Card("K","D");
        List<Card> cards = new ArrayList<>();
        cards.add(aceOfSpades);
        cards.add(aceOfHearts);
        cards.add(aceOfDiamonds);
        cards.add(aceOfClubs);
        cards.add(kingOfHearts);
        //cards.add(kingOfDiamonds);
        Assert.assertEquals(14, game.calculateHand(cards));
    }


}
