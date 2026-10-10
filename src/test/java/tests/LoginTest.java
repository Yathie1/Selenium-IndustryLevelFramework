package tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.LoginPage;
import testdata.LoginData;
import testdata.LoginDataSet;
import utils.ConfigReader;
import utils.JsonDataReader;

public class LoginTest extends BaseTest {

    @DataProvider(name = "loginUsers")
    public Object[][] loginUsers() {

        LoginDataSet data =
                JsonDataReader.readJson(
                        "src/test/resources/testdata/login.json",
                        LoginDataSet.class
                );

        return new Object[][] {
                { data.getValidUser() },
                { data.getProblemUser() },
                { data.getLockedUser() }
        };
    }

    @Test(
            groups = "smoke",
            dataProvider = "loginUsers"
    )
    public void verifyLoginTest(LoginData loginData) {

        // Open the application
        driver.get(
                ConfigReader.getRequiredProperty("baseUrl")
        );

        // Create the Page Object
        LoginPage loginPage = new LoginPage(driver);

        // Perform login
        loginPage.login(
                loginData.getUsername(),
                loginData.getPassword()
        );

        System.out.println(
                "Testing user: " + loginData.getUsername()
        );

        // Validate the expected result
        String expectedResult = loginData.getExpectedResult();

        if ("success".equalsIgnoreCase(expectedResult)) {

            Assert.assertTrue(
                    driver.getCurrentUrl().contains("inventory"),
                    "Expected successful login, but inventory page was not opened. "
                            + "Actual URL: " + driver.getCurrentUrl()
            );

        } else if ("locked".equalsIgnoreCase(expectedResult)) {

            Assert.assertTrue(
                    loginPage.isErrorMessageDisplayed(),
                    "Expected an error message after unsuccessful login"
            );

            String actualError = loginPage.getErrorMessage();

            Assert.assertTrue(
                    actualError.toLowerCase().contains("locked out"),
                    "Expected a locked-out error, but got: " + actualError
            );

        } else {

            Assert.fail(
                    "Unsupported expected result: " + expectedResult
            );
        }
}}