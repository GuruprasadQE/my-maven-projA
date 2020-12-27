package testC;

import org.testng.annotations.*;

public class TestNG_Basic {

    @BeforeClass
    public void beforeClass(){
        System.out.println("Executed - beforeClass");
    }
    @Test
    public void testCaseMethodA(){
        System.out.println("Executed - testCaseMethodA");
    }

    @AfterClass
    public void afterClass(){
        System.out.println("Executed - afterClass");
    }

}
