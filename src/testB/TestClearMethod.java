package testB;

import testA.TestBase;
import testA.TestRunError;

public class TestClearMethod {

    public static void main(String[] args) throws TestRunError, InterruptedException {

        TestBase.invokeApp("https://truweight.in/consultation/1.html");
        TestBase testBase = TestBase.getInstance();
        testBase.setValue("#name", "Guru");
        testBase.clearValue("#name", true);
        testBase.hardWait(5);
        TestBase.closeAllBrowserInstances();
    }
}
