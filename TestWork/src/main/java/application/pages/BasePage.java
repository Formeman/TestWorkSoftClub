package application.pages;

import framework.driverSingleton.WebDriverSingleton;
import framework.utils.ConfigUtil;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {
    protected WebDriver driver = WebDriverSingleton.create();

    protected void waitElementBeClickable(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, Long.parseLong(ConfigUtil.getConfig("fluentWait")));
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }
}

