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

    /*
    CLASS CODE
     */


//    @Test
//    public void testSocialSecurity() {
//        driver.get("https://www.ssa.gov/OACT/quickcalc/");
//        WebElement month = driver.findElement(By.id("month"));
//        WebElement day = driver.findElement(By.id("day"));
//        WebElement year = driver.findElement(By.id("year"));
//        WebElement earnings = driver.findElement(By.id("earnings"));
//        month.clear();
//        day.clear();
//        year.clear();
//        earnings.clear();
//        month.sendKeys("1");
//        day.sendKeys("1");
//        year.sendKeys("1990");
//        earnings.sendKeys("55000");
//        WebElement submit = driver.findElement(By.cssSelector("body > table:nth-child(6) > tbody > tr:nth-child(2) > td:nth-child(2) > form > table > tbody > tr:nth-child(5) > td > input[type=submit]"));
//        submit.click();
//
//        WebElement earningsSubmit = driver.findElement(By.cssSelector("body > table:nth-child(3) > tbody > tr:nth-child(3) > td > form > input[type=submit]:nth-child(10)"));
//        earningsSubmit.click();
//        int birthyear = 1990;
//        int startYear = birthyear + 17;
//        int endYear = 2024;
//        for (int i = startYear; i <= endYear ; i++) {
//            WebElement field = driver.findElement(By.name(i+""));
//            field.clear();
//            field.sendKeys("55000");
//        }
//        WebElement evaluate = driver.findElement(By.cssSelector("body > table:nth-child(3) > tbody > tr:nth-child(1) > td:nth-child(1) > form > input[type=submit]:nth-child(7)"));
//        evaluate.click();
//        WebElement fra = driver.findElement(By.id("est_fra"));
//        String estimate = fra.getText().replace("$","").replace(",","");
//        double a = Double.parseDouble(estimate);
//        Assert.assertEquals(2445,a, 0);
//    }



    /*
    QUESTION 1 : the first three values in Parameters

    QUESTION 2 : the fourth value in the Parameters. (2024 - 35 = 1989 - 21 = 1968)
    Quoted from https://www.bankrate.com/retirement/maximum-social-security-benefit/
    To do so, Social Security takes your 35 highest-earning years after age 21
    to figure your average indexed monthly earnings.
    You’ll get credit only for earnings up to the Social Security wage base,
    which is the maximum amount of income on which Social Security assesses taxes.
    For 2025, the Social Security wage base is $176,100, an increase from $168,600 in 2024.
     */

    @Parameters({"1, 1, 1990, 55000, 2445", "1, 1, 1980, 65000, 3169", "1, 1, 1970, 75000, 3648", "1, 1, 1968, 168600, 4049"})
    @Test
    public void testSocialSecurityWithParameters(String m, String d, String y, String e, String efra) {
        driver.get("https://www.ssa.gov/OACT/quickcalc/");
        WebElement month = driver.findElement(By.id("month"));
        WebElement day = driver.findElement(By.id("day"));
        WebElement year = driver.findElement(By.id("year"));
        WebElement earnings = driver.findElement(By.id("earnings"));
        month.clear();
        day.clear();
        year.clear();
        earnings.clear();
        month.sendKeys(m);
        day.sendKeys(d);
        year.sendKeys(y);
        earnings.sendKeys(e);
        WebElement submit = driver.findElement(By.cssSelector("body > table:nth-child(6) > tbody > tr:nth-child(2) > td:nth-child(2) > form > table > tbody > tr:nth-child(5) > td > input[type=submit]"));
        submit.click();

        WebElement earningsSubmit = driver.findElement(By.cssSelector("body > table:nth-child(3) > tbody > tr:nth-child(3) > td > form > input[type=submit]:nth-child(10)"));
        earningsSubmit.click();
        int birthyear = Integer.parseInt(y);
        int startYear = birthyear + 17;
        int endYear = 2024;
        for (int i = startYear; i <= endYear; i++) {
            WebElement field = driver.findElement(By.name(i + ""));
            field.clear();
            field.sendKeys(e);
        }
        WebElement evaluate = driver.findElement(By.cssSelector("body > table:nth-child(3) > tbody > tr:nth-child(1) > td:nth-child(1) > form > input[type=submit]:nth-child(7)"));
        evaluate.click();
        WebElement fra = driver.findElement(By.id("est_fra"));
        String estimate = fra.getText().replace("$", "").replace(",", "");
        double a = Double.parseDouble(estimate);
        Assert.assertEquals(Double.parseDouble(efra), a, 0);
    }


    /*
    QUESTION 3
    average salary is 52264
     */

    @Parameters({"1, 1, 1990, 17, 52264, 0", "1, 1, 1990, 27, 52264, 1"})
    @Test
    public void test10yearDifference(String m, String d, String y, String workstart, String earn, String ten) {
        driver.get("https://www.ssa.gov/OACT/quickcalc/");
        WebElement month = driver.findElement(By.id("month"));
        WebElement day = driver.findElement(By.id("day"));
        WebElement year = driver.findElement(By.id("year"));
        WebElement earnings = driver.findElement(By.id("earnings"));
        month.clear();
        day.clear();
        year.clear();
        earnings.clear();
        month.sendKeys(m);
        day.sendKeys(d);
        year.sendKeys(y);
        earnings.sendKeys(earn);
        WebElement submit = driver.findElement(By.cssSelector("body > table:nth-child(6) > tbody > tr:nth-child(2) > td:nth-child(2) > form > table > tbody > tr:nth-child(5) > td > input[type=submit]"));
        submit.click();

        WebElement earningsSubmit = driver.findElement(By.cssSelector("body > table:nth-child(3) > tbody > tr:nth-child(3) > td > form > input[type=submit]:nth-child(10)"));
        earningsSubmit.click();
        int birthyear = Integer.parseInt(y);
        int startYear = birthyear + Integer.parseInt(workstart);
        int endYear = 2024;
        if(startYear == 2007) {
            for (int i = startYear; i <= endYear; i++) {
                WebElement field = driver.findElement(By.name(i + ""));
                field.clear();
                field.sendKeys(earn);
            }
        } else {
            for (int i = (startYear-10); i <= startYear; i++) {
                WebElement field = driver.findElement(By.name(i + ""));
                field.clear();
                field.sendKeys("0");
            }
            for (int i = startYear; i <= endYear; i++) {
                WebElement field = driver.findElement(By.name(i + ""));
                field.clear();
                field.sendKeys(earn);
            }
        }
        WebElement evaluate = driver.findElement(By.cssSelector("body > table:nth-child(3) > tbody > tr:nth-child(1) > td:nth-child(1) > form > input[type=submit]:nth-child(7)"));
        evaluate.click();
        WebElement fra = driver.findElement(By.id("est_fra"));
        String estimate = fra.getText().replace("$", "").replace(",", "");
        double a = Double.parseDouble(estimate);
        if(ten.equals("0")) {
            System.out.printf("Working early and retiring makes $%.2f dollars a month\n", a);
        }
        else {
            System.out.printf("Working 10 years later and retiring makes $%.2f dollars a month\n", a);
            System.out.print("Not much of a significant difference");
        }
    }

}
