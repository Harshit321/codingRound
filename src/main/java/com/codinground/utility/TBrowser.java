package com.codinground.utility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.sun.javafx.PlatformUtil;

@SuppressWarnings("restriction")
public class TBrowser {

	// instance of singleton class
	private static TBrowser instance = null;

	private static WebDriver driver;

	static {
		if (PlatformUtil.isMac()) {
			System.setProperty("webdriver.chrome.driver", "chromedriver_mac");
		} else if (PlatformUtil.isWindows()) {
			System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
		} else if (PlatformUtil.isLinux()) {
			System.setProperty("webdriver.chrome.driver", "chromedriver_linux");
		}
	}

	// Constructor
	private TBrowser() {

	}

	// TO create instance of class
	public static TBrowser getInstance() {
		if (instance == null) {
			instance = new TBrowser();
		}
		return instance;
	}

	public WebDriver launchUrl(String url) throws Exception {
		try {
			if (driver == null) {
				driver = new ChromeDriver();

			}
		} catch (Exception ex) {
			throw ex;
		}
		driver.get(url);
		driver.manage().window().maximize();
		return driver;
	}
}