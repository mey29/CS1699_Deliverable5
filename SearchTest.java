/*
 * User Story:
 * 
 * As a searcher of Nordstrom's I want to find products so that I can purchase them.
 * 
 * Scenarios: 
 *
 * Given the Nordstrom's home page, when I enter a product's code number in the search bar and press enter, then the product will appear. 
 * Given the Nordstrom's home page, when I enter a product's name in the search bar and press enter, then the product will appear.
 * Given the Nordstrom's home page, when I click through the correct departments, then the correct title will be displayed.
 * Given the Google home page, when I click the search bar and enter a specific product and the word "Nordstrom" and press enter, then a link to the product's page on Nordstrom's shows up in the results.
 */

import java.util.concurrent.TimeUnit;
import org.junit.Test;
import static org.junit.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;

public class SearchTest {
	
	WebDriver driver = new FirefoxDriver();

	public void setUp() throws Exception {
		driver.get("http://shop.nordstrom.com");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	// Given the Nordstrom's home page, when I enter a product's code number in
	// the search bar and press enter, then the product will appear.
	@Test
	public void testSearchBarCode() {
		try {
			setUp();
		} catch (Exception e) {
			fail();
		}

		// Click on search bar and present product code
		driver.findElement(By.id("primary-search-input")).sendKeys("1036625");

		// Click submit button
		driver.findElement(By.id("primary-search-submit")).click();

		// assertTextPresent "Broken Photo' Floral Print Peasant Blouse"
		WebElement element = driver.findElement(By.id("product-title"));
		assertEquals(element.getText(),
				"'Broken Photo' Floral Print Peasant Blouse");
		driver.quit();
	}
	

	// Given the Nordstrom's home page, when I enter a product's name in
	// the search bar and press enter, then the product will appear.
	@Test
	public void testSearchBarName() {
		try {
			setUp();
		} catch (Exception e) {
			fail();
		}

		// Click on search bar and present product code
		driver.findElement(By.id("primary-search-input")).sendKeys("'Broken Photo' Floral Print Peasant Blouse");

		// Click submit button
		driver.findElement(By.id("primary-search-submit")).click();

		// Assertion that items were found
		assertNotNull(driver.findElement(By.id("fashion_3942742")));
		driver.quit();
	}

	// Given the Nordstrom's home page, when I click through the correct
	// departments, then the correct title will be displayed.
	@Test
	public void testDepartment() {
		try {
			setUp();
		} catch (Exception e) {
			fail();
		}
		
		// Click on the shoe department
		driver.findElement(By.id("shoes-tab")).click();

		// Click "Boots"
		driver.findElement(By.linkText("Boots")).click();

		// Click on "Riding"
		driver.findElement(By.linkText("Riding")).click();

		// assertTextPresent "Women's Riding Boots"
		WebElement element = driver.findElement(By.id("categoryHeader"));
		assertEquals(element.getText(), "Women's Riding Boots");
		driver.quit();
	}

	// Given the Google home page, when I click the search bar and enter a
	// specific product and the word "Nordstrom" and press enter, then a link to
	// the product's page on Nordstrom's shows up in the results.
	@Test
	public void testSearchGoogle() {
		// Start at Google Homepage
		try {
			driver.get("http://google.com");
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		} catch (Exception e) {
			fail();
		}

		// Click on Google search bar and enter "Nordstrom and "Broken Photo'
		// Floral Print Peasant Blouse"
		driver.findElement(By.id("lst-ib")).sendKeys(
				"Nordstrom Broken Photo' Floral Print Peasant Blouse");

		// Click submit button
		driver.findElement(By.name("btnG")).click();

		// Click on first result
		driver.findElement(By.xpath("//div/h3/a")).click();

		// assertTextPresent "Broken Photo' Floral Print Peasant Blouse"
		WebElement element = driver.findElement(By.id("product-title"));
		assertEquals(element.getText(),
				"'Broken Photo' Floral Print Peasant Blouse");
		driver.quit();
	}
}