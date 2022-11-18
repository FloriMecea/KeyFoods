package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import utils.SeleniumWrappers;

public class LoginPage extends SeleniumWrappers {

	
	public LoginPage(WebDriver driver) {
	
		super(driver);
	}
	public By usernameField=By.cssSelector("input[id='username']");
	public By passwordField=By.cssSelector("input[id='password']");
	public By submitButton=By.cssSelector("button[type='submit']");

	
	public By loginErrorMessage_wrongUsername=By.cssSelector("ul[role='alert']");
	public By loginErrorMessage_wrongPassword= By.cssSelector("li a[href*='lost-password']");
	public By loginSuccessMessage=By.cssSelector("p a[href*='logout']");
	
	
	public By logoutButton=By.cssSelector("li a[href*='logout']");
	
	public void loginInApp(String username, String password) {
		sendKeys(usernameField,username);		
		sendKeys(passwordField,password);	
		click(submitButton);
	
	}
	
	public void logoutFromApp() {
		
     	click(logoutButton);
	}
	
//	 fie separat dar atunci sunt duplicate
	public boolean loginSuccessMessageIsDisplayed() {
		return driver.findElement(loginSuccessMessage).isDisplayed();
	}
	
	public boolean loginErrorMessageIsDisplayed() {
		boolean wrongLogin=false;
		if (driver.findElement(loginErrorMessage_wrongUsername).isDisplayed() || driver.findElement(loginErrorMessage_wrongPassword).isDisplayed()) {
			wrongLogin=true;
		}
		return wrongLogin;
		 
	}

	
	public boolean loginMessageIsDisplayed(By locator) {
		return driver.findElement(locator).isDisplayed();
	}
	
}
