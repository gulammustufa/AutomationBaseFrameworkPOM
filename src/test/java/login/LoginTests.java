package login;

import base.BaseTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.SecureAreaPage;

import static org.assertj.core.api.Assertions.assertThat;

public class LoginTests extends BaseTest {

    @Test(testName = "Login with invalid credentials", groups = {"smoke", "regression"})
    public void testLoginWithInvalidCredentials() {
        LoginPage loginPage = homePage.clickFormAuthentication();
        loginPage.setUsername("WrongUsername");
        loginPage.setPassword("SuperSecretPassword!");
        extentTest.info("Wrong Username: WrongUsername");
        extentTest.info("Wrong Password: SuperSecretPassword!");
        loginPage.clickLoginButton();
        assertThat(loginPage.getFailLoginMessage()).contains("Your username is vv invalid!");
    }

    @Test(testName = "Login with invalid credentials using data provider", groups = {"smoke", "regression"}, dataProvider = "InvalidLoginCredentials")
    public void testLoginWithValidCredentialsWithDataProvider(String username, String password) {
        LoginPage loginPage = homePage.clickFormAuthentication();
        loginPage.setUsername(username);
        loginPage.setPassword(password);
        extentTest.info("Invalid Username: " + username);
        extentTest.info("Invalid Password: " + password);
        SecureAreaPage secureAreaPage = loginPage.clickLoginButton();
        assertThat(secureAreaPage.getAlertText()).contains("Your username is invalid!");
    }

    @Test(testName = "Login with valid credentials", groups = {"smoke", "regression"})
    public void testLoginWithValidCredentials() {
        String username = "tomsmith";
        String password = "SuperSecretPassword!";
        LoginPage loginPage = homePage.clickFormAuthentication();
        loginPage.setUsername(username);
        loginPage.setPassword(password);
        extentTest.info("Valid Username: " + username);
        extentTest.info("Valid Password: " + password);
        SecureAreaPage secureAreaPage = loginPage.clickLoginButton();
        assertThat(secureAreaPage.getAlertText()).contains("You logged into a secure area!");
    }


    @DataProvider(name = "InvalidLoginCredentials")
    public static Object[][] invalidLoginCredentials() {
        return new Object[][]{
                {"WrongUserOne", "WrongPasswordOne"},
                {"WrongUserTwo", "WrongPasswordTwo"}
        };
    }

}
