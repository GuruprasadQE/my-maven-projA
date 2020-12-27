package testB;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import testA.TestBase;
import testA.TestRunError;

public class TestDragAndDrop {

    public static void main(String[] args) throws TestRunError, InterruptedException {

        TestBase.invokeApp("http://beej.us/blog/data/drag-n-drop/");
        TestBase testBase = TestBase.getInstance();
        WebDriver driver = TestBase.getDriver();
                testBase.hardWait(30);
        WebElement gt0 = driver.findElement(By.cssSelector("#goat0"));
        WebElement drp0 = driver.findElement(By.cssSelector("#dropzone0"));

        testBase.dragAndDropElement(driver,gt0,drp0);
        testBase.hardWait(2);
        testBase.closePopup();

        testBase.hardWait(10);
        TestBase.closeAllBrowserInstances();
    }
}
