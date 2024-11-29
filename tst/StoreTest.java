import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class StoreTest {

    private Inventory inventory;
    private Store store;
    @Before
    public void setUp() throws Exception {
        inventory = new Inventory();
        store = new Store(inventory);
        store.loadInventoryFromWeb("https://gist.githubusercontent.com/tacksoo/349702bd06852814fba06a4df48e32d8/raw/5fb59f716e9069ac186b1994376c85823a65e335/myinventory.csv");
    }

    @Test
    public void testStore() {
        Assert.assertEquals(3, inventory.getSize());
    }

    @Test
    public void testFindCheapestGameInStore() {
        String cheap = store.findCheapestGameInStore(store).getName();
        Assert.assertEquals("Lethal Company", store.findCheapestGameInStore(store).getName());
        System.out.printf("The cheapest game in store is: %s%n", cheap);
    }

    @Test
    public void testFindMostExpensiveGameInStore() {
        String expensive = store.findMostExpensiveGame(store).getName();
        Assert.assertEquals("Baldur's Gate 3", store.findMostExpensiveGame(store).getName());
        System.out.printf("The most expensive game is %s%n", expensive);
    }

    @Test
    public void testGetAveragePriceOfAllGames() {
        double average = store.getAveragePriceOfAllGames(store);
        String averageString = String.format("%.0f", average);
        String formattedAverage = averageString.substring(0, averageString.length() - 2) + "." + averageString.substring(averageString.length() - 2);
        System.out.printf("The average price of all games is: $%s%n", formattedAverage);
        Assert.assertEquals(3332, store.getAveragePriceOfAllGames(store), 0.34);
    }
}
