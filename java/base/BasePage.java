package base;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utils.ConfigReader;

public class BasePage {

    protected WebDriver driver;
    protected WebDriverWait wait;

    // Constructor
    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(
        	    driver,
        	    Duration.ofSeconds(
        	        Long.parseLong(
        	            ConfigReader.getProperty("explicitWait")
        	        )
        	    )
        	);
    }

    // =========================================================
    // WAIT METHODS - By
    // =========================================================

    protected void waitForVisible(By locator) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    protected void waitForClickable(By locator) {
        wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    protected void waitForPresence(By locator) {
        wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    protected void waitForText(By locator, String text) {
        wait.until(
            ExpectedConditions.textToBePresentInElementLocated(locator, text)
        );
    }

    protected void waitForInvisibility(By locator) {
        wait.until(
            ExpectedConditions.invisibilityOfElementLocated(locator)
        );
    }

    // =========================================================
    // WAIT METHODS - WebElement
    // =========================================================

    protected void waitForVisible(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    protected void waitForClickable(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    // =========================================================
    // CLICK METHODS
    // =========================================================

    protected void click(By locator) {

        int attempts = 0;

        while (attempts < 3) {

            try {

                waitForClickable(locator);

                driver.findElement(locator).click();

                return;

            } catch (StaleElementReferenceException e) {

                attempts++;
            }
        }

        throw new RuntimeException(
            "Unable to click element after 3 attempts: " + locator
        );
    }

    protected void click(WebElement element) {

        waitForClickable(element);
        element.click();
    }

    // =========================================================
    // TYPE METHOD
    // =========================================================

    protected void type(By locator, String text) {

        waitForVisible(locator);

        WebElement element = driver.findElement(locator);

        element.clear();
        element.sendKeys(text);
    }

    // =========================================================
    // GET TEXT
    // =========================================================

    protected String getText(By locator) {

        waitForVisible(locator);

        return driver.findElement(locator).getText();
    }

    // =========================================================
    // IS DISPLAYED
    // =========================================================

    protected boolean isDisplayed(By locator) {

        try {

            waitForVisible(locator);

            return driver.findElement(locator).isDisplayed();

        } catch (Exception e) {

            return false;
        }
    }

    protected boolean isDisplayed(WebElement element) {

        try {

            return element.isDisplayed();

        } catch (Exception e) {

            return false;
        }
    }
}