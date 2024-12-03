import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.sql.DriverManager;
import java.util.List;

public class HotelDataTest {

    private static Connection connection;
    private static WebDriver driver;
    public static String DB_URL = "jdbc:sqlite:hotels.sqlite";
    String[] hotelChains = {"Hampton Inn", "Holiday Inn Express", "Residence Inn", "Courtyard by Marriott", "Hilton Garden Inn"};
    String[] cities = {"Nashville", "Boston", "Dallas", "Atlanta", "New Orleans"};

    @BeforeClass
    public static void setUp() throws Exception {
        connection = DriverManager.getConnection(DB_URL);
        driver = new ChromeDriver();
    }

    @Test
    public void testHotels() {
        try {
            String createSQLTable = "CREATE TABLE IF NOT EXISTS hotels " +
                    "(id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "hotel_chain TEXT, " +
                    "city TEXT, " +
                    "date TEXT, " +
                    "price INTEGER)";
            connection.createStatement().executeUpdate(createSQLTable);

            String insertSQL = "INSERT INTO hotels (hotel_chain, city, date, price) VALUES (?, ?, ?, ?)";
            PreparedStatement ps = connection.prepareStatement(insertSQL);

            LocalDate startDate = LocalDate.of(LocalDate.now().getYear(), 5, 1);
            LocalDate endDate = LocalDate.of(LocalDate.now().getYear(), 5, 4).minusDays(1);

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

            for (String chain : hotelChains) {
                for (String city : cities) {
                    for (LocalDate date = startDate; date.isBefore(endDate); date = date.plusDays(1)) {
                        String formattedMonth = String.format("%02d", date.getMonthValue());
                        String formattedDay = String.format("%02d", date.getDayOfMonth());
                        String formattedDate = date.getYear() + "-" + formattedMonth + "-" + formattedDay;

                        String url = "https://www.booking.com/searchresults.en.html" +
                                "?ss=" + chain + city +
                                "&checkin=" + formattedDate +
                                "&checkout=" + date.plusDays(3).format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) +
                                "&ltfd=6%3A28%3A5-2024%3A%3A&group_adults=2&no_rooms=1&group_children=0&order=popularity";

                        driver.get(url);
                        try {
                            WebElement closePopup = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#b2searchresultsPage > div.b9720ed41e.cdf0a9297c > div > div > div > div.dd5dccd82f > div.ffd93a9ecb.dc19f70f85.eb67815534 > div > button")));
                            closePopup.click();
                        } catch (TimeoutException e) {

                        }

                        try {
                            List<WebElement> roomPrices = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("#bodyconstraint-inner > div:nth-child(8) > div > div.a6e267116d > div > form > div > div.e22b782521.d12ff5f5bf > button")));

                            if (!roomPrices.isEmpty()) {
                                int lowestPrice = roomPrices.stream()
                                        .map(roomPrice -> {
                                            String priceText = roomPrice.getText().replaceAll("[^0-9]", "");
                                            try {
                                                return Integer.parseInt(priceText);
                                            } catch (NumberFormatException e) {
                                                return Integer.MAX_VALUE;
                                            }
                                        })
                                        .min(Integer::compareTo)
                                        .orElse(Integer.MAX_VALUE);


                                ps.setString(1, chain);
                                ps.setString(2, city);
                                ps.setString(3, formattedDate);
                                ps.setInt(4, lowestPrice);
                                ps.executeUpdate();
                            }

                        } catch (NoSuchElementException e) {
                            System.out.println("Price not found for " + chain + " in " + city + " on " + formattedDate);
                        }
                    }
                }
            }
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        driver.quit();
    }

}
