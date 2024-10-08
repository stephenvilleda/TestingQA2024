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
    }


    public void printGame() {
        System.out.println(Arrays.toString(this.platforms));
        System.out.println(this.name);
        System.out.println(this.releaseDate);
        System.out.println(Arrays.toString(this.developers));
        System.out.println(Arrays.toString(this.genres));
        String priceString = Integer.toString(this.price);
        String prettyPrice = "$" + priceString.substring(0, priceString.length() - 2) +
                "." + priceString.substring(priceString.length() - 2);
        System.out.println(prettyPrice);
        System.out.println("");
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

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Game game = (Game) object;
        return price == game.price && Objects.equals(appID, game.appID) && Objects.equals(name, game.name) && Arrays.equals(platforms, game.platforms) && Objects.equals(releaseDate, game.releaseDate) && Arrays.equals(developers, game.developers) && Arrays.equals(genres, game.genres) && Arrays.equals(rating, game.rating);
    }
}
