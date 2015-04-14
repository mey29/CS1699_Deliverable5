/*
 * User Story:
 *
 * As a user of Nordstrom's I want to manage my account so that I can have up-to-date information and I am aware of account balances.
 *
 * Scenarios:
 * Given a signed in Nordstrom's account page, when the page loads, then a welcomed display is visible. 
 * Given a signed in Nordstrom's account page, when I click "Addresses & Phone Numbers," then my new address can be added.
 * Given a signed in Nordstrom's account page, when I click "Order History," then I see a list of order archives.
 * Given a signed in Nordstrom's account page, when I click "Communication Preferences," and "Sign up to receive e-mail Updates," then a new e-mail may be added into a pop-up window.
 * Given a signed in Nordstrom's account page, when I click "Payment Methods," and "Add New Payment Method," and enter my password, then I can add a new payment method.
 * Given a signed in Nordstrom's account page, when I click "Sign-In Information," and "Edit,"  then I can edit my name.
 * Given a signed in Nordstrom's account page, when I click "Rewards Information," then I am able to apply for one.
 */
import static org.junit.Assert.*;
import org.junit.Test;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ManageAccountTest {
	// Create new WebDriver
	WebDriver driver = new FirefoxDriver();

	public void setUp() throws Exception {
		// Base url at Nordstrom.com
		driver.get("http://shop.nordstrom.com");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		// Travel to sign in page

		// Travel to account page
		driver.findElement(By.linkText("Your Account")).click();

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

	// Given a signed in Nordstrom's account page, when the page loads, then a
	// welcomed display is visible.
	@Test
	public void testWelcomePage() {
		try {
			setUp();
		} catch (Exception e) {
			fail();
		}

		// Assertion
		assertTrue(driver
				.findElement(
						By.id("ctl00_mainContentPlaceHolder_SignInNordUserControl_editSignInEmailDiv"))
				.getText().contains("Welcome"));
		driver.quit();
	}

	// Given a signed in Nordstrom's account page, when I click
	// "Addresses & Phone Numbers," then my new address can be added.
	@Test
	public void testAddAddress() {
		try {
			setUp();
		} catch (Exception e) {
			fail();
		}
		// Click on "Addresses & Phone Numbers"
		driver.findElement(
				By.id("ctl00_mainContentPlaceHolder_leftMenu_LinkAddress"))
				.click();
		// Click on "Add New Address"
		driver.findElement(
				By.id("ctl00_mainContentPlaceHolder_AddressInfoNordUserControl_addNewAddressLink1"))
				.click();

		// Add a new address
		driver.findElement(
				By.id("ctl00_EndOfDomContent_addEditAddress_addEditAddressForm_firstName"))
				.sendKeys("Master");
		driver.findElement(
				By.id("ctl00_EndOfDomContent_addEditAddress_addEditAddressForm_lastName"))
				.sendKeys("Tester");
		driver.findElement(
				By.id("ctl00_EndOfDomContent_addEditAddress_addEditAddressForm_address1"))
				.sendKeys("4200 Fifth Avenue");
		driver.findElement(
				By.id("ctl00_EndOfDomContent_addEditAddress_addEditAddressForm_city"))
				.sendKeys("Pittsburgh");
		driver.findElement(
				By.id("ctl00_EndOfDomContent_addEditAddress_addEditAddressForm_stateProvince"))
				.sendKeys("Pennsylvania");
		driver.findElement(
				By.id("ctl00_EndOfDomContent_addEditAddress_addEditAddressForm_zipCode"))
				.sendKeys("15260");
		driver.findElement(
				By.id("ctl00_EndOfDomContent_addEditAddress_addEditAddressForm_phoneNumber"))
				.sendKeys("4126244141");
		driver.findElement(
				By.id("ctl00_EndOfDomContent_addEditAddress_saveAddressButton"))
				.click();

		// Assertion
		WebElement element = driver
				.findElement(By
						.id("ctl00_mainContentPlaceHolder_AddressInfoNordUserControl_addressDisplayList_ctl01_shippingAddress_addressPanel"));
		assertNotNull(element);
		driver.quit();
	}

	// Given a signed in Nordstrom's account page, when I click
	// "Order History," then I see a list of order archives.
	@Test
	public void testViewOrders() {
		try {
			setUp();
		} catch (Exception e) {
			fail();
		}
		// Click on "Order History"
		driver.findElement(
				By.id("ctl00_mainContentPlaceHolder_leftMenu_LinkOrderHistory"))
				.click();

		// Assertion
		assertTrue(driver
				.findElement(
						By.id("ctl00_mainContentPlaceHolder_OrderHistoryControlNordUserControl_ResourceLabel4"))
				.getText()
				.matches(
						"There is no order history for this registered account."));
		driver.quit();
	}

	// Given a signed in Nordstrom's account page, when I click
	// "Communication Preferences," and "Sign up to receive e-mail Updates,"
	// then a new
	// e-mail may be added into a pop-up window.
	@Test
	public void testCommunicationPreferences() {
		try {
			setUp();
		} catch (Exception e) {
			fail();
		}
		// Click on "Communication Preferences"
		driver.findElement(
				By.id("ctl00_mainContentPlaceHolder_leftMenu_LinkComm_CommPref"))
				.click();

		// Click on "Sign up to receive e-mail Updates"
		driver.findElement(
				By.id("ctl00_mainContentPlaceHolder_AccountProfileCurrentSettingsNordUserControl_manageEmailSettingsLink"))
				.click();

		// Click on "Add an e-mail Address"
		driver.findElement(
				By.id("ctl00_mainContentPlaceHolder_AccountProfileEmailNordUserControl_AddEmailLinkButton"))
				.click();

		// Assertion that an email may be added
		assertNotNull(driver.findElement(By
				.id("ctl00_EndOfDomContent_AccountProfileEmailAddModal_ctl00")));
		driver.quit();
	}

	// Given a signed in Nordstrom's account page, when I click
	// "Payment Methods," and "Add New Payment Method," and enter my password,
	// then I can add a new payment method.
	@Test
	public void testAddPayment() {
		try {
			setUp();
		} catch (Exception e) {
			fail();
		}

		// Click on "Payment Methods"
		driver.findElement(
				By.id("ctl00_mainContentPlaceHolder_leftMenu_LinkPaymentMethods"))
				.click();

		// Click on "Add New Payment Method"
		driver.findElement(
				By.id("ctl00_mainContentPlaceHolder_PaymentMethodsNordUserControl_addNewCreditCardLink1"))
				.click();

		// Assertion that a payment method may be added
		assertNotNull(driver.findElement(By
				.id("ctl00_EndOfDomContent_addEditCreditCard_EditCardPanel")));
		driver.quit();
	}

	// Given a signed in Nordstrom's account page, when I click
	// "Sign-In Information," and "Edit," then I can edit my name.
	@Test
	public void testSignInInformation() {
		try {
			setUp();
		} catch (Exception e) {
			fail();
		}

		// Click on "Sign-In Information"
		driver.findElement(
				By.id("ctl00_mainContentPlaceHolder_leftMenu_LinkSignIn"))
				.click();

		// Click on "Edit"
		driver.findElement(
				By.id("ctl00_mainContentPlaceHolder_SignInNordUserControl_editSignInName"))
				.click();

		// Enter "Tester"
		driver.findElement(
				By.id("ctl00_EndOfDomContent_editFirstNameModal_firstNameTextBox"))
				.sendKeys("Tester");

		// Click "Save"
		driver.findElement(
				By.id("ctl00_EndOfDomContent_editFirstNameModal_nameEditSaveButton"))
				.click();

		// Assertion
		assertTrue(driver
				.findElement(
						By.id("ctl00_mainContentPlaceHolder_SignInNordUserControl_spnFirstName"))
				.getText().contains("TesterSoftware"));
		driver.quit();
	}

	// Given a signed in Nordstrom's account page, when I click
	// "Rewards Information," then I am able to apply for them.
	@Test
	public void testRewardsInformation() {
		try {
			setUp();
		} catch (Exception e) {
			fail();
		}

		// Click on "Rewards Information"
		driver.findElement(
				By.id("ctl00_mainContentPlaceHolder_leftMenu_LinkRewardsInformation"))
				.click();

		// Click on "Learn More About Nordstrom Rewards"
		driver.findElement(By.linkText("Learn More About Nordstrom Rewards"))
				.click();

		// Assert one can apply
		assertNotNull(driver.findElement(By.linkText("Apply Now")));
		driver.quit();
	}
}