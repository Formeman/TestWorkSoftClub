
package framework.driverSingleton;

import framework.browser.Browser;
import framework.factory.ChromeDriverFactory;
import framework.factory.FireFoxFactory;
import framework.factory.RemoteFactory;
import framework.factory.WebDriverFactory;
import framework.utils.ConfigUtil;
import org.openqa.selenium.WebDriver;

public class WebDriverSingleton {
    static WebDriverFactory factory;
    static ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();

    private WebDriverSingleton() {
    }

    public static WebDriver create() {
        String browserName = ConfigUtil.getConfig("browserName");
        boolean isRemote = Boolean.parseBoolean(ConfigUtil.getConfig("isRemote"));
        if (browserName == null) {
            browserName = "chrome";
        }

        if (browserName.equalsIgnoreCase("firefox")) {
            factory = new FireFoxFactory();
        } else if (browserName.equalsIgnoreCase("chrome")) {
            factory = new ChromeDriverFactory();
        }
        if (isRemote) {
            factory = new RemoteFactory(browserName);
        }

        if (driver.get() == null) {
            driver.set(factory.create());
        }
        return new Browser(driver.get());
    }

    public static void kill() {
        if (driver.get() != null) {
            driver.get().quit();
        }
        driver.set(null);
    }
}

