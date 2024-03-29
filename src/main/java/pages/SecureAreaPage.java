package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SecureAreaPage {
    private final WebDriver driver;
    private final By statusAlert = By.id("flash");
    private final By logoutButton = By.xpath("//i[@class='icon-2x icon-signout']");
    public SecureAreaPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getAlertText() {
        return driver.findElement(statusAlert).getText();
    }

    public LoginPage clickLogoutButton() {
        driver.findElement(logoutButton).click();
        return new LoginPage(driver);
    }
}
