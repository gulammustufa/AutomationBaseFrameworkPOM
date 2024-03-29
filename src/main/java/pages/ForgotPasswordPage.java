package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ForgotPasswordPage {
	private final WebDriver driver;
	private final By emailField = By.id("email");
	
	public ForgotPasswordPage(WebDriver driver) {
		this.driver = driver;
	}
	
	public void setEmail(String email) {
		driver.findElement(emailField).sendKeys(email);
	}
	public EmailSentPage submitForgotForm() {
		driver.findElement(By.id("form_submit")).click();
		return new EmailSentPage(driver);
	}
}
