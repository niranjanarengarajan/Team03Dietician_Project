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

	public void waitForVisible(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
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


	public String getTextAfterVisibility(WebDriver driver, WebElement element, String elementName) {
	    try {
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
	        wait.until(ExpectedConditions.visibilityOf(element));
	        
	        String text = element.getText().trim();
	        
	        return text;
	    } catch (Exception e) {
	        LoggerLoad.error("Failed to get text for " + elementName + ". Error: " + e.getMessage());
	        throw e; 
	    }
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


    public String waitForCodeMirrorOutput(String elementId, int timeoutSeconds) {
        WebDriverWait customWait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds));
        WebElement output = customWait.until(d -> {
            WebElement el = d.findElement(By.id(elementId));
            String text = el.getAttribute("textContent").trim();
            return !text.isEmpty() ? el : null;
        });
        return output.getAttribute("textContent").trim();
    }
    
    public String getActualErrorMessage(List<WebElement> elements) {
	    try {
	       
	        WebElement activeError = waitForVisibility(driver, elements.get(0), 5);

	        if (activeError.isDisplayed()) {
	            String text = activeError.getText().trim();
	            LoggerLoad.info("Captured  error: " + text);
	            return text;
	        }
	    } catch (Exception e) {
	        LoggerLoad.error("No error message element was found on the page.");
	        return "No error message displayed";
	    }
	    return "No error message displayed";
	}
	
    

}