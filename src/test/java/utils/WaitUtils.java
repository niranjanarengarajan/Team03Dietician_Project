package utils;

import java.time.Duration;
import java.util.List;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitUtils {

    private final WebDriver driver;
    private final WebDriverWait wait;

    public WaitUtils(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public WebElement waitForClickable(WebElement element) {
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public void waitForPageLoad() {
        wait.until(d -> ((JavascriptExecutor) d).executeScript("return document.readyState").equals("complete"));
    }

    public boolean waitForVisibilityOfAll(List<WebElement> elements) {
        try {
            wait.until(ExpectedConditions.visibilityOfAllElements(elements));
            return !elements.isEmpty();
        } catch (TimeoutException e) {
            return false;
        }
    }

    public static void waitForUrlContains(WebDriver driver, String fragment, int timeoutSeconds) {
        new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds))
            .until(ExpectedConditions.urlContains(fragment));
    }

    public static WebElement waitForVisibility(WebDriver driver, WebElement element, int timeoutSeconds) {
        return new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds))
            .until(ExpectedConditions.visibilityOf(element));
    }

    public static String getVisibleText(WebDriver driver, WebElement element, int timeoutSeconds) {
        return waitForVisibility(driver, element, timeoutSeconds).getText().trim();
    }

    public static boolean isVisible(WebDriver driver, WebElement element, int timeoutSeconds) {
        try {
            new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds))
                .until(ExpectedConditions.visibilityOf(element));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

}