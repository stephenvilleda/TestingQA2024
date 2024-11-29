import org.junit.Assert;
import org.junit.Test;

public class WeatherUtilityTest {

    @Test
    public void testWeatherGivenZipCode() {
        Location beverlyHills = WeatherUtility.getLocation("90210");
        double temp = WeatherUtility.getTemperature(beverlyHills.getLat(), beverlyHills.getLng());
        System.out.println(temp);
        Assert.assertTrue(temp > 0 && temp < 100);
        // Phoenix, AZ
        Location phoenix = WeatherUtility.getLocation("85004");
        double temp2 = WeatherUtility.getTemperature(phoenix.getLat(), phoenix.getLng());
        System.out.println(temp2);
        Assert.assertTrue(temp2 > 0 && temp2 < 150);
        // Anchorage, AK
        Location anchorage = WeatherUtility.getLocation("99504");
        double temp3 = WeatherUtility.getTemperature(anchorage.getLat(), anchorage.getLng());
        System.out.println(temp3);
        Assert.assertTrue(temp3 > -50 && temp3 < 100);
    }

    @Test
    public void testGetLocation() {
        // 30044
        Location home = WeatherUtility.getLocation("30044");
        System.out.println(home.getLat());
        System.out.println(home.getLng());
    }

    @Test
    public void testTemp() {
        WeatherUtility.getTemperature("39.7456", "-97.0892");

    }
}
