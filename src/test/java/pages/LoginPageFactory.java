//we are going to use Page Factory to create this class
package pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPageFactory {

	WebDriver driver; // declare a class variable driver to use in all methods of this class
	WebDriverWait wait; //only required if using explicit waits for the elements

	public LoginPageFactory(WebDriver driver) // the Webdriver object will be passed from our test class
	{
		this.driver = driver; 
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // only required if using Explicit Wait
		
		//initialize the Page Factory class in the constructor:
		PageFactory.initElements(driver, this);
		//This method supplies the webdrivers to all the locators where we are using @FindBy
		
	}

	// locators (For Page factory, we will use the annotation @FindBy)
	//@FindBy - finds the element with the given locator and stores it in the WebElement declared below.
	//No need for WebElement = driver.findElement(By.xpath());
	@FindBy(xpath = "//input[@placeholder='Username']")
	WebElement txtUserName;
	
	@FindBy(xpath = "//input[@placeholder='Password']")
	WebElement txtPassword;
	
	@FindBy(xpath = "//button[normalize-space()='Login']")
	WebElement loginBtn;
	
	//assume we want all the links on this page. Here we will not use driver.findElements(By.tagName);
	//stores in a List of Webelement
	@FindBy(tagName="a")
	List<WebElement> list;
	
	//methods:
	//we already have got the WebElements, now we perform the actions on them:
	
	public void setUserName(String Username) {
		
	
		 wait.until(ExpectedConditions.visibilityOf(txtUserName));
	        txtUserName.sendKeys(Username);
		
	}
	
	public void setPassword(String Password) {
		wait.until(ExpectedConditions.visibilityOf(txtPassword));
        txtPassword.sendKeys(Password);
	}
	
	public void clickLoginBtn() {
		wait.until(ExpectedConditions.elementToBeClickable(loginBtn));
        loginBtn.click();
	}
	
	public int getNumberOfLinks() {
        wait.until(ExpectedConditions.visibilityOfAllElements(list));
        return list.size();
    }
	
}
