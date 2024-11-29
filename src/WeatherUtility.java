import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class WeatherUtility {

    private WeatherUtility() {}

    public static Location getLocation(String zipcode) {
        Location location = null;
        try {
            CSVParser parser = CSVParser.parse(new File("uszips.csv"),
                    StandardCharsets.UTF_8, CSVFormat.DEFAULT.builder().setHeader().build());
            for (CSVRecord record : parser) {
                if (record.get("zip").equals(zipcode)) {
                    location = new Location(record.get("lat"),
                            record.get("lng"), zipcode);
                }
            }
            parser.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return location;
    }

    public static double getTemperature(String lat, String lng) {
        // https://api.weather.gov/points/{latitude},{longitude}
        String url = "https://api.weather.gov/points/" + lat + "," + lng;
        ObjectMapper objectMapper = new ObjectMapper();
        double temp = Double.MAX_VALUE;
        try {
            JsonNode root = objectMapper.readTree(new URL(url));
            String office = root.get("properties").get("gridId").asText();
            String gridX = root.get("properties").get("gridX").asText();
            String gridY = root.get("properties").get("gridY").asText();
            String forcastURL = "https://api.weather.gov/gridpoints/" + office + "/" + gridX + "," + gridY + "/forecast";
            JsonNode forecastRoot = objectMapper.readTree(new URL(forcastURL));
            temp = forecastRoot.get("properties").get("periods").get(0).get("temperature").asDouble();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return temp;
    }
}