package testB;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.Keyboard;
import testA.TestBase;
import testA.TestRunError;

public class TestFrames {

    public static void main(String[] args) throws TestRunError, InterruptedException {

        TestBase.invokeApp("https://www.tutorialspoint.com/html/html_frames.htm");
        TestBase testBase = TestBase.getInstance();
        WebDriver driver = TestBase.getDriver();
        testBase.hardWait(5);
        driver.switchTo().frame(0).switchTo().frame("main");
        testBase.hardWait(3);
        Actions action = new Actions(driver);
       for(int i=1; i<=3;i++){
           action.sendKeys(Keys.PAGE_DOWN).build().perform();
           testBase.hardWait(3);
           System.out.println("Page down - done");
       }
        testBase.hardWait(3);
        driver.findElement(By.cssSelector(".gsc-input")).click();
        testBase.hardWait(1);
        driver.findElement(By.cssSelector(".gsc-input")).sendKeys();
        testBase.hardWait(15);
    }
}
