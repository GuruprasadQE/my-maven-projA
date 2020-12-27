package testC;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;

public class TestNG_ExpectedException {
    Calc calc;

    @BeforeClass
    public void beforeClass() {
        System.out.println("Executed - beforeClass");
    }

    @Test( expectedExceptions = { NullPointerException.class } )
    public void testCaseMethodA() {
        System.out.println("Executed - testCaseMethodA");
        calc.additionResult(2,3);
    }

    @AfterClass
    public void afterClass() {
        System.out.println("Executed - afterClass");
    }

}
