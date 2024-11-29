import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.SerializationUtils;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

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
        String name = GameUtility.getGameName("23423423423");
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

    @Test
    public void testFileUtils() throws IOException {
        List<String> lines = FileUtils.readLines(new File("text/jesus3.txt"), StandardCharsets.UTF_8);
        System.out.println(lines.size());
    }

    @Test
    public void testSerilization() throws IOException {
        String[] attending =  {"alex", "ali", "ani", "bao", "huy", "isai", "james", "jean", "minh", "nikki", "woody"};
        byte [] data = SerializationUtils.serialize(attending);
        FileUtils.writeByteArrayToFile(new File("names.ser"), data);

        byte [] restoredData = FileUtils.readFileToByteArray(new File("names.ser"));
        String[] names = SerializationUtils.deserialize(restoredData);

        Assert.assertArrayEquals(attending, names);
        System.out.println(Arrays.toString(attending));
    }

}
