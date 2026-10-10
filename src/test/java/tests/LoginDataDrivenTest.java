package tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.LoginPage;

public class LoginDataDrivenTest extends BaseTest {

    @DataProvider(name = "loginData")
    public Object[][] loginData() {

        return new Object[][] {
            {"standard_user", "secret_sauce"},
            {"problem_user", "secret_sauce"}
        };
    }

    @Test(dataProvider = "loginData", groups = "smoke")
    public void verifyLogin(String username, String password) {

        driver.get("https://www.saucedemo.com/");

        LoginPage loginPage = new LoginPage(driver);

        loginPage.login(username, password);

        Assert.assertTrue(
            driver.getCurrentUrl().contains("inventory")
        );
    }
}