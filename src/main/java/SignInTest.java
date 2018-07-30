import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.codingground.exception.DriverIntializationException;
import com.codinground.utility.Driver;

public class SignInTest {

	WebDriver driver = new ChromeDriver();
	final static Logger logger = Logger.getLogger(SignInTest.class);

	@Test
	public void shouldThrowAnErrorIfSignInDetailsAreMissing() {
		try {
			logger.debug("Entering in  shouldThrowAnErrorIfSignInDetailsAreMissing method");
			Driver.setDriverPath();
			driver.manage().window().maximize();
			driver.get("https://www.cleartrip.com/");
			logger.info("Entering in  shouldThrowAnErrorIfSignInDetailsAreMissing method");
			Driver.waitFor(2000);

			driver.findElement(By.linkText("Your trips")).click();
			driver.findElement(By.id("SignIn")).click();

			Driver.waitFor(2000);
			driver.switchTo().frame("modal_window");

			driver.findElement(By.id("signInButton")).click();

			String errors1 = driver.findElement(By.id("errors1")).getText();
			Assert.assertTrue(errors1.contains("There were errors in your submission"));
			driver.quit();
		} catch (DriverIntializationException ex) {
			logger.error("Exception accured ,root cause:: " + ex.getMessage());
		} catch (InterruptedException exception) {
			logger.error("Intruppted accured ,root cause:: " + exception.getMessage());
		} catch (Exception e) {
			logger.error("Exception accured while booking the flight:root cause" + e.getMessage());
		}

	}

}
