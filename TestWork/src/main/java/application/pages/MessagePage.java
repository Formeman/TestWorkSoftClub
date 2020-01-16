package application.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MessagePage extends BasePage {
    public MessagePage() {
        super();
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//div[@class='AO']")
    WebElement messagePage;

    private String locationByText = "//div[contains(text(),'%s')]";

    public boolean getTextOfMessage(String message) {
        return messagePage.findElement(By.xpath(String.format(locationByText, message))).isDisplayed();
    }
}