package testA;

import java.util.regex.Pattern;

public class BMIResult extends TestBase {

    public boolean checkBmiResult() throws TestRunError {
        String bmiHeaderText = this.getElementText("h4#bmilabel");
        System.out.println("BmiResultHeader: " + bmiHeaderText);
        String[] result = bmiHeaderText.split("Your BMI is ");
        System.out.println("BmiResultHeader: " + bmiHeaderText);
        System.out.println("BmiResultHeader-with whole text pattern: " + Pattern.matches("[a-z|A-Z|\\s]*[0-9]{2}.[0-9]{2}", result[1]));
        if (Pattern.matches("[0-9]{2}.[0-9]{2}", result[1])) {
            return true;
        } else {
            return false;
        }
    }

    public String getBmiResult() throws TestRunError {
        return this.getElementText("h4#bmilabel");
    }

    public void clickOnVisitOurStore() throws TestRunError {
        this.clickElement("btn btn-danger main-button");
    }

    public void clickOnNext() throws TestRunError {
        this.clickElement(PageCommon.ELEMENT_NEXT_BUTTON);
    }
}
