package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import base.BasePage;

public class LoginPage extends BasePage {

    @FindBy(id = "user-name")
    private WebElement username;

    @FindBy(id = "password")
    private WebElement password;

    @FindBy(id = "login-button")
    private WebElement loginButton;

    public LoginPage(WebDriver driver) {

        super(driver);

        PageFactory.initElements(driver, this);
    }

    public void enterUsername(String username) {

        waitForVisible(this.username);

        this.username.clear();
        this.username.sendKeys(username);
    }

    public void enterPassword(String password) {

        waitForVisible(this.password);

        this.password.clear();
        this.password.sendKeys(password);
    }

    public void clickLogin() {

        click(loginButton);
    }

    public void login(String username, String password) {

        enterUsername(username);
        enterPassword(password);
        clickLogin();
    }
}