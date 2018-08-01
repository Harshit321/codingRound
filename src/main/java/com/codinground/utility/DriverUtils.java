package com.codinground.utility;

import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DriverUtils {

	private WebDriver driver;

	public DriverUtils(WebDriver driver1) {
		driver = driver1;
	}

	public void waitFor(int durationInMilliSeconds) throws InterruptedException {
		try {
			Thread.sleep(durationInMilliSeconds);
		} catch (InterruptedException e) {
			throw e; // To change body of catch statement use File | Settings | File Templates.
		}
	}

	public boolean isElementPresent(By by) {
		try {
			driver.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}
}
