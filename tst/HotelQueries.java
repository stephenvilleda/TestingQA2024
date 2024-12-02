import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.*;

public class HotelQueries {

    private static Connection connection;

    @BeforeClass
    public static void setup() throws SQLException {
        connection = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\Steph\\Desktop\\hotels.sqlite");
    }


    private int getCarTableSize() {
        int count = 0;
        try {
            String sql = "select count(*) from hotelData";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            count = rs.getInt("count(*)");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    @Test
    public void testTableSize() {
        int size = getCarTableSize();
        System.out.println(size);
        Assert.assertTrue(size >= 0);
    }

    @Test
    public void tenDatesLowestPrice() {
        String[] hotels = {"Comfort Inn", "Hampton Inn", "Holiday Inn Express", "La Quinta By Wyndham", "Residence Inn"};
        try {
            for (String hotel : hotels) {
                String sql = "  SELECT hotel, city, date, price\n" +
                                "FROM hotelData\n" +
                                "ORDER BY price ASC\n" +
                                "LIMIT 10;";
                PreparedStatement statement = connection.prepareStatement(sql);;
                ResultSet rs = statement.executeQuery();
                System.out.println("Results for " + hotel + ":");
                while (rs.next()) {
                    String city = rs.getString("city");
                    String date = rs.getString("date");
                    double price = rs.getDouble("price");

                    // Format the price to show a dollar sign and 2 decimal places
                    String formattedPrice = String.format("$%.2f", price);
                    System.out.println("City: " + city + ", Date: " + date + ", Price: " + formattedPrice);
                }
                System.out.println(); // To add a newline between hotel results
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
