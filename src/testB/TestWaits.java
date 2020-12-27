package testB;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import testA.TestBase;
import testA.TestRunError;

import java.util.concurrent.TimeUnit;

public class TestWaits {

    public static void main(String[] args) throws TestRunError, InterruptedException {
        TestBase.invokeApp("https://www.tutorialspoint.com/html/html_frames.htm");
        WebDriver driver = TestBase.getDriver();

        //Explicit wait
        WebDriverWait explicitWait = new WebDriverWait(driver, 75);
        explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".tp-logo")));



//        //Fluent wait
//        Wait wait = new FluentWait(driver)
//                .withTimeout(60, TimeUnit.SECONDS)
//                .pollingEvery(5, TimeUnit.SECONDS)
//                .ignoring(NoSuchElementException.class);
//        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".tp-logo")));

        driver.findElement(By.cssSelector(".tp-logo")).click();

////        explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".gsc-input")));
//
//                Wait wait = new FluentWait(driver)
//                .withTimeout(75, TimeUnit.SECONDS)
//                .pollingEvery(5, TimeUnit.SECONDS)
//                .ignoring(ElementNotInteractableException.class);
//        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".gsc-input")));


        TestBase.customPageLoadWait();

//        driver.findElement(By.cssSelector(".gsc-input")).click();
//        driver.findElement(By.cssSelector(".gsc-input")).sendKeys("Selenium");

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("var x = document.getElementById(\"gsc-i-id1\");\n" +
                "\t\tx.click();\n" +
                "\t\tx.value=\"Hellloo\";");
    }
}
