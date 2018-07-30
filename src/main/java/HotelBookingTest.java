import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.codingground.exception.DriverIntializationException;
import com.codinground.utility.Driver;

public class HotelBookingTest {

	WebDriver driver = new ChromeDriver();
	final static Logger logger = Logger.getLogger(HotelBookingTest.class);
	
	@FindBy(linkText = "Hotels")
	private WebElement hotelLink;

	@FindBy(id = "Tags")
	private WebElement localityTextBox;

	@FindBy(id = "SearchHotelsButton")
	private WebElement searchButton;

	@FindBy(id = "travellersOnhome")
	private WebElement travellerSelection;

	@Test
	public void shouldBeAbleToSearchForHotels() {
		try {
			Driver.setDriverPath();
			driver.manage().window().maximize();
			PageFactory.initElements(driver, this);
			driver.get("https://www.cleartrip.com/");
			hotelLink.click();

			localityTextBox.sendKeys("Indiranagar, Bangalore");
			Driver.waitFor(2000);
			List<WebElement> originOptions = driver.findElement(By.id("ui-id-1")).findElements(By.tagName("li"));
			originOptions.get(1).click();
			new Select(travellerSelection).selectByVisibleText("1 room, 2 adults");
			searchButton.click();

			Driver.waitFor(5000);
			// verify that result appears for the provided journey search
			Assert.assertTrue(isElementPresent(By.className("searchSummary")));

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
}
