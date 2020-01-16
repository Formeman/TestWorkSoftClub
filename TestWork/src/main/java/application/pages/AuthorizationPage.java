package application.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AuthorizationPage extends BasePage {
    public AuthorizationPage() {
        super();
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "initialView")
    WebElement authorizationPage;

    @FindBy(xpath = "//input[@type='email']")
    WebElement emailInput;

    @FindBy(id = "identifierNext")
    WebElement setEmailButton;

    @FindBy(xpath = "//input[@type='password']")
    WebElement passwordInput;

    @FindBy(id = "passwordNext")
    WebElement setPasswordButton;

    public boolean isAuthorizationPageDisplayed() {
        return authorizationPage.isDisplayed();
    }

    public void setEmail(String email) {
        emailInput.sendKeys(email);
        setEmailButton.click();
    }

    public void setPassword(String password) {
        waitElementBeClickable(passwordInput);
        passwordInput.sendKeys(password);
        setPasswordButton.click();
    }

    public void authorization(String email, String password) {
        setEmail(email);
        setPassword(password);
    }
}
