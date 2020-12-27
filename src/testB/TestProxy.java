package testB;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import testA.TestBase;
import testA.TestRunError;

import java.util.concurrent.TimeUnit;

public class TestProxy {

    public static void main(String[] args) throws TestRunError, InterruptedException {

        Proxy proxy =new Proxy();
        // Set HTTP Port to 7777
        proxy.setHttpProxy("localhost:7777");
        // Create desired Capability object
        DesiredCapabilities cap=new DesiredCapabilities();
        // Pass proxy object p
        cap.setCapability(CapabilityType.PROXY, proxy);
        TestBase.invokeApp("https://www.etihad.com/en-in/book");
        WebDriver driver = TestBase.getDriver(cap);

        TestBase.closeAllBrowserInstances();

    }
}
