package dropdown;

import base.BaseTest;
import org.testng.annotations.Test;
import pages.DropdownPage;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class DropdownTests extends BaseTest {
    @Test(testName = "Dropdown selection test")
    public void testSelectOption() {
        String option = "Option 1";
        extentTest.info("Option to be selected: " + option);

        DropdownPage dropDownPage = homePage.clickDropDown();
        dropDownPage.selectFromDropDown(option);
        extentTest.info("Selected option: " + option);
        List<String> selectedOptions = dropDownPage.getSelectedOptions();

        assertThat(selectedOptions.size()).isEqualTo(1);
        assertThat(selectedOptions).contains(option);
    }
}
