package testA;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class TestFrame {

    public static void main(String[] args) throws TestRunError, InterruptedException {

        TestBase testBase = new TestBase();
        testBase.invokeApp("https://www.tutorialspoint.com/html/html_frames.htm");
        WebDriver driver = testBase.getDriver();
//        driver.findElement(By.cssSelector("#w3loginbtn")).click();
//        testBase.hardWait(3);
//        driver.findElement(By.cssSelector(".w3-button.w3-xlarge.w3-right")).click();
        testBase.hardWait(3);
        System.out.println("main");
        driver.switchTo().frame(0);
        System.out.println("WindowPosition-height:"+ driver.manage().window().getSize().getHeight());
        System.out.println("WindowPosition-width:"+ driver.manage().window().getSize().getWidth());
        JavascriptExecutor jse = (JavascriptExecutor)driver;
        jse.executeAsyncScript("window.close()");
        testBase.hardWait(4);
//        driver.switchTo().frame("main");
//        driver.findElement(By.cssSelector("input[name='search']")).click();
//        driver.findElement(By.cssSelector("input[name='search']")).sendKeys("TestFrame");
//        testBase.hardWait(4);
//        driver.quit();
    }
}
