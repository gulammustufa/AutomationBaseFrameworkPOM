package forgotPassword;

import base.BaseTest;
import org.testng.annotations.Test;
import pages.EmailSentPage;
import pages.ForgotPasswordPage;

import static org.assertj.core.api.Assertions.assertThat;

public class ForgotPasswordTests extends BaseTest {
	@Test(testName = "Forgot password")
	public void testForgotPasswordSuccess() throws InterruptedException {
		ForgotPasswordPage forgotPassword = homePage.clickForgotPassword();
		forgotPassword.setEmail("abc@gmail.com");
		EmailSentPage emailSent = forgotPassword.submitForgotForm();
		Thread.sleep(5000);
		String emailMessage = emailSent.getMessage();
		assertThat(emailMessage).isEqualTo("Internal Server Error");
	}
}
