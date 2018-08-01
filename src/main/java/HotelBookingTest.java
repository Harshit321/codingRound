import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.Test;

import com.codinground.utility.DriverUtils;
import com.codinground.utility.TBrowser;

public class HotelBookingTest {

	final static Logger logger = Logger.getLogger(HotelBookingTest.class);

	@FindBy(linkText = "Hotels")
	private WebElement hotelLink;

	@FindBy(id = "Tags")
	private WebElement localityTextBox;

	@FindBy(id = "SearchHotelsButton")
	private WebElement searchButton;

	@FindBy(id = "travellersOnhome")
	private WebElement travellerSelection;

	private WebDriver driver;

	@Test
	public void shouldBeAbleToSearchForHotels() {
		try {
			logger.debug("Entering in  shouldBeAbleToSearchForHotels method");
			TBrowser browser = TBrowser.getInstance();

			logger.debug("Opening browser and launching URL");
			driver = browser.launchUrl("https://www.cleartrip.com/");
			PageFactory.initElements(driver, this);
			DriverUtils utils = new DriverUtils(driver);

			utils.waitFor(2000);
			hotelLink.click();

			logger.debug("Entering the locality to search hotels.");
			localityTextBox.sendKeys("Indiranagar, Bangalore");
			utils.waitFor(2000);
			List<WebElement> originOptions = driver.findElement(By.id("ui-id-1")).findElements(By.tagName("li"));
			originOptions.get(1).click();
			logger.debug("Selected the locality to search hotels.");

			logger.debug("Entering the travellerSelection option to search hotels.");
			new Select(travellerSelection).selectByVisibleText("1 room, 2 adults");

			searchButton.click();
			logger.debug("Clicked on search button.");

			logger.debug("Waiting to display search results.");
			utils.waitFor(5000);
			// verify that result appears for the provided journey search
			Assert.assertTrue(utils.isElementPresent(By.className("searchSummary")));
			logger.debug("TestCase-shouldBeAbleToSearchForHotels passed.");

		} catch (InterruptedException exception) {
			logger.error("Intruppted accured ,root cause:: " + exception.getMessage());
		} catch (Exception e) {
			logger.error("Exception accured while booking the flight:root cause" + e.getMessage());
		}
	}

	@AfterSuite
	public void closeBrowser() {
		logger.info("Closing the browser.");
		driver.quit();
	}
}
