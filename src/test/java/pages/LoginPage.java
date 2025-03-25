package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {

	WebDriver driver; // declare a class variable driver to use in all methods of this class
	WebDriverWait wait; //only required if using explicit waits for the elements

	// constructor to initiate the webdriver
	// Will creat object of this page class in our test class.Then this constructor
	// will get called.
	public LoginPage(WebDriver driver) // the Webdriver object will be passed from our test class
	{
		this.driver = driver; // the driver variable of this class is getting initialized by the Webdriver object
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // only required if using Explicit Wait
	}

	// locators (here just give the locators with By, no need to give
	// driver.findElement
	By txt_userNameloc = By.xpath("//input[@placeholder='Username']");
	By txt_passwordloc = By.xpath("//input[@placeholder='Password']");
	By btn_loginBtnloc = By.xpath("//button[normalize-space()='Login']");

	// action methods (declare all as public)
	// send keys to user name. Call this method from the test class and pass a
	// string for user name
	public void setUserName(String Username) {
		WebElement userName = driver.findElement(txt_userNameloc);
		userName.sendKeys(Username);
	}

	public void setPassword(String Password) {
		WebElement password = driver.findElement(txt_passwordloc);
		password.sendKeys(Password);

	}

	public void clickLoginBtn() {
		//WebElement loginBtn = driver.findElement(btn_loginBtnloc);
		//We can add an explicit wait for this button to get clickable 
		WebElement loginBtn = wait.until(ExpectedConditions.elementToBeClickable(btn_loginBtnloc));
		loginBtn.click();
	}

}
