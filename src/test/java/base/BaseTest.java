package base;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import drivers.DriverFactory;
import utils.ConfigReader;

public class BaseTest {

    protected WebDriver driver;

    @BeforeMethod(alwaysRun = true)
    public void setup() {

        String browser =
            ConfigReader.getProperty("browser");

        DriverFactory.createDriver(browser);

        driver = DriverFactory.getDriver();
    }

    @AfterMethod
    public void tearDown() {

        DriverFactory.quitDriver();
    }
}