package keys;

import base.BaseTest;
import org.openqa.selenium.Keys;
import org.testng.annotations.Test;
import pages.KeyPressesPage;

import static org.assertj.core.api.Assertions.assertThat;

public class KeysTests extends BaseTest {

    @Test(testName = "Backspace key test", groups = {"regression"})
    public void testBackSpace() throws InterruptedException {
        KeyPressesPage keyPage = homePage.clickKeyPresses();
        keyPage.enterText("A" + Keys.BACK_SPACE);
        assertThat(keyPage.getResult()).isEqualTo("You entered: BACK_SPACE");
    }

    @Test(testName = "Beta special character test", groups = {"regression"})
    public void testBeta() throws InterruptedException {
        KeyPressesPage keyPage = homePage.clickKeyPresses();
        keyPage.enterBeta();
        assertThat(keyPage.getResult()).isEqualTo("You entered: O");
    }
}
