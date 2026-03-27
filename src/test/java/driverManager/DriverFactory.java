package driverManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class DriverFactory {
	
	public static ThreadLocal<WebDriver> mydriver = new ThreadLocal<>();
	private static final Logger logger = LoggerFactory.getLogger(DriverFactory.class);

	public void launchBrowser(String browser) {
		WebDriver driver;
		logger.info("Launching browser: {}", browser);

		try {
			
			if (browser.equalsIgnoreCase("Chrome")){
				ChromeOptions co = new ChromeOptions();
										
				co.addArguments("--headless=new");
				co.setPageLoadStrategy(PageLoadStrategy.NORMAL);
					
				driver = new ChromeDriver(co);
				mydriver.set(driver);
				logger.info("Chrome browser launched successfully.");
			}
			else if (browser.equalsIgnoreCase("Firefox")) {
	            FirefoxOptions fo = new FirefoxOptions();
	            fo.addArguments("-headless"); // Firefox uses a single dash
	            driver = new FirefoxDriver(fo);
	            mydriver.set(driver);
				logger.info("Firefox browser launched successfully.");
			}
			else if (browser.equalsIgnoreCase("Edge")) {
	            EdgeOptions eo = new EdgeOptions();
	            eo.addArguments("--headless=new"); // Edge is Chromium-based
	            driver = new EdgeDriver(eo);
	            mydriver.set(driver);
				logger.info("Edge browser launched successfully.");
			}
			else {
				logger.error("Browser not supported: {}", browser);
				throw new IllegalArgumentException("Browser not supported: " + browser);
			}

			getDriver().manage().deleteAllCookies();
			logger.info("Deleted all browser cookies.");

			getDriver().manage().window().maximize();
			logger.info("Browser window maximized.");

			getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			logger.info("Implicit wait set to 10 seconds.");

		} catch (Exception e) {
			logger.error("Error while launching browser: {}", e.getMessage());
			throw e;
		}
	}

	public static WebDriver getDriver() {
		logger.debug("Fetching WebDriver instance from ThreadLocal.");
		return mydriver.get();
	}
	
	public void closeDriver() {
	    if (getDriver() != null) {
	        getDriver().quit();
	        mydriver.remove(); // This clears the thread memory
	        logger.info("Driver quit and removed from ThreadLocal.");
	    }
	
	}
	
}
