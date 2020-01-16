package application.pages;

import framework.utils.ConfigUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class MainPage extends BasePage {
    public MainPage() {
        super();
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//img[@class='gb_ua']")
    WebElement mainPage;

    @FindBy(xpath = "//span[@class='gb_Ia gbii']")
    WebElement userIcon;

    @FindBy(id = "gb_71")
    WebElement exitButton;

    @FindBy(xpath = "//div[@class='T-I J-J5-Ji T-I-KE L3']")
    WebElement writeMessageButton;

    @FindBy(name = "to")
    WebElement addressInput;

    @FindBy(name = "subjectbox")
    WebElement subjectInput;

    @FindBy(xpath = "//div[@class='dC']//div[@role='button']")
    WebElement sendButton;

    final String listMessageLocation = "//tbody//tr[@jsmodel]";
    @FindBy(xpath = listMessageLocation)
    List<WebElement> listMessage;

    public boolean isMainPageDisplayed() {
        return mainPage.isDisplayed();
    }

    public void exitFromService() {
        userIcon.click();
        exitButton.click();
    }

    public void writeMessage(String address, String subject, String message) {
        writeMessageButton.click();
        addressInput.sendKeys(address);
        subjectInput.sendKeys(subject + Keys.TAB + message);
        sendButton.click();
    }

    public int getMessagesNumber() {
        return listMessage.size();
    }

    public boolean waitMessage(int messageNumber) {
        WebDriverWait wait = new WebDriverWait(driver, Long.parseLong(ConfigUtil.getConfig("messageWait")));
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.xpath(listMessageLocation), messageNumber));
        return messageNumber < listMessage.size();
    }

    public void clickOnLastMessage() {
        listMessage.get(0).click();
    }
}
