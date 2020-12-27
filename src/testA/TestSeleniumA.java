package testA;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;


public class TestSeleniumA {

    public static void main(String[] args) throws InterruptedException, TestRunError {
        WebDriver driver = invokeApp("https://truweight.in/consultation/1.html");

        //Interacting with elements
        isElementDisplayed(driver,"img.logo");

        //enterValue in text feild
        setValue(driver,"#name","Guru");
        setValue(driver,"#age","37");
        setValue(driver,"#weight","80");

        //Selecting value fromdropdown
//        selectDropdownItemByIndex(driver,".form-control.height",100);
//        selectDropdownItemByValue(driver,".form-control.height","4ft.2in");
        selectDropdownItemByVisibleText(driver,".form-control.height","6ft.2in");
//        selectDropdownByRandom(driver,".form-control.height");


        hardWait(5);

        //Close all the instances of browser
        quitBrowsers(driver);


    }

    private static int getRandomNumber(int min, int max) {
        int random_int = (int) (Math.random() * (max - min + 1) + min);
        return random_int;
    }

    private static void selectDropdownItemByIndex(WebDriver driver, String css, int itemIndex) throws TestRunError {
        Select select = new Select(driver.findElement(By.cssSelector(css)));
        int totalItemSize = select.getOptions().size();
        if(itemIndex==0 || itemIndex > totalItemSize-1){
            throw new TestRunError("Select dropdpwn - provided with invalid index: "+ itemIndex);
        }else{
            select.selectByIndex(itemIndex);
        }

    }
    private static void selectDropdownByRandom(WebDriver driver, String css) {
        Select select = new Select(driver.findElement(By.cssSelector(css)));
        List<WebElement> options = select.getOptions();
        int totalItems = options.size();
        select.selectByIndex(getRandomNumber(1,totalItems-1));
    }

    private static void selectDropdownItemByValue(WebDriver driver, String css, String value) throws TestRunError {
        Select select = new Select(driver.findElement(By.cssSelector(css)));
        List<WebElement> options = select.getOptions();
        boolean  itemValueFound = false;
        for(WebElement option: options){
            if(value.equals(option.getAttribute("value"))){
                itemValueFound = true;
                break;
            }
        }
        if(itemValueFound){
            select.selectByValue(value);
        }else{
            throw new TestRunError("Select dropdown - is provided with invalid value: "+value);
        }
    }

    private static void selectDropdownItemByVisibleText(WebDriver driver, String css, String visibleText) throws TestRunError {
        WebElement element =  driver.findElement(By.cssSelector(css));
        if(driver.findElement(By.cssSelector(css)).isDisplayed()){
            Select select = new Select(element);
            List<WebElement> options = select.getOptions();
            boolean  itemValueFound = false;
            for(WebElement option: options){
                if(visibleText.equals(option.getText())){
                    itemValueFound = true;
                    break;
                }
            }
            if(itemValueFound){
                select.selectByValue(visibleText);
            }else{
                throw new TestRunError("Select dropdown - is provided with invalid visibleText: "+visibleText);
            }
        }else{
            throw new TestRunError("Select dropdown - is not displayed "+css);
        }
    }

    private static void isElementDisplayed(WebDriver driver, String cssLocator) throws TestRunError {
        if (driver.findElement(By.cssSelector(cssLocator)).isDisplayed()) {
            System.out.println("Element is displayed - " + cssLocator);
        } else {
            throw new TestRunError("Element is not displayed - " + cssLocator);
        }
    }

    private static void setValue(WebDriver driver, String cssLocator, String input) throws TestRunError {
        if (driver.findElement(By.cssSelector(cssLocator)).isDisplayed()) {
            System.out.println("Element is displayed - " + cssLocator);
            driver.findElement(By.cssSelector(cssLocator)).sendKeys(input);
        } else {
            throw new TestRunError("Element is not displayed - " + cssLocator);
        }
    }

    private static void hardWait(int sec) throws TestRunError {
        try {
            Thread.sleep(sec * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void manageMethods(WebDriver driver) throws InterruptedException {
        //manage
        driver.manage().window().fullscreen();
        System.out.println("XPosition: " + driver.manage().window().getPosition().getX());
        System.out.println("YPosition: " + driver.manage().window().getPosition().getY());
        System.out.println("Height: " + driver.manage().window().getSize().getHeight());
        System.out.println("width: " + driver.manage().window().getSize().getWidth());
        Thread.sleep(2000);
        driver.manage().window().setSize(new Dimension(200, 200));
        Thread.sleep(10000);
    }

    private static void navigationTests(WebDriver driver) throws InterruptedException {
        //TestNavigateMethods
        driver.navigate().to("https://www.icicibank.com/calculator/personal-loan-emi-calculator.page");
        Thread.sleep(2000);
        driver.navigate().back();
        System.out.println("Page URL after browser back: " + driver.getCurrentUrl());
        Thread.sleep(2000);
        driver.navigate().forward();
        System.out.println("Page URL after forward: " + driver.getCurrentUrl());
        Thread.sleep(2000);
        driver.navigate().refresh();
        System.out.println("Page URL after refreshed: " + driver.getCurrentUrl());
    }

    private static void quitBrowsers(WebDriver driver) {
        driver.quit();
    }

    private static void testA(WebDriver driver) throws TestRunError {
        //Getting Unique window id
//        String windowId = driver.getWindowHandle();
//        System.out.println("Parent Window id-before click:  " + windowId);

        //Click on a link
        elementClick(driver, "a[title='Report a Problem']");

        //Get window ids of all the opened browsers
//        switchToWindowWithTitle(driver,"Feedback");
        switchToWindowWithURLParam(driver, "feedback");

        System.out.println("The CurrentPage URL: " + driver.getCurrentUrl());

        //check expected string present in html source page
        checkPageSourceWithText(driver, "files.naukri.com");
    }

    private static WebDriver invokeApp(String url) throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:\\G-Learn\\Selenium\\env\\chromedriver.exe");
        //launch browser
        WebDriver driver = new ChromeDriver();
        //maximize the browser
        driver.manage().window().maximize();
        //delete all cookies
        driver.manage().deleteAllCookies();
        //Launch URL
        driver.get(url);
        //Wait for 25sec till the page load
        driver.manage().timeouts().pageLoadTimeout(25, TimeUnit.SECONDS);
        //Implicit wait for any element to load
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        return driver;
    }

    private static void checkPageSourceWithText(WebDriver driver, String expectedPageSourceValue) throws TestRunError {
        String pageSource = driver.getPageSource();
        if (pageSource.contains(expectedPageSourceValue)) {
            System.out.println("Expected value: " + expectedPageSourceValue + " is found in page source");
        } else {
            throw new TestRunError("Expected value: " + expectedPageSourceValue + " is not found in page source");
        }
    }

    private static void switchToWindowWithTitle(WebDriver driver, String expectedWindowTitle) throws TestRunError {
        // it return all the window ids
        Set<String> windowIds = driver.getWindowHandles();
        // returning Iterator to go-through each window id
        Iterator<String> itr = windowIds.iterator();
        // assigning a variable with false and update true only when required window title is found
        boolean isWindowFound = false;
        // iterating each window
        while (itr.hasNext()) {
            //getting window id
            String windowId = itr.next();
            System.out.println("WindowId: " + windowId);
            //switching to the returned window id
            driver.switchTo().window(windowId);
            //getting window id title
            String windowTitle = driver.getTitle();
            //comparing the window id is matching with expectedWindowTitle
            if (windowTitle.contains(expectedWindowTitle)) {
                System.out.println("Currently Switched to Window with title: " + expectedWindowTitle);
                // updating true only when required window title is matched
                isWindowFound = true;
                //exit from the loop since we found the expected window based on title key
                break;
            }
        }
        //checking the variable boolean status if the window does not exist we throw an exception
        if (isWindowFound) {
            System.out.println("WebDriver switched to window which has title: " + expectedWindowTitle);
        } else {
            throw new TestRunError("WebDriver has not found the window with title: " + expectedWindowTitle);
        }
    }

    private static void switchToWindowWithURLParam(WebDriver driver, String urlParam) throws TestRunError {
        // it return all the window ids
        Set<String> windowIds = driver.getWindowHandles();
        // returning Iterator to go-through each window id
        Iterator<String> itr = windowIds.iterator();
        // assigning a variable with false and update true only when required window title is found
        boolean isWindowFound = false;
        // iterating each window
        while (itr.hasNext()) {
            //getting window id
            String windowId = itr.next();
            System.out.println("WindowId: " + windowId);
            //switching to the returned window id
            driver.switchTo().window(windowId);
            //getting window id title
            String windowTitle = driver.getCurrentUrl();
            //comparing the window id is matching with expectedWindowTitle
            if (windowTitle.contains(urlParam)) {
                System.out.println("Currently Switched to Window with URLParam: " + urlParam);
                // updating true only when required window title is matched
                isWindowFound = true;
                //exit from the loop since we found the expected window based on title key
                break;
            }
        }
        //checking the variable boolean status if the window does not exist we throw an exception
        if (isWindowFound) {
            System.out.println("WebDriver switched to window which has urlParam: " + urlParam);
        } else {
            throw new TestRunError("WebDriver has not found the window with urlParam: " + urlParam);
        }
    }

    private static void elementClick(WebDriver driver, String cssLocator) throws TestRunError {
        //1st find the element presence
        if (driver.findElement(By.cssSelector(cssLocator)).isEnabled()) {
            //Click the element
            driver.findElement(By.cssSelector(cssLocator)).click();
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            //If the selector is not found then throwing the custom exception
            throw new TestRunError("Element is not found with selector: " + cssLocator);
        }
    }
}
