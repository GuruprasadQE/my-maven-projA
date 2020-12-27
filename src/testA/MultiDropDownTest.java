package testA;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class MultiDropDownTest {

    public static void main(String[] args) throws InterruptedException, TestRunError {
        TestBase testBase = new TestBase();
        testBase.invokeApp("https://www.plus2net.com/html_tutorial/html_frmddl.php");
        WebDriver driver = testBase.getDriver();
        //select multiple items
        String[] itemTexts = {"Green","Red"};
        testBase.selectMultipleItemsByVisibleText( "span[itemprop='articleBody']>select[name='ddl']", itemTexts);
        testBase.hardWait(3);
        testBase.closeAllBrowserInstances();
    }


}
