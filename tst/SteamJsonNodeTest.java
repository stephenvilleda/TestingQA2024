import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class SteamJsonNodeTest {

    @Test
    public void testCatGame() {
        SteamJsonNode node = new SteamJsonNode("2782380");
        Assert.assertEquals("100 Cats New York", node.getGameName());
        Assert.assertEquals(0, node.getGamePrice());
        Assert.assertEquals("[windows]", Arrays.toString(node.getGamePlatforms()));
        Assert.assertEquals("Aug 27, 2024", node.getReleaseDate());
        Assert.assertEquals("[Casual, Indie, Free To Play]", Arrays.toString(node.getGenres()));
        Assert.assertEquals("[100 Cats]", Arrays.toString(node.getDevelopers()));
    }

}
