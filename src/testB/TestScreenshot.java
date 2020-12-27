package testB;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import testA.TestBase;
import testA.TestRunError;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class TestScreenshot {

    public static void main(String[] args) throws TestRunError, InterruptedException, IOException {

        TestBase.invokeApp("https://www.travolook.in/");
        TestBase testBase = TestBase.getInstance();
        WebDriver driver = TestBase.getDriver();
        selectDateFromCalender(testBase, "20/02/2021");
        testBase.hardWait(5);
        testBase.takeScreenshot("C:\\Users\\SG0226069\\IdeaProjects\\SeleniumProjectA\\CodeFiles\\myFile.jpeg");
        testBase.hardWait(5);
        TestBase.closeAllBrowserInstances();
    }



    private static void selectDateFromCalender(TestBase testBase, String ddMMyyyy) throws TestRunError {
        String[] dateSplitter = ddMMyyyy.split("/");
        WebDriver driver = TestBase.getDriver();
        int requiredDate = Integer.valueOf(dateSplitter[0]).intValue();
        int requiredMonth = Integer.valueOf(dateSplitter[1]).intValue();
        int requiredYear = Integer.valueOf(dateSplitter[2]).intValue();
        System.out.println("Selecting calender for Date: " + requiredDate + "-" + requiredMonth + "-" + requiredYear);
        String requiredMonthName = testBase.getMonthName(requiredMonth);
        testBase.clickElement("#Fly_dep_datepickerid");
        testBase.clickElement(".fa-chevron-left");
        testBase.hardWait(5);
        List<WebElement> datePickers = driver.findElements(By.cssSelector(".ui-datepicker-group"));
        System.out.println("ExpectedMonthName: " + requiredMonthName);
        whileLoop:
        while (true) {
            for (WebElement datePicker : datePickers) {
                String dateTitleTxt = datePicker.getText();
                if (dateTitleTxt.contains(requiredMonthName) && dateTitleTxt.contains(String.valueOf(requiredYear))) {
                    WebElement requiredCalender = datePicker.findElement(By.cssSelector(".ui-datepicker-calendar"));
                    List<WebElement> dates = requiredCalender.findElements(By.cssSelector("td[data-handler='selectDay']"));
                    for (WebElement date : dates) {
                        if (date.getText().equals(String.valueOf(requiredDate))) {
                            date.click();
                        }
                    }
                    break whileLoop;
                }
            }
            testBase.clickElement(".fa-chevron-right");
            testBase.hardWait(1);
            datePickers = driver.findElements(By.cssSelector(".ui-datepicker-group"));
        }
    }
}
