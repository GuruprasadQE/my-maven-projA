package testB;

import org.json.simple.JSONArray;
import org.openqa.selenium.*;
import testA.TestBase;
import testA.TestRunError;

import java.lang.reflect.Array;
import java.util.Arrays;

public class TestJavaScript {

    public static void main(String[] args) throws TestRunError, InterruptedException {

        TestBase.invokeApp("https://www.google.com/");
        TestBase testBase = TestBase.getInstance();
        WebDriver driver = TestBase.getDriver();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        System.out.println("================Start");
//        testBase.hardWait(3);
//        js.executeScript("alert(\"I am an alert box!\");");
//        testBase.hardWait(1);
//        driver.switchTo().alert().accept();
//        testBase.hardWait(3);
//        System.out.println("================Done1");
//        boolean isPageLoading = true;
//        int poll = 1;
//        while(isPageLoading){
//            Object object = (Object) js.executeScript("return document.readyState");
//            System.out.println(object.toString());
//            testBase.hardWait(3);
//            if(object!="undefined" && object!=null){
//                if(object.toString().equalsIgnoreCase("complete")){
//                    isPageLoading = false;
//                }
//            }
//            poll = poll + 1;
//            if(poll==10){
//                break;
//            }
//        }
//        System.out.println("================Done2");
//        testBase.hardWait(3);
//        WebElement myElement = (WebElement) js.executeScript("var elements = document.getElementsByName(\"q\");\n" +
//                "return elements[0];");
//        if(myElement!=null){
//            myElement.sendKeys("India");
//            testBase.hardWait(5);
//        }else{
//            System.out.println("Element is not loaded");
//        }

//        System.out.println("================Done3");
//        js.executeScript("document.getElementById(\"hplogo\").style.display = \"none\";");
//        testBase.hardWait(5);
//        js.executeScript("document.getElementById(\"hplogo\").style.display = \"block\";");
//        testBase.hardWait(5);
//        js.executeScript("var elements = document.getElementsByName(\"q\");\n" +
//                "\telements[0].value = \"India\";\n" +
//                "\tvar buttons = document.getElementsByName(\"btnK\");\n" +
//                "\tbuttons[0].click();");
//        testBase.hardWait(3);
//        System.out.println("================Done4");
//        driver.navigate().to("https://www.tutorialspoint.com/selenium/index.htm");
//        js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
//        testBase.hardWait(10);
//        System.out.println("================Done5");
//        driver.navigate().to("https://www.tutorialspoint.com/selenium/index.htm");
//        js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
//        testBase.hardWait(5);
//        js.executeScript("window.scrollTo(0, 0);");
//        testBase.hardWait(5);
//        WebElement element = driver.findElement(By.cssSelector(".nxt-btn"));
//        int yOffset = element.getLocation().getY();
//        js.executeScript("window.scrollTo(0, "+yOffset+");");
//        testBase.hardWait(5);
//        js.executeScript("window.scrollTo(0, "+(yOffset-200)+");");
//        testBase.hardWait(3);

        //Find javascript errors in page
        boolean errorPresentInPage = testBase.jsErrorPresentInPage(driver);
        System.out.println("JSErrorInPage: " + errorPresentInPage);
        testBase.hardWait(5);
        TestBase.closeAllBrowserInstances();
    }


}
