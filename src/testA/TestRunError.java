package testA;

public class TestRunError extends Exception{
    TestRunError(String exceptionMessage){
        System.out.println("Test execution is failed, due to: "+ exceptionMessage);
    }
}
