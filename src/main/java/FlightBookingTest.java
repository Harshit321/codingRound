import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.codingground.exception.DriverIntializationException;
import com.codinground.utility.DriverUtils;
import com.codinground.utility.TBrowser;

public class FlightBookingTest {

	private String today;

	final static Logger logger = Logger.getLogger(FlightBookingTest.class);

	@FindBy(id = "OneWay")
	private WebElement oneWay;

	@FindBy(id = "FromTag")
	private WebElement fromTag;

	@FindBy(id = "ToTag")
	private WebElement toTag;

	@FindBy(id = "SearchBtn")
	private WebElement searchBtn;

	@Test
	public void testThatResultsAppearForAOneWayJourney() {

		try {

			logger.debug("Entering in  shouldThrowAnErrorIfSignInDetailsAreMissing method");
			TBrowser browser = TBrowser.getInstance();
			logger.debug("Opening browser and launching URL");
			WebDriver driver = browser.launchUrl("https://www.cleartrip.com/");
			PageFactory.initElements(driver, this);
			DriverUtils utils = new DriverUtils(driver);

			utils.waitFor(2000);

			oneWay.click();
			logger.info("Clicked on One Way radio button");

			utils.waitFor(2000);

			logger.info("Entering the value of Source station");
			fromTag.clear();
			fromTag.sendKeys("Bangalore");

			// wait for the auto complete options to appear for the origin

			utils.waitFor(2000);
			List<WebElement> originOptions = driver.findElement(By.id("ui-id-1")).findElements(By.tagName("li"));
			originOptions.get(0).click();
			logger.info("Selected the value of Source station from DropDown");

			logger.info("Entering the value of Destination station");
			toTag.clear();
			toTag.sendKeys("Delhi");

			// wait for the auto complete options to appear for the destination
			utils.waitFor(2000);

			// select the first item from the destination auto complete list
			List<WebElement> destinationOptions = driver.findElement(By.id("ui-id-2")).findElements(By.tagName("li"));
			destinationOptions.get(0).click();
			logger.info("Selected the value of Destination station from DropDown");

			today = getCurrentDay();
			logger.info("Selecting today's date in date widget..");
			WebElement dateWidgetFrom = driver.findElement(By.xpath("//*[@id='ui-datepicker-div']/div[1]/table/tbody"));
			List<WebElement> columns = dateWidgetFrom.findElements(By.tagName("td"));

			for (WebElement cell : columns) {

				// Select Today's Date
				if (cell.getText().equals(today)) {
					cell.click();
					break;
				}
			}

			utils.waitFor(4000);

			// all fields filled in. Now click on search
			searchBtn.click();
			logger.debug("Clicked on Search button");

			utils.waitFor(5000);
			// verify that result appears for the provided journey search
			Assert.assertTrue(utils.isElementPresent(By.className("searchSummary")));
			logger.debug("Search results are successfully visible");
			// close the browser

			logger.debug("Closing the browser.");
			// driver.quit();

		} catch (DriverIntializationException ex) {
			logger.error("Exception accured ,root cause:: " + ex.getMessage());
		} catch (InterruptedException exception) {
			logger.error("Intruppted accured ,root cause:: " + exception.getMessage());
		} catch (Exception e) {
			logger.error("Exception accured while booking the flight:root cause" + e.getMessage());
		}
	}

	// method to get current Date
	private String getCurrentDay() {
		// Create a Calendar Object
		Calendar calendar = Calendar.getInstance(TimeZone.getDefault());

		// Get Current Day as a number
		int todayInt = calendar.get(Calendar.DAY_OF_MONTH);
		System.out.println("Today Int: " + todayInt + "\n");

		// Integer to String Conversion
		String todayStr = Integer.toString(todayInt);
		System.out.println("Today Str: " + todayStr + "\n");

		return todayStr;
	}

}
