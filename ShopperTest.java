/*
 * User Story:
 *
 * As a shopper of Nordstrom's I want to manage my cart so that I choose the correct purchases.
 *
 * Scenarios:
 *
 * Given a products page on Nordstrom's, when I click "Add to Shopping Bag", then the item shows up in my Shopping Bag.
 * Given my Shopping Bag on Nordstrom's, when I click "Delete" on an item, then that item is no longer in my Shopping Bag.
 * Given my Shopping Bag on Nordstrom's, when I change the size on an item, then my Shopping Bag is updated with the revised size.
 * Given the Nordstrom's home page, when I click Shopping Bag, then the contents of Shopping Bag are displayed.
 * Given my Shopping Bag on Nordstrom's, when I click "Save for later", then my Shopping Bag is updated with the product removed and saved.
 */
import java.util.concurrent.TimeUnit;
import org.junit.Test;
import static org.junit.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;

public class ShopperTest {

	WebDriver driver = new FirefoxDriver();

	public void setUp() throws Exception {
		driver.get("http://shop.nordstrom.com");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	// Given a products page on Nordstrom's, when I click "Add to Shopping Bag",
	// then the
	// item shows up in my Shopping Bag.
	@Test
	public void testAddItem() {
		try {
			setUp();
		} catch (Exception e) {
			fail();
		}

		// Click on search bar and present product code
		driver.findElement(By.id("primary-search-input")).sendKeys("1036625");

		// Click submit button
		driver.findElement(By.id("primary-search-submit")).click();

		// Select an X-Small
		driver.findElement(By.id("size-id-0")).click();

		// Click "Add to Shopping Bag"
		driver.findElement(By.id("add-to-shopping-bag-button")).click();

		// Click on shopping bag
		driver.findElement(By.id("shopping-bag-link")).click();

		// assertTextPresent
		// "Vince Camuto 'Broken Photo' Floral Print Peasant Blouse"
		WebElement element = driver
				.findElement(By
						.id("ctl00_mainContentPlaceHolder_shoppingBagList_orderItemRepeater_ctl00_styleNameHyperlink"));
		assertTrue(element.getText().contains(
				"Vince Camuto 'Broken Photo' Floral Print Peasant Blouse"));
		driver.quit();
	}

	// Given my Shopping Bag on Nordstrom's, when I click "Delete" on an item,
	// then that item
	// is no longer in my Shopping Bag.
	@Test
	public void testRemoveItem() {
		try {
			setUp();
		} catch (Exception e) {
			fail();
		}
		// Click on search bar and present product code
		driver.findElement(By.id("primary-search-input")).sendKeys("1036625");

		// Click submit button
		driver.findElement(By.id("primary-search-submit")).click();

		// Select an X-Small
		driver.findElement(By.id("size-id-0")).click();

		// Click "Add to Shopping Bag"
		driver.findElement(By.id("add-to-shopping-bag-button")).click();

		// Click on shopping bag
		driver.findElement(By.id("shopping-bag-link")).click();

		// Click "Remove" to remove item
		driver.findElement(
				By.id("ctl00_mainContentPlaceHolder_shoppingBagList_orderItemRepeater_ctl00_removeItemImageButton"))
				.click();

		// Assertion that element has been removed
		assertNotNull(driver
				.findElement(By
						.id("ctl00_mainContentPlaceHolder_shoppingBagHeader_bagitemToShoppingBag")));
		driver.quit();
	}

	// Given my Shopping Bag on Nordstrom's, when I change the size on an item,
	// then my
	// Shopping Bag is updated with the revised size.
	@Test
	public void testChangeItemSize() {
		try {
			setUp();
		} catch (Exception e) {
			fail();
		}

		// Click on search bar and present product code
		driver.findElement(By.id("primary-search-input")).sendKeys("1036625");

		// Click submit button
		driver.findElement(By.id("primary-search-submit")).click();

		// Select an X-Small
		driver.findElement(By.id("size-id-0")).click();

		// Click "Add to Shopping Bag"
		driver.findElement(By.id("add-to-shopping-bag-button")).click();

		// Click on shopping bag
		driver.findElement(By.id("shopping-bag-link")).click();

		// Click on "Edit"
		driver.findElement(
				By.id("ctl00_mainContentPlaceHolder_shoppingBagList_orderItemRepeater_ctl00_editItemImageButton"))
				.click();

		// Enter "Small" as new size
		driver.findElement(By.id("dropDown1_1")).sendKeys("Small");

		// Click "Save"
		driver.findElement(
				By.id("ctl00_EndOfDomContent_itemEditModal_editSaveButton"))
				.click();

		// assertTextPresent "Small"
		WebElement element = driver.findElement(By.className("value"));
		assertTrue(element.getText().contains("Small"));
		driver.quit();
	}

	// Given the Nordstrom's home page, when I click Shopping Bag, then the contents
	// of Shopping Bag
	// are displayed.
	@Test
	public void testItemInCart() {
		try {
			setUp();
		} catch (Exception e) {
			fail();
		}
		// Click on search bar and present product code
		driver.findElement(By.id("primary-search-input")).sendKeys("1036625");

		// Click submit button
		driver.findElement(By.id("primary-search-submit")).click();

		// Select an X-Small
		driver.findElement(By.id("size-id-0")).click();

		// Click "Add to Shopping Bag"
		driver.findElement(By.id("add-to-shopping-bag-button")).click();

		// Go to Nordstrom.com
		driver.findElement(By.id("nordlogo-primary")).click();

		// Click on Shopping Bag
		driver.findElement(By.id("shopping-bag-link")).click();

		// assertTextPresent
		// "Vince Camuto 'Broken Photo' Floral Print Peasant Blouse"
		WebElement element = driver
				.findElement(By
						.id("ctl00_mainContentPlaceHolder_shoppingBagList_orderItemRepeater_ctl00_styleNameHyperlink"));
		assertTrue(element.getText().contains(
				"Vince Camuto 'Broken Photo' Floral Print Peasant Blouse"));
		driver.quit();
	}

	// Given my Shopping Bag on Nordstrom's, when I click "Save for later", then
	// my
	// Shopping Bag is updated with the product removed and saved.
	@Test
	public void testSaveForLater() {
		try {
			setUp();
		} catch (Exception e) {
			fail();
		}
		// Click on search bar and present product code
		driver.findElement(By.id("primary-search-input")).sendKeys("1036625");

		// Click submit button
		driver.findElement(By.id("primary-search-submit")).click();

		// Select an X-Small
		driver.findElement(By.id("size-id-0")).click();

		// Click "Add to Shopping Bag"
		driver.findElement(By.id("add-to-shopping-bag-button")).click();

		// Click on Shopping Bag
		driver.findElement(By.id("shopping-bag-link")).click();

		// Click on "Save for later"
		driver.findElement(
				By.id("ctl00_mainContentPlaceHolder_shoppingBagList_orderItemRepeater_ctl00_saveforlaterItemImageButton"))
				.click();

		// Assertion that item was saved
		assertNotNull(driver
				.findElement(By
						.id("ctl00_mainContentPlaceHolder_shoppingBagList_saveForLaterItemAlert_ResourceLabel1")));
		driver.quit();
	}
}