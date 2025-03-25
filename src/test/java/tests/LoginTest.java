package tests;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pages.LoginPageFactory;
import pages.LogoutPageFactory;

public class LoginTest {

	// declare WebDriver as a class variable to be accessed in all classes:
	WebDriver driver;
	WebDriverWait wait;
	// LoginPage loginPageObj; //declare object of login page.
	// We need to use this object to call functions from the login page class

	LoginPageFactory factoryObj;
	LogoutPageFactory logoutObj;

	@BeforeClass
	public void setUp() {
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		// go to target page:
		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");

		// Initialize Page Objects
		// loginPageObj = new LoginPage(driver);

		factoryObj = new LoginPageFactory(driver);
		logoutObj = new LogoutPageFactory(driver);
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}

	// this test will get the data from the data provider defined below
	@Test(dataProvider = "LoginData")
	public void testLogin(String username, String password) throws InterruptedException {
		System.out.println("***** Inside Login test for: " + username + " *****");

		// call the methods of the Login Page class using the loginPage object
		factoryObj.setUserName(username);
		factoryObj.setPassword(password);
		factoryObj.clickLoginBtn();
		Thread.sleep(3000);

		// Add an assert
		Assert.assertEquals(driver.getCurrentUrl(), "https://opensource-demo.orangehrmlive.com/web/index.php/dashboard/index");
		Thread.sleep(3000);

	}

	@Test(dependsOnMethods = "testLogin")
	public void testLogout() throws InterruptedException {
		logoutObj.clickLogoutBtn();
		logoutObj.clickLogout();

		Thread.sleep(2000);
		// Assert the URL after logout
		String expectedUrl = "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login";
		String actualUrl = driver.getCurrentUrl();
		
		// ✅ Wait for the login page to reload
        wait.until(ExpectedConditions.urlToBe("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login"));

		Assert.assertEquals(actualUrl, expectedUrl, "Logout failed: URL did not match expected login page");

		System.out.println("Logout successful and URL verified!");
		Thread.sleep(2000);
	}

	// Add data using DataProvider
	@DataProvider(name = "LoginData")
	public Object[][] getLoginData() {
		return new Object[][] { { "Admin", "admin123" },{ "Admin123", "admin123" }};
	}

	@AfterClass
	public void tearDown() {
		if (driver != null)
			driver.quit();
		System.out.println("******Closing browser");

	}

}
