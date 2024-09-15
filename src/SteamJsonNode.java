import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.URL;
import java.util.ArrayList;

public class SteamJsonNode {

    private String appID;
    private JsonNode root;
    private final String STEAM_URL_PREFIX = "https://store.steampowered.com/api/appdetails?appids=";
    private final String STEAM_URL_SUFFIX = "&cc=us&l=en";
    private ObjectMapper mapper = new ObjectMapper();

    public SteamJsonNode(String appID) {
        this.appID = appID;
        try {
            this.root = mapper.readTree(new URL(STEAM_URL_PREFIX + appID + STEAM_URL_SUFFIX));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public String getGameName() {
        String name = root.get(appID).get("data").get("name").asText();
        return name;
    }

    public String[] getGamePlatforms() {
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
    }

    public int getGamePrice() {
        boolean isFree = root.get(appID).get("data").get("is_free").asBoolean();
        if (isFree) {
            return 0;
        } else {
            return root.get(appID).get("data").get("price_overview").get("final").asInt();
        }
    }

    public String getReleaseDate() {
        return root.get(appID).get("data").get("release_date").get("date").asText();
    }

    public String[] getGenres() {
        ArrayList<String> genres = new ArrayList<>();
        JsonNode genresNode = root.get(appID).get("data").get("genres");
        for (JsonNode genre : genresNode) {
            genres.add(genre.get("description").asText());
        }
        return genres.toArray(new String[genres.size()]);
    }

    public String[] getDevelopers() {
        ArrayList<String> developers = new ArrayList<>();
        JsonNode developersNode = root.get(appID).get("data").get("developers");
        for (JsonNode developer : developersNode) {
            developers.add(developer.asText());
        }
        return developers.toArray(new String[developers.size()]);
    }

    public String[] getRating() {
        //TODO
        
        return null;
    }


}
