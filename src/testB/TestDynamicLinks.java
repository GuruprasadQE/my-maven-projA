package testB;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import testA.TestBase;
import testA.TestRunError;

public class TestDynamicLinks {

    public static void main(String[] args) throws TestRunError, InterruptedException {

//        TestBase.invokeApp("https://www.google.com/");
//        TestBase testBase = TestBase.getInstance();
//        testBase.setValue("input[name='q']","India");
//        testBase.setValue("input[name='q']", "PressEnterKey");
//        testBase.clickOnDynamicLink("a","https://www.rbi.org.in/");

        TestBase.invokeApp("https://www.w3schools.com/html/html_tables.asp");
        TestBase testBase = TestBase.getInstance();
        testBase.checkValuePresenceInTable("table#customers","Ernst Handel");


    }
}
