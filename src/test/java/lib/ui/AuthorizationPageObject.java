package lib.ui;

import org.openqa.selenium.remote.RemoteWebDriver;

public class AuthorizationPageObject extends MainPageObject {

    private static final String
            LOGIN_BUTTON = "xpath://body//div/a[text()='Log in']",
            LOGIN_INPUT = "xpath://input[@name='wpName']",
            PASSWORD_INPUT = "xpath://input[@name='wpPassword']",
            SUBMIT_BUTTON = "xpath://button[@id='wpLoginAttempt']";

    public AuthorizationPageObject(RemoteWebDriver driver) {
        super(driver);
    }

    public void clickAuthButton() {
        this.waitForElementPresent(LOGIN_BUTTON, "Cannot find Auth button", 5);
        this.tryClickElementWithFewAttempts(LOGIN_BUTTON,"error",3);
        //this.waitForElementAndClick(LOGIN_BUTTON, "Cannot find and click Auth button", 5);
    }

    public void enterLoginData(String login, String password) {
        this.waitForElementAndSendKeys(LOGIN_INPUT, login, "Cannot find and put a login to the field", 5);
        this.waitForElementAndSendKeys(PASSWORD_INPUT, password, "Cannot find and put a password to the field", 5);
    }

    public void submitForm() {
        this.waitForElementAndClick(SUBMIT_BUTTON, "Cannot find and click submit button", 5);
    }
}
