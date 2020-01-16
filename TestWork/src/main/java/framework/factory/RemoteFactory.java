package framework.factory;

import framework.utils.ConfigUtil;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class RemoteFactory extends WebDriverFactory {
    private String browserName;

    public RemoteFactory(String browserName) {
        this.browserName = browserName;
    }

    @Override
    public WebDriver create() {
        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.acceptInsecureCerts();
        capabilities.setBrowserName(browserName);
        try {
            return new RemoteWebDriver(new URL(ConfigUtil.getConfig("remoteUrl")), capabilities);
        } catch (MalformedURLException e) {
            return null;
        }
    }
}
