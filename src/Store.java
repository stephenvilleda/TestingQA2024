import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.net.URL;
import java.nio.charset.StandardCharsets;

public class Store {

    private Inventory inventory;

    // dependency injection
    public Store (Inventory inventory) {
        this.inventory = inventory;
    }

    public void loadInventoryFromWeb(String url) {
        // this link will be in the test, feed it into the method
        // https://gist.githubusercontent.com/tacksoo/349702bd06852814fba06a4df48e32d8/raw/5fb59f716e9069ac186b1994376c85823a65e335/myinventory.csv
        try {
            URL urlObj = new URL(url);
            CSVParser parser = CSVParser.parse(urlObj, StandardCharsets.UTF_8,
                    CSVFormat.DEFAULT.builder().setHeader().build());
            for (CSVRecord record : parser) {
                String id = record.get("appID");
                Game g = new Game(id);
                inventory.add(g);
            }
            parser.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Game findCheapestGameInStore(Store store) {
        Game cheap = store.inventory.findCheapestGame();
        return cheap;
    }

    public Game findMostExpensiveGame(Store store) {
        Game expensive = store.inventory.findMostExpensiveGame();
        return expensive;
    }

    public double getAveragePriceOfAllGames(Store store) {
        double average = store.inventory.printAverageOfGames();
        return average;
    }
}

