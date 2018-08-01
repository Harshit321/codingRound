import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.codinground.utility.DriverUtils;
import com.codinground.utility.TBrowser;

public class SignInTest {

	final static Logger logger = Logger.getLogger(SignInTest.class);

	@FindBy(linkText = "Your trips")
	private WebElement yourTrips;

	@FindBy(id = "SignIn")
	private WebElement signIn;

	@FindBy(id = "signInButton")
	private WebElement signInBtn;

	@FindBy(id = "errors1")
	private WebElement error;

	@Test
	public void shouldThrowAnErrorIfSignInDetailsAreMissing() {
		try {
			TBrowser browser = TBrowser.getInstance();
			WebDriver driver = browser.launchUrl("https://www.cleartrip.com/");
			PageFactory.initElements(driver, this);
			logger.debug("Entering in  shouldThrowAnErrorIfSignInDetailsAreMissing method");
			DriverUtils utils = new DriverUtils(driver);

			utils.waitFor(2000);
			yourTrips.click();
			logger.debug("Clicked on YourTrips button.");
			signIn.click();
			logger.debug("Clicked on SignIn button.");
			utils.waitFor(2000);
			driver.switchTo().frame("modal_window");
			logger.debug("Clicking on SignIn button without filling any details.");
			signInBtn.click();
			String errors1 = error.getText();
			Assert.assertTrue(errors1.contains("There were errors in your submission"));
			logger.debug("TestCase-shouldThrowAnErrorIfSignInDetailsAreMissing passed.");

		} catch (InterruptedException exception) {
			logger.error("Intruppted accured ,root cause:: " + exception.getMessage());
		} catch (Exception e) {
			logger.error("Exception accured while booking the flight:root cause" + e.getMessage());
		}

	}
}
