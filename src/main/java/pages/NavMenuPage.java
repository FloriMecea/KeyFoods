package pages;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utils.SeleniumWrappers;

public class NavMenuPage extends SeleniumWrappers {
	
	
	
	public NavMenuPage(WebDriver driver) {
		
		super(driver);
	}
	
	//locatori

	public By iconLogin=By.cssSelector("i[class*='icon-user']");
	public By loginLink=By.linkText("My account");	
	public By homeLink=By.linkText("HOME");
	public By searchField=By.cssSelector("input[type='search']");
//	public By searchListContainsProduct1=By.cssSelector("a[href*='pretzels']");
	public By searchListContainsProduct1=By.xpath("//strong[contains(text(),'Pretzels')]");
	public By searchListContainsProduct2=By.xpath("//strong[contains(text(),'Brown Coconut')]");
	
	
	

	
	
	public void navigateTo(By locator) { //o metoda care o pot folosi pe urma sa o apelez 
		//pt mai multi locatori
		//adica in loc sa fac o metoda pentru fiecare locator, fac una singura
		driver.findElement(locator).click();
	}
	
	public boolean isProductDisplayedInTheList(String name, By locator) {
	        WebElement element =  driver.findElement(locator);
			WebDriverWait wait= new WebDriverWait(driver,Duration.ofSeconds(10));
			wait.until(ExpectedConditions.visibilityOf(element));
			return element.isDisplayed(); 
	}
	
	public void searchProduct(String name, By locator) {
	   
	   sendKeys(searchField,name);
	   click(locator);
	
	} 
	
	
 
}
