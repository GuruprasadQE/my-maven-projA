package testC;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TestNG_Priorities {

    @BeforeClass
    public void beforeClass() {
        System.out.println("Executed - beforeClass");
    }

    @Test(priority = 2)
    public void testCaseMethodA() {
        System.out.println("Executed - testCaseMethodA");
    }

    @Test(priority = 1)
    public void testCaseMethodB() {
        System.out.println("Executed - testCaseMethodB");
    }

    @AfterClass
    public void afterClass() {
        System.out.println("Executed - afterClass");
    }

}
