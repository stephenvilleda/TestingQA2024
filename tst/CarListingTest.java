import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.sql.*;
import java.time.Duration;
import java.util.Date;
import java.util.List;

public class CarListingTest {

    private static Connection connection;
    private static WebDriver driver;

    @BeforeClass
    public static void setUp() throws Exception {
        connection = DriverManager.getConnection("jdbc:sqlite:cars.sqlite");
        driver = new ChromeDriver();
    }

    @Ignore
    @Test
    public void testInsert() throws Exception{
        String sql = "insert into cars values(null, ?, ?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, "Tesla Model Y");
        statement.setString(2, "https://www.tesla.com");
        statement.setInt(3, 43000);
        statement.setString(4, "10/17/2024");
        statement.execute();
        statement.close();
    }

    @Ignore
    @Test
    public void testTableSize() {
        int size = getCarTableSize();
        System.out.println(size);
        Assert.assertTrue(size >= 0);
    }

    @Test
    public void testCarListing() {
        int startCount = getCarTableSize();
        driver.get(" https://atlanta.craigslist.org/d/cars-trucks/search/cta");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        List<WebElement> cards = driver.findElements(By.className("gallery-card"));
        Assert.assertEquals(120, cards.size());

        for (int i = 0; i < cards.size(); i++) {
            WebElement card = cards.get(i);
            String subject = card.findElement(By.className("label")).getText();
            int price = 0;
            try {
                String priceinfo = card.findElement(By.className("priceinfo")).getText();
                price = Integer.parseInt(priceinfo.replace("$", "").replace(",", ""));
            } catch (Exception e) {
                price = -1;
            }
            String url = card.findElement(By.tagName("a")).getAttribute("href");
            insertIntoTable(subject, url, price);
        }
        int endCount = getCarTableSize();
        Assert.assertEquals(120, endCount - startCount);
    }

    private void insertIntoTable(String subject, String url, int price) {
        String sql = "insert into cars values(null, ?, ?, ?, ?)";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, subject);
            ps.setString(2, url);
            ps.setInt(3, price);
            String now = new Date().toString();
            ps.setString(4, now);
            ps.execute();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private int getCarTableSize() {
        int count = 0;
        try {
            String sql = "select count(*) from cars";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            count = rs.getInt("count(*)");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    @AfterClass
    public static void tearDown() throws Exception {
        connection.close();
    }

    /*
    test  case verifies that the table is increased when,
    the testCarListing() method runs and adds 120 new entries to the database
     */
    @Test
    public void testCarsUpdated () {
        int current = getCarTableSize();
        testCarListing();
        int updated = getCarTableSize();
        Assert.assertTrue(updated > current);
    }

}
