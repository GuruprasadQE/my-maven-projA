package testA;

public class TestBMICase {

    public static void main(String[] args) throws TestRunError, InterruptedException {
        TestBase.invokeApp("https://truweight.in/consultation/1.html");
        BMICalculator bmiCalculator = new BMICalculator();
        BMIResult bmiResult = new BMIResult();
        BMiGoal bMiGoal = new BMiGoal();
        bmiCalculator.calculateBMI("Guru", "37", "85", 7);
        bmiResult.checkBmiResult();
        bmiResult.clickOnNext();
        bmiResult.hardWait(4);
        bMiGoal.selectGoal(BMiGoal.GOAL_ALL_OF_THOSE);
        bmiResult.hardWait(4);
        TestBase.closeAllBrowserInstances();
    }


}
