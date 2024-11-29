import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

public class InventoryTest {

    private Inventory inventory;

    @Before
    public void setUp() {
        inventory = new Inventory();
        Game g1 = new Game("1934680"); // age of mythology :retold
        Game g2 = new Game("1245620"); // elden ring
        Game g3 = new Game("1091500"); // cyberpunk
        inventory.add(g1);
        inventory.add(g2);
        inventory.add(g3);
    }

    @Test
    @Ignore
    public void testInventory() {
        Inventory inventory = new Inventory();
        Assert.assertEquals(0, inventory.getSize());
        Game stardew = new Game("413150");
        inventory.add(stardew);
        Assert.assertEquals(1, inventory.getSize());
        inventory.add(stardew);
        Assert.assertEquals(1, inventory.getSize());
        inventory.remove(stardew);
        Assert.assertEquals(0, inventory.getSize());
    }

    @Test
    @Ignore
    public void testAddGame() {
        // try adding same game
        Game repeatGame = new Game("1934680");
        inventory.add(repeatGame);
        Assert.assertEquals(3, inventory.getSize());
        // try adding different game
        Game nba = new Game("2878980");
        inventory.add(nba);
        Assert.assertEquals(4, inventory.getSize());
    }

    @Test
    public void testRemoveGame() {
        // remove game
        inventory.remove(new Game("1245620"));
        Assert.assertEquals(2, inventory.getSize());
        // remove again
        inventory.remove(new Game("1245620"));
        Assert.assertEquals(2, inventory.getSize());
        // remove game not in inventory
        inventory.remove(new Game("2878980"));
        Assert.assertEquals(2, inventory.getSize());
    }

    @Test
    public void testCheapestGame() {
        Game cheapestGame = inventory.findCheapestGame();
        Assert.assertEquals("Age of Mythology: Retold", cheapestGame.getName());
        Game stackLands = new Game("1948280");
        inventory.add(stackLands);
        cheapestGame = inventory.findCheapestGame();
        Assert.assertEquals("Stacklands", cheapestGame.getName());
        // try a free game
        Game tf2 = new Game("440");
        inventory.add(tf2);
        cheapestGame = inventory.findCheapestGame();
        Assert.assertEquals("Team Fortress 2", cheapestGame.getName());
        // try an empty inventory
        Inventory emptyInventory = new Inventory();
        cheapestGame = emptyInventory.findCheapestGame();
        Assert.assertNull(cheapestGame);
    }

    @Test
    public void testFindMostHighlyRatedGame() {
        Game highestRatedGame = inventory.findMostHighlyRatedGame();
        Assert.assertEquals("ELDEN RING", highestRatedGame.getName());
        Game mostPopular = new Game("105600"); // Terraria
        inventory.add(mostPopular);
        highestRatedGame = inventory.findMostHighlyRatedGame();
        Assert.assertEquals("Terraria", highestRatedGame.getName());
    }

    @Test
    public void testPrintAveragePriceOfAllGames() {
        Assert.assertEquals(4999.0, inventory.printAverageOfGames(), 0.001);
        System.out.print("The average price of all games is: ");
        System.out.println(inventory.printAverageOfGames());
    }
}
