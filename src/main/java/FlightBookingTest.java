import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.codingground.exception.DriverIntializationException;
import com.codinground.utility.Driver;

public class FlightBookingTest {

	WebDriver driver = new ChromeDriver();
	private String today;
	final static Logger logger = Logger.getLogger(FlightBookingTest.class);

	@Test
	public void testThatResultsAppearForAOneWayJourney() {

		try {
			logger.debug("Intializing the driver..");
			Driver.setDriverPath();
			logger.debug("Intialized the driver successfully..");
			driver.manage().window().maximize();

			logger.debug("Opening the ClearTrip site on Chrome..");
			driver.get("https://www.cleartrip.com/");
			logger.debug("Succesfully Opened the ClearTrip site on Chrome..");

			Driver.waitFor(2000);
			driver.findElement(By.id("OneWay")).click();
			logger.info("Clicked on One Way radio button");
			Driver.waitFor(2000);
			driver.findElement(By.id("FromTag")).clear();
			logger.info("Entering the value of Source station");
			driver.findElement(By.id("FromTag")).sendKeys("Bangalore");

			// wait for the auto complete options to appear for the origin

			Driver.waitFor(2000);
			List<WebElement> originOptions = driver.findElement(By.id("ui-id-1")).findElements(By.tagName("li"));
			originOptions.get(0).click();
			logger.info("Selected the value of Source station from DropDown");

			driver.findElement(By.id("ToTag")).clear();
			logger.info("Entering the value of Destination station");
			driver.findElement(By.id("ToTag")).sendKeys("Delhi");

			// wait for the auto complete options to appear for the destination

			Driver.waitFor(2000);
			// select the first item from the destination auto complete list
			List<WebElement> destinationOptions = driver.findElement(By.id("ui-id-2")).findElements(By.tagName("li"));
			destinationOptions.get(0).click();
			logger.info("Selected the value of Destination station from DropDown");
			// driver.findElement(By.xpath("//*[@id='ui-datepicker-div']/div[1]/table/tbody/tr[3]/td[7]/a")).click();

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

			Driver.waitFor(4000);

			// all fields filled in. Now click on search
			driver.findElement(By.id("SearchBtn")).click();
			logger.debug("Clicked on Search button");
			Driver.waitFor(5000);
			// verify that result appears for the provided journey search
			Assert.assertTrue(isElementPresent(By.className("searchSummary")));
			logger.debug("Search results are successfully visible");
			// close the browser
			logger.debug("Closing the browser.");
			driver.quit();

		} catch (DriverIntializationException ex) {
			logger.error("Exception accured ,root cause:: " + ex.getMessage());
		} catch (InterruptedException exception) {
			logger.error("Intruppted accured ,root cause:: " + exception.getMessage());
		} catch (Exception e) {
			logger.error("Exception accured while booking the flight:root cause" + e.getMessage());
		}
	}

	private boolean isElementPresent(By by) {
		try {
			driver.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;
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
