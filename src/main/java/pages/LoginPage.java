package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
    private final WebDriver driver;
    private final By usernameField = By.id("username");
    private final By passwordField = By.id("password");
    private final By loginButton = By.cssSelector("#login button");
    private final By loginFailedMessageField = By.id("flash");
    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void setUsername(String username) {
        driver.findElement(usernameField).sendKeys(username);
    }

    public void setPassword(String password) {
        driver.findElement(passwordField).sendKeys(password);
    }

    public String getFailLoginMessage() {
        return driver.findElement(loginFailedMessageField).getText();
    }

    public SecureAreaPage clickLoginButton() {
        driver.findElement(loginButton).click();
        return new SecureAreaPage(driver);
    }
}
