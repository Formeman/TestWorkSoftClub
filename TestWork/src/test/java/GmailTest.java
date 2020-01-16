import application.pages.AuthorizationPage;
import application.pages.MainPage;
import application.pages.MessagePage;
import framework.driverSingleton.WebDriverSingleton;
import framework.utils.ConfigUtil;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class GmailTest {
    WebDriver driver;
    AuthorizationPage authorizationPage;
    MainPage mainPage;
    MessagePage messagePage;

    @BeforeMethod
    public void beforeMethod() {
        driver = WebDriverSingleton.create();

        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(Long.parseLong(ConfigUtil.getConfig("pageLoadTimeout")), TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(Long.parseLong(ConfigUtil.getConfig("implicitlyWait")), TimeUnit.SECONDS);

        driver.get(ConfigUtil.getConfig("url"));

        authorizationPage = new AuthorizationPage();
        mainPage = new MainPage();
        messagePage = new MessagePage();
    }

    @AfterMethod
    public void afterMethod() {
        WebDriverSingleton.kill();
    }

    @Parameters({"email", "password"})
    @Test
    public void firstCase(String email, String password) {
        authorizationPage.setEmail(email);
        authorizationPage.setPassword(password);

        Assert.assertTrue(mainPage.isMainPageDisplayed(), "Main page should be displayed");
    }

    @Parameters({"email", "password"})
    @Test
    public void secondCase(String email, String password) {
        authorizationPage.authorization(email, password);

        mainPage.exitFromService();
        Assert.assertTrue(authorizationPage.isAuthorizationPageDisplayed(), "Authorization page should be displayed");
    }

    @Parameters({"email", "password", "subject", "message"})
    @Test
    public void thirdCase(String email, String password, String subject, String message) {
        authorizationPage.authorization(email, password);

        int messagesNumber = mainPage.getMessagesNumber();
        mainPage.writeMessage(email, subject, message);
        Assert.assertTrue(mainPage.waitMessage(messagesNumber), "Message should be displayed");
        mainPage.clickOnLastMessage();
        Assert.assertTrue(messagePage.getTextOfMessage(message), "Text must be the same");

        mainPage.exitFromService();
    }
}
