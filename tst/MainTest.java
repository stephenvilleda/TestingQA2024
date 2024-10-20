import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class MainTest {

    @Test
    public void testGames() {
        String[] appIDs = {"214950", "1245620", "1850570", "2591280", "300570"};
        Game[] games = new Game[appIDs.length];
        for (int i = 0; i < appIDs.length; i++) {
            games[i] = new Game(appIDs[i]);
        }
        for (int i = 0; i < games.length; i++) {
            games[i].printGame();
        }
    }

    @Test
    public void testGameName() {
        String name = GameUtility.getGameName("1850570");
        System.out.println(name);
    }

    @Test
    public void testGamePrice() {
        int price = GameUtility.getGamePrice("440");
        Assert.assertEquals(0, price);
        int price2 = GameUtility.getGamePrice("23423423423");
        Assert.assertEquals(-1, price2);
        int price3 = GameUtility.getGamePrice("400");
        Assert.assertTrue(price3 > 0);
    }

    @Test
    public void testGamePlatforms() {
        String[] platforms = GameUtility.getGamePlatforms("400"); // 400 is portal
        System.out.println(Arrays.toString(platforms));
        String[] platforms2 = GameUtility.getGamePlatforms("1245620"); // elden ring
        System.out.println(Arrays.toString(platforms2));
    }
}
