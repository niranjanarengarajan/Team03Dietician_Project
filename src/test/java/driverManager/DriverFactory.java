package driverManager;

import utils.LoggerLoad;
import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverFactory {
	
	public static ThreadLocal<WebDriver> mydriver = new ThreadLocal<>();

	public void launchBrowser(String browser) {

		LoggerLoad.info("Launching browser: {}", browser);

		try {
			if (browser.equalsIgnoreCase("Chrome")) {
				mydriver.set(new ChromeDriver());
				LoggerLoad.info("Chrome browser launched successfully.");
			}
			else if (browser.equalsIgnoreCase("Firefox")) {
				mydriver.set(new FirefoxDriver());
				LoggerLoad.info("Firefox browser launched successfully.");
			}
			else if (browser.equalsIgnoreCase("Edge")) {
				mydriver.set(new EdgeDriver());
				LoggerLoad.info("Edge browser launched successfully.");
			}
			else {
				LoggerLoad.error("Browser not supported: "+ browser);
				throw new IllegalArgumentException("Browser not supported: " + browser);
			}

			getDriver().manage().deleteAllCookies();
			LoggerLoad.info("Deleted all browser cookies.");

			getDriver().manage().window().maximize();
			LoggerLoad.info("Browser window maximized.");

			getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			LoggerLoad.info("Implicit wait set to 10 seconds.");

		} catch (Exception e) {
			LoggerLoad.error("Error while launching browser: "+ e.getMessage());
			throw e;
		}
	}

	public WebDriver getDriver() {
		LoggerLoad.debug("Fetching WebDriver instance from ThreadLocal.");
		return mydriver.get();
	}
	
	public void closeDriver() {
	    if (getDriver() != null) {
	        getDriver().quit();
	        mydriver.remove(); // This clears the thread memory
	        LoggerLoad.info("Driver quit and removed from ThreadLocal.");
	    }
	
	}
}
