public class Card {

    private String rank;
    private String suit;

    public Card(String rank, String suit) {
        this.rank = rank;
        this.suit = suit;
    }

    public int getValue() {
        try {
            return Integer.parseInt(rank);
        } catch (NumberFormatException e) {
            if (rank.equals("J") || rank.equals("Q") || rank.equals("K") ) {
                return 10;
            } else if (rank.equals("A")) {
                return 11;
            } else {
                return -1;
            }
        }
    }

    public String getSuit() {
        return suit;
    }

    public void setSuit(String suit) {
        this.suit = suit;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }
}
