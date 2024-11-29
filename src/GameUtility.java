import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.URL;
import java.util.ArrayList;

public class GameUtility {

    private static final String STEAM_URL_PREFIX = "https://store.steampowered.com/api/appdetails?appids=";
    private static final String STEAM_URL_SUFFIX = "&cc=us&l=en";
    private static final ObjectMapper mapper = new ObjectMapper();

    public static String getGameName(String appID) {
        try {
            URL url = new URL(STEAM_URL_PREFIX + appID + STEAM_URL_SUFFIX);
            JsonNode root = mapper.readTree(url);
            String name = root.get(appID).get("data").get("name").asText();
            return name;
        } catch (Exception e) {
            return null;
        }
    }

    public static String[] getGamePlatforms(String appID) {
        try {
            URL url = new URL(STEAM_URL_PREFIX + appID + STEAM_URL_SUFFIX);
            JsonNode root = mapper.readTree(url);
            ArrayList<String> platforms = new ArrayList<>();
            JsonNode platformsNode = root.get(appID).get("data").get("platforms");
            if (platformsNode.get("windows").asBoolean()) {
                platforms.add("windows");
            }
            if (platformsNode.get("linux").asBoolean()) {
                platforms.add("linux");
            }
            if (platformsNode.get("mac").asBoolean()) {
                platforms.add("mac");
            }
            return platforms.toArray(new String[platforms.size()]);
        } catch (Exception e) {
            return null;
        }
    }

    public static int getGamePrice(String appID) {
        try {
            URL url = new URL(STEAM_URL_PREFIX + appID + STEAM_URL_SUFFIX);
            JsonNode root = mapper.readTree(url);
            boolean isFree = root.get(appID).get("data").get("is_free").asBoolean();
            if (isFree) {
                return 0;
            } else {
                return root.get(appID).get("data").get("price_overview").get("final").asInt();
            }
        } catch (Exception e) {
            return -1;
        }
    }

}
