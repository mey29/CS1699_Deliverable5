/*
 * User Story:
 * 
 * As a user of Nordstrom's I want to be able to sign in so that I can make purchases. 
 * 
 * Scenarios:
 * 
 * 	Given the Nordstrom's sign-in page, when I enter the correct username and password, then I am signed into my account.
 * 	Given the Nordstrom's sign-in page, when I enter the incorrect username and password, then I am not signed into my account and receive an error message.
 * 	Given the Nordstrom's sign-in page, when I enter the incorrect username and correct password, then I am not signed into my account and receive an error message.
 *  Given the Nordstrom's sign-in page, when I enter the correct username and incorrect password, then I am not signed into my account and receive an error message.
 */

import static org.junit.Assert.*;

import java.util.concurrent.TimeUnit;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SignInTest {

	WebDriver driver = new FirefoxDriver();

	// Create new driver with base URL at Nordstrom.com
	public void setUp() throws Exception {
		driver.get("http://shop.nordstrom.com");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		// Travel to sign-in page
		driver.findElement(By.linkText("Sign In")).click();
	}

	// Given the Nordstrom's sign-in page, when I enter the correct username and
	// password, then I am signed into my account.
	@Test
	public void testCorrectInput() {
		try {
			setUp();
		} catch (Exception e) {
			fail();
		}

		// Click on text box for email address and enter correct email
		driver.findElement(By.id("ctl00_mainContentPlaceHolder_signIn_email"))
				.sendKeys("softwaretesting1699@gmail.com");

		// Click on text box for password and enter correct password
		driver.findElement(
				By.id("ctl00_mainContentPlaceHolder_signIn_password"))
				.sendKeys("Testing1699");

		// Click "Sign in"
		driver.findElement(
				By.id("ctl00_mainContentPlaceHolder_signIn_enterButton"))
				.click();

		// Assertion - correctly signed in
		WebElement element = driver.findElement(By.id("shopper-nav"));
		assertTrue(element.getText().contains("Hello"));
		driver.quit();
	}

	 // Given the Nordstrom's sign-in page, when I enter the incorrect username and
	 // password, then I am not signed into my account and receive an error
	 // message.
	@Test
	public void testIncorrectOutput() {
		try {
			setUp();
		} catch (Exception e) {
			fail();
		}

		// Click on text box for email address and enter incorrect email
		driver.findElement(By.id("ctl00_mainContentPlaceHolder_signIn_email"))
				.sendKeys("1699@gmail.com");

		// Click on text box for password and enter incorrect password
		driver.findElement(
				By.id("ctl00_mainContentPlaceHolder_signIn_password"))
				.sendKeys("test");

		// Click "Sign in using our secure server"
		driver.findElement(
				By.id("ctl00_mainContentPlaceHolder_signIn_enterButton"))
				.click();

		// Assertion - NOT signed in
		WebElement element = driver
				.findElement(By
						.id("ctl00_mainContentPlaceHolder_signIn_loginError_mainMessageLabel"));
		assertNotNull(element);
		driver.quit();
	}

	 // Given the Nordstrom's sign-in page, when I enter the incorrect username and
	 // correct password, then I am not signed into my account and receive an
	 // error message.
	@Test
	public void testWrongUsername() {
		try {
			setUp();
		} catch (Exception e) {
			fail();
		}

		// Click on text box for email address and enter incorrect email
		driver.findElement(By.id("ctl00_mainContentPlaceHolder_signIn_email"))
				.sendKeys("1699@gmail.com");

		// Click on text box for password and enter correct password
		driver.findElement(
				By.id("ctl00_mainContentPlaceHolder_signIn_password"))
				.sendKeys("Testing1699");

		// Click "Sign in using our secure server"
		driver.findElement(
				By.id("ctl00_mainContentPlaceHolder_signIn_enterButton"))
				.click();

		// Assertion - NOT signed in
		WebElement element = driver
				.findElement(By
						.id("ctl00_mainContentPlaceHolder_signIn_loginError_mainMessageLabel"));
		assertNotNull(element);
		driver.quit();
	}

	 // Given the Nordstrom's sign-in page, when I enter the correct username and
	 // incorrect password, then I am not signed into my account and receive an
	 // error message.
	@Test
	public void testWrongPassword() {
		try {
			setUp();
		} catch (Exception e) {
			fail();
		}

		// Click on text box for email address and enter correct email
		driver.findElement(By.id("ctl00_mainContentPlaceHolder_signIn_email"))
				.sendKeys("softwaretesting1699@gmail.com");

		// Click on text box for password and enter incorrect password
		driver.findElement(
				By.id("ctl00_mainContentPlaceHolder_signIn_password"))
				.sendKeys("test");

		// Click "Sign in using our secure server"
		driver.findElement(
				By.id("ctl00_mainContentPlaceHolder_signIn_enterButton"))
				.click();

		// Assertion - NOT signed in
		WebElement element = driver
				.findElement(By
						.id("ctl00_mainContentPlaceHolder_signIn_loginError_mainMessageLabel"));
		assertNotNull(element);
		driver.quit();
	}
}