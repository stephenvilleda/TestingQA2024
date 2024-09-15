import java.util.ArrayList;

public class Inventory {

    private ArrayList<Game> games;

    public Inventory() {
        games = new ArrayList<>();
    }
    public int getSize() {
        return games.size();
    }

    public void add(Game game) {
        // check if inventory already has the game.
        for (int i =0; i < games.size(); i++) {
            String appID = games.get(i).getAppID();
            if (appID.equals(game.getAppID())) {
                return;
            }
        }
        games.add(game);
    }

    public void remove(Game game) {
        // check if game exists
        for (int i = 0; i < games.size(); i++) {
            String appID = games.get(i).getAppID();
            if (appID.equals(game.getAppID())) {
                games.remove(game);
            }
            return;
        }
    }
}
