package testA;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class TestBrowserType {

    @BeforeClass
    public void setUpTest() throws IOException, TestRunError, InterruptedException {
        String projPath = System.getProperty("user.dir");
        System.out.println("ProjectPath: " + projPath);
        InputStream file = new FileInputStream(projPath + "\\src\\testA\\appconfig.properties");
        Properties properties = new Properties();
        properties.load(file);
        String appURL = properties.getProperty("appURL");
        String browserType = properties.getProperty("browserType");
        TestBase.invokeApp(browserType, appURL);
    }

    @Test
    public void testBMIBrowserType() throws TestRunError {
        BMICalculator bmiCalculator = new BMICalculator();
        BMIResult bmiResult = new BMIResult();
        BMiGoal bMiGoal = new BMiGoal();
        bmiCalculator.calculateBMI("Guru", "37", "85", 7);
        boolean testResult = bmiResult.checkBmiResult();
        Assert.assertTrue(testResult);
    }

    @AfterClass
    public void tearDownTest() throws TestRunError {
        TestBase.closeAllBrowserInstances();
    }
}
