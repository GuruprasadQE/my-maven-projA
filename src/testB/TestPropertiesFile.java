package testB;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import testA.TestBase;
import testA.TestRunError;

import java.io.*;
import java.util.List;
import java.util.Properties;

public class TestPropertiesFile {

    public static void main(String[] args) throws TestRunError, InterruptedException, IOException {


        String projPath = System.getProperty("user.dir");
        System.out.println("ProjectPath: "+ projPath);
        InputStream file = new FileInputStream(projPath+"\\src\\testB\\appconfig.properties");
        Properties properties = new Properties();
        properties.load(file);
        String appURL = properties.getProperty("appURL");
        System.out.println("Application URL: "+ appURL);
        TestBase.invokeApp(appURL);
        TestBase testBase = TestBase.getInstance();
        WebDriver driver = TestBase.getDriver();
//
        testBase.clickElement("#createacc");
        testBase.setValue(".ureg-fname","Guru");
        testBase.setValue(".ureg-lname","Prasad");
        testBase.hardWait(4);
        testBase.clickElement("#reg-submit-button");
        testBase.hardWait(5);
       //============== Using below code to set values in to propfile

        List<WebElement> errors = driver.findElements(By.cssSelector(".oneid-error-message"));
        if(errors.size()==4){
            System.out.println("TCPass");
        }else{
            System.out.println("TCFail");
        }
        String errorMessage= null;
        for(WebElement errorElement: errors){
            //suppose if if it is the values present in textbox we need to get it using driver.finedElement(By.css("")).getAttribute("value");
            // if the value present as text in page
            errorMessage = errorElement.getText();
            System.out.println("ErrorMessage: "+ errorMessage);
            if(errorMessage.equals("This is required.")){
                System.out.println("TCErrorMessagePass");
            }else{
                System.out.println("TCErrorMessageFail");
            }
        }
        Properties properties2 = new Properties();
        properties2.setProperty("ErrorMessage",errorMessage);
        properties2.store(new FileWriter(projPath+"\\src\\testB\\testOutputProp.properties"),"FileSaved");
    }
}
