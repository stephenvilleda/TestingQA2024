import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.sql.*;
import java.util.List;

public class NewsTest {

    private static Connection connection;
    private static WebDriver driver;
    private final static String DB_URL = "jdbc:sqlite:news.sqlite";

    @BeforeClass
    public static void setUp() throws Exception {
        connection = DriverManager.getConnection(DB_URL);
        driver = new FirefoxDriver();
    }

    @Test
    public void testNews() {
        driver.get("https://news.ycombinator.com");
        List<WebElement> titleLines = driver.findElements(By.className("titleline"));
        List<WebElement> ages = driver.findElements(By.className("age"));

        int startSize = getNewsTableSize();
        for (int i = 0; i < titleLines.size(); i++) {
            WebElement title = titleLines.get(i);
            WebElement anchor = title.findElement(By.tagName("a"));
            String url = anchor.getAttribute("href");
            String titleText = anchor.getText();
            WebElement age = ages.get(i);
            String ageText = age.getAttribute("title");
            insertNews(titleText, url, ageText);
        }
        int endSize = getNewsTableSize();
        Assert.assertEquals(30, endSize-startSize);
    }

    private void insertNews(String title, String url, String timestamp) {
        String sql = "INSERT INTO news (title, url, timestamp) VALUES (?, ?, ?)";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, title);
            ps.setString(2, url);
            ps.setString(3, timestamp);
            ps.executeUpdate();
            ps.close();
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }


    private int getNewsTableSize() {
        String sql = "SELECT COUNT(*) FROM news";
        int size = 0;
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            rs.next();
            size = rs.getInt(1);
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return size;
    }


    @AfterClass
    public static void tearDown() throws Exception {
        driver.quit();
        connection.close();
    }

}
