import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.time.Duration;

public class HelloSelenium {

    private static WebDriver driver;

    @BeforeClass
    public static void setUp() {
        // if you get the following msg:
        // org.openqa.selenium.SessionNotCreatedException: Could not start a new session. Possible causes are invalid address of the remote server or browser start-up failure.
        // do NOT set the property
        // System.setProperty("webdriver.chrome.driver","chromedriver");
        driver = new ChromeDriver();
        //System.setProperty("webdriver.edge.driver","msedgedriver");
        //driver = new EdgeDriver();
        //System.setProperty("webdriver.gecko.driver","geckodriver");
        //driver = new FirefoxDriver();
    }

    @Test
    public void testGoogle() {
        driver.get("https://www.google.com");
        WebElement searchBar = driver.findElement(By.name("q"));
        searchBar.sendKeys("GGC");
        searchBar.submit();

        boolean exists = driver.getTitle().contains("GGC");
        Assert.assertTrue(exists);
    }

    @Test
    public void testIHGSite() {
        driver.get("https://ihgrewardsdineandearn.rewardsnetwork.com/join");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        WebElement firstName = driver.findElement(By.id("firstName"));
        firstName.sendKeys("John");
        WebElement lastName = driver.findElement(By.id("lastName"));
        lastName.sendKeys("Smith");

        WebElement zipCode = driver.findElement(By.id("zipCode"));
        zipCode.sendKeys("30043");
        WebElement partnerCode = driver.findElement(By.id("partnerProgramNumber"));
        partnerCode.sendKeys("12345");
        WebElement email = driver.findElement(By.id("email"));
        email.sendKeys("john.smith@ggc.edu");
        WebElement phone = driver.findElement(By.id("phoneNumber"));
        phone.sendKeys("55555555");
        WebElement password = driver.findElement(By.id("password"));
        password.sendKeys("password");
        WebElement submitButton = driver.findElement(By.tagName("button"));
        submitButton.click();
        // zipCode
        // partnerProgramNumber
        // email
        // phoneNumber
        // password

    }
}
