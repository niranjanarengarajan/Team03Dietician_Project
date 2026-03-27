package utils;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class ConfigReader {
	private static Properties prop;
	public static Properties initializeProperties() {
		Properties prop = new Properties();
		File propFile = new File(System.getProperty("user.dir") + "\\src\\test\\resources\\Config.properties");

		try {
			FileInputStream fis = new FileInputStream(propFile);
			prop.load(fis);
		} catch (Throwable e) {
			e.printStackTrace();
		}

		return prop;
	}

//	public static String getProperty(String key) {
//		return initializeProperties().getProperty(key);
//	}

	public static String getProperty(String key) {
        // If prop is null, initialize it once. Otherwise, just return the value.
        if (prop == null) {
            initializeProperties();
        }
        return prop.getProperty(key);
    }
}