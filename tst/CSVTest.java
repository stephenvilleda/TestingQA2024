import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class CSVTest {

        @Test
        public void testDownloadCSV() throws IOException {
            URL url = new URL("https://gist.githubusercontent.com/tacksoo/349702bd06852814fba06a4df48e32d8/raw/5fb59f716e9069ac186b1994376c85823a65e335/myinventory.csv");
            String str = IOUtils.toString(url.openStream());
            System.out.println(str);
        }

        @Test
        public void testGetLatLng() throws Exception {
            // always need a parser -> records
            CSVParser parser = CSVParser.parse(new File("uszips.csv"),
                    StandardCharsets.UTF_8, CSVFormat.DEFAULT.builder().setHeader().build());
            for (CSVRecord record : parser) {
                if (record.get("zip").equals("30043")) {
                    System.out.println(record.get("lat"));
                    System.out.println(record.get("lng"));
                }
            }
            parser.close();
        }

    @Test
    public void testGunViolenceCSV() throws IOException {
        // file the contents of a file
        String str = FileUtils.readFileToString(new File("gunviolence.csv"), "UTF-8");
        // create the parser
        CSVParser parser = CSVParser.parse(str, CSVFormat.DEFAULT.builder().setHeader().build());
        // go through the parser
        int count = 0;
        for (CSVRecord record : parser) {
            String city = record.get("City Or County");
            String address = record.get("Address");
            int killed = Integer.parseInt(record.get("Victims Killed"));
            int injured = Integer.parseInt(record.get("Victims Injured"));
            if (record.get("State").equals("Georgia")) {
                count++;
                System.out.println(city + " " + address + " " + killed + " " + injured);
            }
        }
        System.out.println(count);
    }
}
