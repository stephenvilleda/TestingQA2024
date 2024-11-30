import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

public class InventoryMockTest {

    @Test
    public void testCheapestGame() {
        InventoryInterface myGames = Mockito.mock(InventoryInterface.class);
        Game g = new Game("420");
        g.setName("Madden NFL");
        Mockito.when(myGames.findCheapestGame()).thenReturn(g);

        Assert.assertEquals("Madden NFL", myGames.findCheapestGame().getName());
    }

    @Test
    public void testMostExpensiveGame() {
        InventoryInterface myGames = Mockito.mock(InventoryInterface.class);
        Game g = new Game("420");
        g.setName("Super Mario Bros");
        Mockito.when(myGames.findMostExpensiveGame()).thenReturn(g);

        Assert.assertEquals("Super Mario Bros", myGames.findMostExpensiveGame().getName());
    }

    @Test
    public void testAveragePriceOfAllGames() {
        InventoryInterface myGames = Mockito.mock(InventoryInterface.class);
        Mockito.when(myGames.getAveragePriceOfAllGames()).thenReturn(60.0);

        Assert.assertEquals(60.0, myGames.getAveragePriceOfAllGames(), 0.01);

    }
}
