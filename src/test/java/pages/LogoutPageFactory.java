//we are going to use Page Factory to create this class
package pages;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LogoutPageFactory {

	WebDriver driver; // declare a class variable driver to use in all methods of this class
	WebDriverWait wait; // only required if using explicit waits for the elements

	public LogoutPageFactory(WebDriver driver) // the Webdriver object will be passed from our test class
	{
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // only required if using Explicit Wait

		// initialize the Page Factory class in the constructor:
		PageFactory.initElements(driver, this);
		// This method supplies the webdrivers to all the locators where we are using
		// @FindBy

	}

	// locators (For Page factory, we will use the annotation @FindBy)
	// @FindBy - finds the element with the given locator and stores it in the
	// WebElement declared below.
	// No need for WebElement = driver.findElement(By.xpath());

	@FindBy(xpath = "//p[@class='oxd-userdropdown-name']")
	WebElement logoutName;

	@FindBy(xpath = "//a[normalize-space()='Logout']")
	WebElement logoutBtn;

	// methods:
	// we already have got the WebElements, now we perform the actions on them:

	public void clickLogoutBtn() {
		wait.until(ExpectedConditions.elementToBeClickable(logoutName)).click();
		wait.until(ExpectedConditions.visibilityOf(logoutBtn)); // Ensure logout button appears

	}

	public void clickLogout() {
		wait.until(ExpectedConditions.elementToBeClickable(logoutBtn)).click();

	}

}
