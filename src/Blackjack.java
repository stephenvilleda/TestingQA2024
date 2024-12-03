import java.util.List;

public class Blackjack {

    public static int calculateHand(List<Card> cards) {
        int total = 0;
        int aces = 0;

        for (Card card : cards) {
            int value = card.getValue();
            total = total + value;
            if (card.getRank().equals("A")) {
                aces = aces + 1;
            }
        }

        // Adjust for Aces if total is over 21
        while (total > 21 && aces > 0) {
            total = total - 10;
            aces--;
        }

        return total;
    }
}
