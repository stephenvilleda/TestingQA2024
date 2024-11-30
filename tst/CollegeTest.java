import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

@RunWith(JUnitParamsRunner.class)
public class CollegeTest {

    private static WebDriver driver;

    @BeforeClass
    public static void setUp() {
        driver = new ChromeDriver();
    }

    @Parameters(value = {"Georgia Gwinnett College,Tuition","Kennesaw State University,Tuition","Georgia State University,Tuition"})
    @Test
    public void testColleges(String name, String keyword) {
        driver.get("https://www.google.com");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        WebElement q = driver.findElement(By.name("q"));
        q.sendKeys(name);
        q.submit();
        WebElement price = driver.findElement(By.className("hAvHce"));
        System.out.println(name);
        System.out.println(price.getText());
    }

}
