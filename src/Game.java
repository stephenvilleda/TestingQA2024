import java.util.Arrays;
import java.util.Objects;

public class Game {

    private String appID;
    private String name;
    private String[] platforms;
    private String releaseDate;
    private String[] developers;
    private String[] genres;
    int price;
    private String[] rating;
    private int recommendations;

    public Game(String appID) {
        this.appID = appID;
        //initialize the other fields
        SteamJsonNode root = new SteamJsonNode(appID);
        this.name = root.getGameName();
        this.platforms = root.getGamePlatforms();
        this.releaseDate = root.getReleaseDate();
        this.developers = root.getDevelopers();
        this.genres = root.getGenres();
        this.price = root.getGamePrice();
        this.rating = root.getRating();
        this.recommendations = root.getGameRecommendations();
    }


    public void printGame() {
        System.out.printf("Platform: %s\n", (Arrays.toString(this.platforms)));
        System.out.printf("Name: %s\n", this.name);
        System.out.printf("Release Date: %s\n", (this.releaseDate));
        System.out.printf("Developers: %s\n", (Arrays.toString(this.developers)));
        System.out.printf("Genres: %s\n", (Arrays.toString(this.genres)));
        String priceString = Integer.toString(this.price);
        String prettyPrice = "$" + priceString.substring(0, priceString.length() - 2) +
                "." + priceString.substring(priceString.length() - 2);
        System.out.printf("Price: %s\n", (prettyPrice));
        System.out.printf("Rating: %s\n", (Arrays.toString(this.rating)));
        System.out.println("---------------------------------------------");
    }

    public String getAppID() {
        return appID;
    }

    public void setAppID(String appID) {
        this.appID = appID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPlatforms(String[] platforms) {
        this.platforms = platforms;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String[] getDevelopers() {
        return developers;
    }

    public void setDevelopers(String[] developers) {
        this.developers = developers;
    }

    public String[] getGenres() {
        return genres;
    }

    public void setGenres(String[] genres) {
        this.genres = genres;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String[] getRating() {
        return rating;
    }

    public void setRating(String[] rating) {
        this.rating = rating;
    }

    public int getRecommendations() {
        return recommendations;
    }

    public void setRecommendations(int recommendations) {
        this.recommendations = recommendations;
    }


        @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Game)) return false;
        Game game = (Game) o;
        return Objects.equals(appID, game.appID);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(appID);
    }
}
