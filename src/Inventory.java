import java.util.ArrayList;

public class Inventory {

    private final ArrayList<Game> games;

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
                return;
            }
        }
    }

    public Game findCheapestGame() {
        if (games.isEmpty()) {
            return null;
        }
        Game cheapestGame = games.get(0);
        for (int i = 1; i < games.size(); i++) {
            Game game = games.get(i);
            if (game.getPrice() < cheapestGame.getPrice()) {
                cheapestGame = game;
            }
        }
        return cheapestGame;
    }

    public Game findMostExpensiveGame() {
        if (games.isEmpty()) {
            return null;
        }
        Game expensive = games.get(0);
        for (int i = 1; i < games.size(); i++) {
            Game game = games.get(i);
            if (game.getPrice() > expensive.getPrice()) {
                expensive = game;
            }
        }
        return expensive;
    }

    public Game findMostHighlyRatedGame() {
        if (games.isEmpty()) {
            return null;
        }
        Game mostHighlyRatedGame = games.get(0);
        for (int i = 1; i < games.size(); i++) {
            Game game = games.get(i);
            if (game.getRecommendations() > mostHighlyRatedGame.getRecommendations()) {
                mostHighlyRatedGame = game;
            }
        }
        return mostHighlyRatedGame;
    }

    public double printAverageOfGames() {
        if (games.isEmpty()) {
            return 0;
        }
        double total = 0;
        for (int i = 0; i < games.size(); i++) {
            Game game = games.get(i);
            total += game.getPrice();
        }
        double average = total / games.size();
        return average;
    }
}
