package framework.factory;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class FireFoxFactory extends WebDriverFactory {
    @Override
    public WebDriver create() {
        WebDriverManager.firefoxdriver().setup();
        return new FirefoxDriver();
    }
}
