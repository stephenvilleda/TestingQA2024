import org.apache.commons.io.IOUtils;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.time.Duration;

public class  TuitionCalculatorTest {

    private static WebDriver driver;
    private final static String USNEWS_URL = "https://www.usnews.com/best-colleges/georgia-gwinnett-college-41429";
    private final static String CALC_URL = "http://www.ggc.edu/admissions/tuition-and-financial-aid-calculators/index.html#";
    private String BANNER = "https://ggc.gabest.usg.edu/StudentSelfService/";

//    private void getInStateTuition() throws Exception {
//        URL url = new URL(USNEWS_URL);
//        String str = IOUtils.toString(url.openStream(), StandardCharsets.UTF_8);
//        //In-state tuition and fees
//        int index = str.indexOf("In-state tuition and fees");
//        int start = index+"In-state tuition and fees".length()+6;
//        String priceText = str.substring(start, start+6);
//        System.out.println(priceText);
//        //String formatted = priceText.replace("$","").replace(",","");
//        //return Integer.parseInt(formatted);
//        //Fall 2024
//        //return 5354
//    }

    @Ignore
    @Test
    public void testInstateUSNews() throws Exception {
        //getInStateTuition();
        //Assert.assertEquals(5354, instate);
    }

    @BeforeClass
    public static void setUp() {
        //System.setProperty();
        //ChromeOptions options = new ChromeOptions();
        //options.addArguments("user-data-dir=/Users/tim/Library/Application Support/Google/Chrome/Default");
        driver = new ChromeDriver();

    }

    @Test
    public void testInstateTuition() {
        // place implicit wait just in case
        int expected = 5354;  //yearly tuition
        driver.get(CALC_URL);
        WebElement inorout = driver.findElement(By.id("inorout1"));
        inorout.click();
        WebElement select = driver.findElement(By.id("creditHOURS"));
        Select credits = new Select(select);
        credits.selectByVisibleText("15 hours");
        WebElement cost = driver.findElement(By.id("totalcost"));
        String tuition = cost.getAttribute("value");
        String formatted = tuition.replace("$","");
        int end = formatted.indexOf(".");
        formatted = formatted.substring(0, end);
        int semester = Integer.parseInt(formatted);
        int actual = semester * 2;
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testOutOfState() {
        int expected = 16994;
        // place implicit wait just in case
        driver.get(CALC_URL);
        WebElement inorout = driver.findElement(By.id("inorout0"));
        inorout.click();
        WebElement select = driver.findElement(By.id("creditHOURS"));
        Select credits = new Select(select);
        credits.selectByVisibleText("15 hours");
        WebElement cost = driver.findElement(By.id("totalcost"));
        String tuition = cost.getAttribute("value");
        String formatted = tuition.replace("$","");
        int end = formatted.indexOf(".");
        formatted = formatted.substring(0, end);
        int semester = Integer.parseInt(formatted);
        int actual = semester * 2;
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testPersonalTuition() throws Exception {
        //TODO
        driver.get(BANNER);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        WebElement email = driver.findElement(By.id("i0116"));
        email.sendKeys("");
        WebElement nextButton = driver.findElement(By.id("idSIButton9"));
        nextButton.click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        WebElement password = driver.findElement(By.id("i0118"));
        password.sendKeys("");
        try {
            WebElement signin = driver.findElement(By.id("idSIButton9"));
            signin.click();
        } catch (Exception e) {
            WebElement signin = driver.findElement(By.id("idSIButton9"));
            signin.click();
        }
    }
    }
