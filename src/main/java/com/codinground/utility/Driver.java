package com.codinground.utility;

import com.codingground.exception.DriverIntializationException;
import com.sun.javafx.PlatformUtil;

public class Driver {
	 public static  void setDriverPath() throws DriverIntializationException {
	    try
	    {
		 
		 if (PlatformUtil.isMac()) {
	            System.setProperty("webdriver.chrome.driver", "chromedriver_mac");
	        }
		 else
	        if (PlatformUtil.isWindows()) {
	            System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
	        }
	        else
	        if (PlatformUtil.isLinux()) {
	            System.setProperty("webdriver.chrome.driver", "chromedriver_linux");
	        }
	        else
	        {
	        	throw new  DriverIntializationException("There is no compatible driver for platform");
	        }
	    }
	 
	 catch(DriverIntializationException e)
	 {
		throw e;
		 
	 }
	 }
	 public static  void waitFor(int durationInMilliSeconds) throws InterruptedException{
	        try {
	            Thread.sleep(durationInMilliSeconds);
	        } catch (InterruptedException e) {
	           throw e;  //To change body of catch statement use File | Settings | File Templates.
	        }
	    }
}
