package testA;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class TestBase {
    private static WebDriver driver;
    private static TestBase testBase;

    public static WebDriver getDriver() {
        if (driver == null) {
            System.setProperty("webdriver.chrome.driver", "C:\\G-Learn\\Selenium\\env\\chromedriver.exe");
            driver = new ChromeDriver();
        }
        return driver;
    }

    public static WebDriver getDriver(String browserType) {
        if (driver == null) {
            System.out.println("====================================");
            System.out.println("driver is NULL for: " + browserType);
            System.out.println("====================================");
            if (browserType.equalsIgnoreCase("Firefox")) {
                System.setProperty("webdriver.gecko.driver", "C:\\Users\\SG0226069\\IdeaProjects\\SeleniumProjectA\\CodeFiles\\env\\geckodriver.exe");
                driver = new FirefoxDriver();
            }
            if (browserType.equalsIgnoreCase("Chrome")) {
                System.setProperty("webdriver.chrome.driver", "C:\\G-Learn\\Selenium\\env\\chromedriver.exe");
                driver = new ChromeDriver();
            }
        }
        return driver;
    }

    public static WebDriver getDriver(DesiredCapabilities capabilities) {
        if (driver == null) {
            System.setProperty("webdriver.chrome.driver", "C:\\G-Learn\\Selenium\\env\\chromedriver.exe");
            driver = new ChromeDriver(capabilities);
        }
        return driver;
    }

    public static TestBase getInstance() {
        if (testBase == null) {
            testBase = new TestBase();
        }
        return testBase;
    }

    public void selectMultiDropdown(String css, int... itemIndexes) {
        Select select = new Select(driver.findElement(By.cssSelector(css)));
        for (int index : itemIndexes) {
            select.selectByIndex(index);
        }
    }

    public static void invokeApp(String browserType, String url) {
        driver = getDriver(browserType);
        //maximize the browser
        driver.manage().window().maximize();
        //delete all cookies
//        driver.manage().deleteAllCookies();
        //Launch URL
        driver.get(url);
        //Wait for 25sec till the page load
        driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
//        customPageLoadWait();
        //Implicit wait for any element to load
        driver.manage().timeouts().implicitlyWait(45, TimeUnit.SECONDS);
    }

    public static void invokeApp(String url) throws InterruptedException, TestRunError {
        driver = getDriver();
        //maximize the browser
        driver.manage().window().maximize();
        //delete all cookies
//        driver.manage().deleteAllCookies();
        //Launch URL
        driver.get(url);
        //Wait for 25sec till the page load
        driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);

//        customPageLoadWait();
        //Implicit wait for any element to load
        driver.manage().timeouts().implicitlyWait(45, TimeUnit.SECONDS);
    }

    public static void customPageLoadWait() throws TestRunError {
        WebDriver driver = TestBase.getDriver();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        boolean isPageLoading = true;
        int poll = 1;
        while (isPageLoading) {
            Object object = (Object) js.executeScript("return document.readyState");
            wait(3);
            if (object != "undefined" && object != null) {
                if (object.toString().equalsIgnoreCase("complete")) {
                    isPageLoading = false;
                }
            }
            poll = poll + 1;
            if (poll == 10) {
                break;
            }
        }
    }


    public void hardWait(int sec) throws TestRunError {
        try {
            Thread.sleep(sec * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void wait(int sec) throws TestRunError {
        try {
            Thread.sleep(sec * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void closeAllBrowserInstances() throws TestRunError {
        driver.quit();
        wait(1);
        driver = null;
    }

    public void checkDefaultElementInDropdown(String css, String ItemText) throws TestRunError {
        Select select = new Select(driver.findElement(By.cssSelector(css)));
        List<WebElement> selectedItems = select.getAllSelectedOptions();
        System.out.println("Current selected items: " + selectedItems.size());
        WebElement element = selectedItems.get(0);
        boolean elementFound = false;
        String elementDefaultText = element.getText();
        if (selectedItems.size() == 1) {
            System.out.println("Selected item count: " + selectedItems.size());
            if (elementDefaultText.equals(ItemText)) ;
            {
                System.out.println("default selected item is: " + ItemText);
                elementFound = true;
            }
        } else {
            throw new TestRunError("Selected item count is more than 1 : " + selectedItems.size());
        }
        if (!elementFound) {
            throw new TestRunError("Selected item values is wrong: " + elementDefaultText);
        }
    }

    public void selectMultipleItemsByIndex(String css, int[] itemIndexes) throws TestRunError {
        Select select = new Select(driver.findElement(By.cssSelector(css)));
        select.deselectAll();
        this.hardWait(3);
        for (int itemIndex : itemIndexes) {
            select.selectByIndex(itemIndex);
        }
        List<WebElement> selectedItems = select.getAllSelectedOptions();
        for (WebElement element : selectedItems) {
            System.out.println("ElementText: " + element.getText());
        }
    }

    public void selectMultipleItemsByVisibleText(String css, String[] itemVisibleTexts) throws TestRunError {
        Select select = new Select(driver.findElement(By.cssSelector(css)));
        select.deselectAll();
        this.hardWait(3);
        for (String itemText : itemVisibleTexts) {
            select.selectByVisibleText(itemText);
        }
        List<WebElement> selectedItems = select.getAllSelectedOptions();
        for (WebElement element : selectedItems) {
            System.out.println("ElementText: " + element.getText());
        }
    }

    public void setValue(String cssLocator, String input) throws TestRunError {
        if (driver.findElement(By.cssSelector(cssLocator)).isDisplayed()) {
            System.out.println("Element is displayed - " + cssLocator);
            if (input.equalsIgnoreCase("PressEnterKey")) {
                driver.findElement(By.cssSelector(cssLocator)).sendKeys(Keys.ENTER);
            } else if (input.equalsIgnoreCase("PressEnterKey")) {
                driver.findElement(By.cssSelector(cssLocator)).sendKeys(Keys.ENTER);
            } else {
                driver.findElement(By.cssSelector(cssLocator)).sendKeys(input);
            }

        } else {
            throw new TestRunError("Element is not displayed - " + cssLocator);
        }
    }

    public void setValue(String cssLocator, Keys input) throws TestRunError {
        if (driver.findElement(By.cssSelector(cssLocator)).isDisplayed()) {
            System.out.println("Element is displayed - " + cssLocator);
            driver.findElement(By.cssSelector(cssLocator)).sendKeys(input);
        } else {
            throw new TestRunError("Element is not displayed - " + cssLocator);
        }
    }


    public void clearValue(String cssLocator, boolean useKeyBoard) throws TestRunError {
        if (driver.findElement(By.cssSelector(cssLocator)).isDisplayed()) {
            System.out.println("Element is displayed - " + cssLocator);
            if (useKeyBoard) {
                String textValue = driver.findElement(By.cssSelector(cssLocator)).getAttribute("value");
                System.out.println("TextValue: " + textValue);
                int textLength = textValue.length();
                for (int i = 0; i < textLength; i++) {
                    driver.findElement(By.cssSelector(cssLocator)).sendKeys(Keys.BACK_SPACE);
                    this.hardWait(1);
                }
            } else {
                driver.findElement(By.cssSelector(cssLocator)).clear();
            }

        } else {
            throw new TestRunError("Element is not displayed - " + cssLocator);
        }
    }

    public void selectDropdownItemByVisibleText(String css, String visibleText) throws TestRunError {
        WebElement element = driver.findElement(By.cssSelector(css));
        if (driver.findElement(By.cssSelector(css)).isDisplayed()) {
            Select select = new Select(element);
            List<WebElement> options = select.getOptions();
            boolean itemValueFound = false;
            for (WebElement option : options) {
                if (visibleText.equals(option.getText())) {
                    itemValueFound = true;
                    break;
                }
            }
            if (itemValueFound) {
                select.selectByValue(visibleText);
            } else {
                throw new TestRunError("Select dropdown - is provided with invalid visibleText: " + visibleText);
            }
        } else {
            throw new TestRunError("Select dropdown - is not displayed " + css);
        }
    }

    public void selectDropdownByRandom(String css) {
        Select select = new Select(driver.findElement(By.cssSelector(css)));
        List<WebElement> options = select.getOptions();
        int totalItems = options.size();
        select.selectByIndex(getRandomNumber(1, totalItems - 1));
    }

    private int getRandomNumber(int min, int max) {
        int random_int = (int) (Math.random() * (max - min + 1) + min);
        return random_int;
    }

    public void selectRadioButton(String css) {
        WebElement element = driver.findElement(By.cssSelector(css));
        if (!element.isSelected()) {
            element.click();
        }
    }

    public void clickElement(String css) throws TestRunError {
        WebElement element = driver.findElement(By.cssSelector(css));
        if (element.isDisplayed()) {
            this.hardWait(3);
            element.click();
        } else {
            throw new TestRunError("Element is not displayed: " + css);
        }
    }

    public String getElementText(String css) throws TestRunError {
        String elementText = null;
        WebElement element = driver.findElement(By.cssSelector(css));
        if (element.isDisplayed()) {
            elementText = element.getText();
        } else {
            throw new TestRunError("Element is not displayed: " + css);
        }
        return elementText;
    }

    public void selectDropdownItemByIndex(String css, int itemIndex) throws TestRunError {
        Select select = new Select(driver.findElement(By.cssSelector(css)));
        int totalItemSize = select.getOptions().size();
        if (itemIndex == 0 || itemIndex > totalItemSize - 1) {
            throw new TestRunError("Select dropdpwn - provided with invalid index: " + itemIndex);
        } else {
            select.selectByIndex(itemIndex);
        }

    }

    public void selectCheckBox(String css) {
        if (!driver.findElement(By.cssSelector(css)).isSelected()) {
            driver.findElement(By.cssSelector(css)).click();
        }
    }

    public void checkAllTheCheckBoxesSelected(String css) throws TestRunError {
        this.hardWait(2);
        List<WebElement> checkBoxes = driver.findElements(By.cssSelector(css));
        boolean allCheckBoxesSelected = false;
        for (WebElement element : checkBoxes) {
            if (element.isSelected()) {
                allCheckBoxesSelected = true;
            } else {
                allCheckBoxesSelected = false;
                break;
            }
        }
        if (allCheckBoxesSelected) {
            System.out.println("All checkboxes are selected:" + css);
        } else {
            throw new TestRunError("All checkbixes are not selected:" + css);
        }
    }

    public void clickOnDynamicLink(String css, String hrefReference) throws TestRunError {
        List<WebElement> links = driver.findElements(By.cssSelector(css));
        boolean linkFound = false;
        for (WebElement element : links) {
            String href = element.getAttribute("href");
            if (href != null) {
                if (href.contains(hrefReference) || href.equalsIgnoreCase(hrefReference)) {
                    linkFound = true;
                    element.click();
                    break;
                }
            }
        }
        if (linkFound) {
            System.out.println("Clicked on Link with href: " + hrefReference);
        } else {
            throw new TestRunError("Link not found with href: " + hrefReference);
        }
    }

    public void checkValuePresenceInTable(String css, String valueReference) throws TestRunError {
        WebElement table = driver.findElement(By.cssSelector(css));
        if (table.isDisplayed()) {
            boolean valueFound = false;
            List<WebElement> rows = table.findElements(By.cssSelector("tr"));
            outer:
            for (WebElement rw : rows) {
                List<WebElement> cells = rw.findElements(By.cssSelector("td"));
                for (WebElement cell : cells) {
                    String cellText = cell.getText();
                    if (cellText.trim().equalsIgnoreCase(valueReference.trim())) {
                        valueFound = true;
                        break outer;
                    }
                }
            }
            if (valueFound) {
                System.out.println("Value found in Table: " + valueReference);
            } else {
                throw new TestRunError("Value not found in Table: " + valueReference);
            }
        } else {
            throw new TestRunError("Table not found: " + css);
        }
    }

    public void dragAndDropElement(WebDriver driver, WebElement elementSource, WebElement elementTarget) throws TestRunError {
        Actions actions = new Actions(driver);
        actions.dragAndDrop(elementSource, elementTarget).build().perform();
        this.hardWait(5);
    }

    public void closePopup() {
        System.out.println("======Performing closePopup========");
        WebDriver driver = TestBase.getDriver();
        Alert alert = driver.switchTo().alert();
        if (alert != null) {
            alert.accept();
        } else {
            System.out.println("No Alert Found");
            WebDriverWait wait = new WebDriverWait(driver, 60);
            wait.until(ExpectedConditions.alertIsPresent());
            if (alert != null) {
                System.out.println("Alert Found");
            }
        }

    }

    public boolean jsErrorPresentInPage(WebDriver driver) {
        boolean errorPresent = false;
        JavascriptExecutor js = (JavascriptExecutor) driver;
        String error = (String) js.executeScript("var error = window.onerror;\n" +
                "\t\treturn JSON.stringify(error);");
        System.out.println("JSONErrorObject: " + error + "-" + error.getClass());
        if (!error.equals("null")) errorPresent = true;
        return errorPresent;
    }

    public String getMonthName(int month) {
        String[] monthNames = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
        return monthNames[(month - 1)];
    }

    public void takeScreenshot(String filePath) throws IOException {
        WebDriver driver = TestBase.getDriver();
        TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
        File screenshotFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(screenshotFile, new File(filePath));
    }
}
