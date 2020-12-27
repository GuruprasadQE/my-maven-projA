package testB;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import testA.TestBase;
import testA.TestRunError;

public class TestTextbox {

    public static void main(String[] args) throws TestRunError, InterruptedException {

        TestBase.invokeApp("https://www.tutorialspoint.com/html/html_frames.htm");
        TestBase testBase = TestBase.getInstance();
        WebDriver driver = TestBase.getDriver();
        testBase.hardWait(3);
       driver.findElement(By.cssSelector("#gsc-i-id1")).click();
        testBase.hardWait(3);
        driver.findElement(By.cssSelector("#gsc-i-id1")).sendKeys("Hello");
        for(int i=0;i<5;i++){
            driver.findElement(By.cssSelector("#gsc-i-id1")).sendKeys(Keys.ARROW_DOWN);
            testBase.hardWait(1);
        }
        driver.findElement(By.cssSelector("#gsc-i-id1")).sendKeys(Keys.ENTER);
        testBase.hardWait(3);

    }
}
