import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import java.net.URL;

public class SteamTest {
    /* games used:
     214950 = Total War: ROME II - Emperor Edition
     1245620 = ELDEN RING
     1850570 = DEATH STRANDING DIRECTOR'S CUT
     2591280 = F1® Manager 2024
     300570 = Infinifactory
     */

    private final String[] appIDs = {"214950", "1245620", "1850570", "2591280", "300570"};
    //https://store.steampowered.com/api/appdetails?appids=420&cc=us&l=en
    private final String STEAM_URL_PREFIX = "https://store.steampowered.com/api/appdetails?appids=";
    private final String STEAM_URL_SUFFIX = "&cc=us&l=en";
    private final ObjectMapper mapper = new ObjectMapper();

    @Test
    public void testGameName() throws Exception {
        // jackson databind
        // iterate over array of appIDs and print out the names
        for (int i = 0; i < appIDs.length; i++) {
            URL url = new URL(STEAM_URL_PREFIX + appIDs[i] + STEAM_URL_SUFFIX);
            JsonNode root = mapper.readTree(url);
            String name = root.get(appIDs[i]).get("data").get("name").asText();
            System.out.println(name);
        }
    }

    @Test
    public void testGameGenres() throws Exception {
        for (int i = 0; i < appIDs.length; i++) {
            URL url = new URL(STEAM_URL_PREFIX + appIDs[i] + STEAM_URL_SUFFIX);
            JsonNode root = mapper.readTree(url);
            JsonNode genres = root.get(appIDs[i]).get("data").get("genres");
            for (int j = 0; j < genres.size(); j++) {
                System.out.print(genres.get(j).get("description").asText() + " ");
            }
            System.out.println();
        }
    }

}
