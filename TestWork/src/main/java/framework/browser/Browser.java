package framework.browser;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Set;

public class Browser implements WebDriver {
    private WebDriver driver;

    public WebDriver getDriver() {
        return driver;
    }

    public Browser(WebDriver driver) {
        this.driver = driver;
    }

    public void get(String s) {
        System.out.println("Go to URL " + s);
        driver.get(s);
    }

    public String getCurrentUrl() {
        System.out.println("Get current URL ");
        return driver.getCurrentUrl();
    }

    public String getTitle() {
        System.out.println("Get title");
        return driver.getTitle();
    }

    public List<WebElement> findElements(By by) {
        System.out.println("Find elements by " + by);
        return driver.findElements(by);
    }

    public WebElement findElement(By by) {
        highlight(driver.findElement(by));
        String xpath = getAbsoluteXPath(driver.findElement(by));
        System.out.println("Find element " + by);
        System.out.println("Find element by xpath " + xpath);
        return driver.findElement(by);
    }

    public String getPageSource() {
        System.out.println("Get page source");
        return driver.getPageSource();
    }

    public void close() {
        System.out.println("Close window");
        driver.close();
    }

    public void quit() {
        System.out.println("Quit");
        driver.quit();
    }

    public Set<String> getWindowHandles() {
        System.out.println("Get window handles");
        return driver.getWindowHandles();
    }

    public String getWindowHandle() {
        System.out.println("Get window handle");
        return driver.getWindowHandle();
    }

    public TargetLocator switchTo() {
        System.out.println("Switch to");
        return driver.switchTo();
    }

    public Navigation navigate() {
        System.out.println("navigate");
        return driver.navigate();
    }

    public Options manage() {
        System.out.println("Manage");
        return driver.manage();
    }

    private void highlight(WebElement element) {
        final int wait = 150;
        String originalStyle = element.getAttribute("style");
        try {
            setAttribute(element, "style",
                    "color: yellow; border: 5px solid yellow; background-color: black;");
            Thread.sleep(wait);
            setAttribute(element, "style",
                    "color: black; border: 5px solid black; background-color: yellow;");
            Thread.sleep(wait);
            setAttribute(element, "style",
                    "color: yellow; border: 5px solid yellow; background-color: black;");
            Thread.sleep(wait);
            setAttribute(element, "style",
                    "color: black; border: 5px solid black; background-color: yellow;");
            Thread.sleep(wait);
            setAttribute(element, "style", originalStyle);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void setAttribute(WebElement element, String attributeName, String value) {
        executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, attributeName, value);
    }

    public Object executeScript(String s, Object... args) {
        return ((JavascriptExecutor) this.driver).executeScript(s, args);
    }

    public String getAbsoluteXPath(WebElement element) {
        return (String) ((JavascriptExecutor) this.driver).executeScript(
                "function absoluteXPath(element) {" +
                        "var comp, comps = [];" +
                        "var parent = null;" +
                        "var xpath = '';" +
                        "var getPos = function(element) {" +
                        "var position = 1, curNode;" +
                        "if (element.nodeType == Node.ATTRIBUTE_NODE) {" +
                        "return null;" +
                        "}" +
                        "for (curNode = element.previousSibling; curNode; curNode = curNode.previousSibling) {" +
                        "if (curNode.nodeName == element.nodeName) {" +
                        "++position;" +
                        "}" +
                        "}" +
                        "return position;" +
                        "};" +

                        "if (element instanceof Document) {" +
                        "return '/';" +
                        "}" +

                        "for (; element && !(element instanceof Document); element = element.nodeType == Node.ATTRIBUTE_NODE ? element.ownerElement : element.parentNode) {" +
                        "comp = comps[comps.length] = {};" +
                        "switch (element.nodeType) {" +
                        "case Node.TEXT_NODE:" +
                        "comp.name = 'text()';" +
                        "break;" +
                        "case Node.ATTRIBUTE_NODE:" +
                        "comp.name = '@' + element.nodeName;" +
                        "break;" +
                        "case Node.PROCESSING_INSTRUCTION_NODE:" +
                        "comp.name = 'processing-instruction()';" +
                        "break;" +
                        "case Node.COMMENT_NODE:" +
                        "comp.name = 'comment()';" +
                        "break;" +
                        "case Node.ELEMENT_NODE:" +
                        "comp.name = element.nodeName;" +
                        "break;" +
                        "}" +
                        "comp.position = getPos(element);" +
                        "}" +

                        "for (var i = comps.length - 1; i >= 0; i--) {" +
                        "comp = comps[i];" +
                        "xpath += '/' + comp.name.toLowerCase();" +
                        "if (comp.position !== null) {" +
                        "xpath += '[' + comp.position + ']';" +
                        "}" +
                        "}" +

                        "return xpath;" +

                        "} return absoluteXPath(arguments[0]);", element);
    }
}
