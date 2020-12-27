package testA;

import java.util.regex.Pattern;

public class BMiGoal extends TestBase {

    public static final String GOAL_LOSE_WEIGHT = "LoseWeight";
    public static final String GOAL_ALL_OF_THOSE = "AllOfThose";

    public void selectGoal(String goalName) throws TestRunError {
        if (goalName.equalsIgnoreCase(GOAL_LOSE_WEIGHT)) {
            this.selectCheckBox("input[value='Lose Weight']~span");
        } else if (goalName.equalsIgnoreCase(GOAL_ALL_OF_THOSE)) {
            this.selectCheckBox("input[value='All of these']~span");
            this.checkAllTheCheckBoxesSelected("#lookFor input");
        }
    }
}
