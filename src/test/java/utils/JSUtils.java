package utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class JSUtils {
	private JavascriptExecutor js;
	
      public JSUtils(WebDriver driver) {
       this.js = (JavascriptExecutor) driver;
      }

    public void scrollIntoView(WebElement element) {
       js.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public String getPageInnerText() {
        return (String) js.executeScript("return document.documentElement.innerText;");
    }

    public void clickElement(WebElement element) {
        js.executeScript("arguments[0].click();", element);
    }
    public boolean hasScroll(WebElement element) {
        return (Boolean) js.executeScript(
            "return arguments[0].scrollHeight > arguments[0].clientHeight;", element
        );
    }
}

