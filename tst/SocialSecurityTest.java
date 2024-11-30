import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

@RunWith(JUnitParamsRunner.class)
public class SocialSecurityTest {

    private static WebDriver driver;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        driver = new ChromeDriver();
    }

    @Test
    public void testSocialSecurity() {
        driver.get("https://www.ssa.gov/OACT/quickcalc/");
        WebElement month = driver.findElement(By.id("month"));
        WebElement day = driver.findElement(By.id("day"));
        WebElement year = driver.findElement(By.id("year"));
        WebElement earnings = driver.findElement(By.id("earnings"));
        month.clear();
        day.clear();
        year.clear();
        earnings.clear();
        month.sendKeys("1");
        day.sendKeys("1");
        year.sendKeys("1990");
        earnings.sendKeys("55000");
        WebElement submit = driver.findElement(By.cssSelector("body > table:nth-child(6) > tbody > tr:nth-child(2) > td:nth-child(2) > form > table > tbody > tr:nth-child(5) > td > input[type=submit]"));
        submit.click();

        WebElement earningsSubmit = driver.findElement(By.cssSelector("body > table:nth-child(3) > tbody > tr:nth-child(3) > td > form > input[type=submit]:nth-child(10)"));
        earningsSubmit.click();
        int birthyear = 1990;
        int startYear = birthyear + 17;
        int endYear = 2024;
        for (int i = startYear; i <= endYear ; i++) {
            WebElement field = driver.findElement(By.name(i+""));
            field.clear();
            field.sendKeys("55000");
        }
        WebElement evaluate = driver.findElement(By.cssSelector("body > table:nth-child(3) > tbody > tr:nth-child(1) > td:nth-child(1) > form > input[type=submit]:nth-child(7)"));
        evaluate.click();
        WebElement fra = driver.findElement(By.id("est_fra"));
        String estimate = fra.getText().replace("$","").replace(",","");
        double a = Double.parseDouble(estimate);
        Assert.assertEquals(2445,a, 0);
    }


}
