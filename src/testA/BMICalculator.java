package testA;

public class BMICalculator extends TestBase {

    public void calculateBMI(String userName, String age, String weight) throws TestRunError {

        this.setValue(PageBMICalculator.ELEMENT_USERNAME_EDITBOX, userName);
        this.setValue(PageBMICalculator.ELEMENT_AGE_EDITBOX, age);
        this.setValue(PageBMICalculator.ELEMENT_WEIGHT_EDITBOX, weight);
        this.selectDropdownByRandom(PageBMICalculator.ELEMENT_HEIGHT_DROPDOWN);
        //select radio button
        this.selectRadioButton(PageBMICalculator.ELEMENT_GENDER_MALE_RADIO);
        this.clickElement(PageBMICalculator.ELEMENT_NEXT_BUTTON);
    }

    void calculateBMI(String userName, String age, String weight, int heightIndex) throws TestRunError {
        this.setValue("#name", userName);
        this.setValue("#age", age);
        this.setValue("#weight", weight);
        this.selectDropdownItemByIndex(".form-control.height", heightIndex);
        //select radio button
        this.selectRadioButton("input[value='Male']~span");
        this.clickElement("button#nextBtn");
    }

}
