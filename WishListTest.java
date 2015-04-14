/*
 * User Story:
 * 
 * As a user of Nordstrom's I want to manage my wish lists so that I can store future potential purchases. 
 * 
 * Scenarios:
 * 
 * Given a signed in Nordstrom's account Wish List page, when first loaded, then a statement "Hello Software! There are no items in your Wish List" appears.
 * Given a signed in Nordstrom's account Wish List page, when I search #1036625 and select "X-Small" followed by clicking "Add to Wish List," then a pop up will appear that will state "Added to Wish List:".
 * Given a signed in Nordstrom's account Wish List page, when I click "Remove" then the item is no longer found in the wish list.
 * Given a signed in Nordstrom's account Wish List page, when I click "make it public" and enter password, then all items on wish list are publicly displayed.
 */

import static org.junit.Assert.*;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.WebDriver;

public class WishListTest {

	WebDriver driver = new FirefoxDriver();

	public void setUp() throws Exception {
		driver.get("http://shop.nordstrom.com");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		// Click on "Wish List"
		driver.findElement(By.linkText("Wish List")).click();

		// DEPENDENT ON SIGNING IN CORRECTLY
		driver.findElement(By.id("ctl00_mainContentPlaceHolder_signIn_email"))
				.sendKeys("softwaretesting1699@gmail.com");
		driver.findElement(
				By.id("ctl00_mainContentPlaceHolder_signIn_password"))
				.sendKeys("Testing1699");
		driver.findElement(
				By.id("ctl00_mainContentPlaceHolder_signIn_enterButton"))
				.click();
	}
	
	public void setUpAdd() throws Exception {
		driver.get("http://shop.nordstrom.com");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		// Click on search bar and present product code
		driver.findElement(By.id("primary-search-input")).sendKeys("1036625");

		// Click submit button
		driver.findElement(By.id("primary-search-submit")).click();
		
	    // Select an X-Small
		driver.findElement(By.id("size-id-0")).click();	
		
		// Add to Wish List	
		driver.findElement(By.id("add-to-wish-list-button")).click();
		
		// Sign In 
		driver.findElement(By.id("ctl00_mainContentPlaceHolder_signIn_email"))
		.sendKeys("softwaretesting1699@gmail.com");
driver.findElement(
		By.id("ctl00_mainContentPlaceHolder_signIn_password"))
		.sendKeys("Testing1699");
driver.findElement(
		By.id("ctl00_mainContentPlaceHolder_signIn_enterButton"))
		.click();
	}

	// Given a signed in Nordstrom's account Wish List page, when first
	// loaded, a statement "Hello Software! There are no items in your Wish List" appears.
	@Test
	public void testEmptyList() {
		try {
			setUp();
		} catch (Exception e) {
			fail();
		}

		// Assertion 
		WebElement element = driver.findElement(By.id("ctl00_mainContentPlaceHolder_wishListItemList_emptyOwnerWishList"));
		assertNotNull(element);
		driver.quit();
	}

	// Given a signed in Nordstrom's account Wish List page, when I search #1036625
	// and select "X-Small" followed by clicking "Add to Wish List," then a pop up
	// will appear that will state "Added to Wish List:".
	@Test
	public void testInsertItem() {
		try {
		setUpAdd();
	} catch (Exception e) {
		fail();
	}
		// Assertion
		assertNotNull(driver.findElement(By.id("ctl00_mainContentPlaceHolder_wishListItemList_repeater_ctl00_styleNameHyperlink")));
		driver.quit();
	}

	// Given a signed in Nordstrom's account Wish List page, when I click
	// "Remove" then the item is no longer found in the wish list.
	@Test
	public void testRemoveItem() {
		try {
			setUpAdd();
		} catch (Exception e) {
			fail();
		}
		
		// Click on "remove"
		driver.findElement(By.id("ctl00_mainContentPlaceHolder_wishListItemList_repeater_ctl00_removeWishlistItem")).click();

		// Assertion
		WebElement element = driver.findElement(By.id("ctl00_mainContentPlaceHolder_wishListItemList_emptyOwnerWishList"));
		assertNotNull(element);
		driver.quit();
	}

	// Given a signed in Nordstrom's account Wish List page, when I click
	// "make it public" and enter password, then all items on wish list are publicly displayed.
	@Test
	public void testPrivacySetting() {
		try {
			setUp();
		} catch (Exception e) {
			fail();
		}

		// Click on "Make it public"
		driver.findElement(By.id("ctl00_mainContentPlaceHolder_makePublic")).click();
 
		// Enter password
		driver.findElement(By.id("ctl00_EndOfDomContent_wishListAuthenticationModal_signIn_password")).sendKeys("Testing1699");
		driver.findElement(By.id("ctl00_EndOfDomContent_wishListAuthenticationModal_signIn_enterButton")).click();
		
		// Enter last name
		driver.findElement(By.id("ctl00_EndOfDomContent_wishListPrivacySettingsModal_lname")).sendKeys("Testing");
		// City
		driver.findElement(By.id("ctl00_EndOfDomContent_wishListPrivacySettingsModal_lname")).sendKeys("Pittsburgh");		
		// State
		driver.findElement(By.id("ctl00_EndOfDomContent_wishListPrivacySettingsModal_lname")).sendKeys("Pennsylvania");
		// Submit
		driver.findElement(By.id("ctl00_EndOfDomContent_wishListPrivacySettingsModal_saveButton")).click();
		
		// Assertion
		assertTrue(driver.findElement(By.id("ctl00_mainContentPlaceHolder_ResourceLabel19")).getText().matches("Public"));
		driver.quit();
	}
}