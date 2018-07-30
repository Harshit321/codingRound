import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.sun.javafx.PlatformUtil;

public class SignInTest {
	
    WebDriver driver = new ChromeDriver();
  final static   Logger logger=Logger.getLogger(SignInTest.class);
    @Test
    public void shouldThrowAnErrorIfSignInDetailsAreMissing() {
    	logger.debug("Entering in  shouldThrowAnErrorIfSignInDetailsAreMissing method");
        setDriverPath();
        driver.manage().window().maximize();
        driver.get("https://www.cleartrip.com/");
        logger.info("Entering in  shouldThrowAnErrorIfSignInDetailsAreMissing method");
        waitFor(2000);

        driver.findElement(By.linkText("Your trips")).click();
        driver.findElement(By.id("SignIn")).click();

        waitFor(2000);
        driver.switchTo().frame("modal_window");
        
        driver.findElement(By.id("signInButton")).click();

        String errors1 = driver.findElement(By.id("errors1")).getText();
        Assert.assertTrue(errors1.contains("There were errors in your submission"));
        driver.quit();
    }

    private void waitFor(int durationInMilliSeconds) {
        try {
            Thread.sleep(durationInMilliSeconds);
        } catch (InterruptedException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    private void setDriverPath() {
        if (PlatformUtil.isMac()) {
            System.setProperty("webdriver.chrome.driver", "chromedriver");
        }
        if (PlatformUtil.isWindows()) {
            System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        }
        if (PlatformUtil.isLinux()) {
            System.setProperty("webdriver.chrome.driver", "chromedriver_linux");
        }
    }


}
