package pages;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import utils.SeleniumWrappers;

public class ProductPage extends SeleniumWrappers{

	public ProductPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	public By addToCartButton=By.cssSelector("button[class*='add_to_cart']");
	public By messageAddedToCart=By.cssSelector("div[role='alert']");
	public By viewCartButton=By.cssSelector("div[role='alert']  a[href*='cart-2']");
	public By plusButton=By.cssSelector("i[class*='icon-plus']");
	//public By totalPrice=By.xpath("//strong/span/bdi[contains(text(),'5.98')]");
	public By singlePrice=By.cssSelector("td[data-title='Price'] span[class*='Price-amount'] bdi");
	public By totalPrice=By.cssSelector("td[data-title='Total'] span[class*='Price-amount'] bdi");
	public By quantity=By.cssSelector("input[name*='qty']");
	public By checkoutButton=By.cssSelector("a[class*='checkout-button']");
	public By termsAndCondition=By.cssSelector("input[id='terms']");
	public By placeOrder=By.cssSelector("button[id='place_order']");	
	public By orderNumber=By.cssSelector("ul[class*='order_details'] li:nth-of-type(1)");	
	
	
	
	
	public boolean AddToCartMessageIsDisplayed(By locator) { //facusem cate o metoda pt fiecare produs, asta era pt produsul1 cautat
		
		String text=driver.findElement(locator).getText();
		System.out.println(text);
		text=text.substring(10);
		System.out.println("----new text:------");
		System.out.println(text);
		if (text.equals("“Rold Gold Tiny Twists Pretzels” has been added to your cart.") == true){
			System.out.println("yeeyy1");
			return true;
		} else 
			return false;

	}
	public boolean AddToCartMessageIsDisplayed1(By locator) { //facusem cate o metoda pt fiecare produs, asta era pt produsul2 cautat
		
		String text=driver.findElement(locator).getText();
		System.out.println(text);
		text=text.substring(10);
		System.out.println("----new text:------");
		System.out.println(text);
		if (text.equals("“Fresh Brown Coconut” has been added to your cart.") == true){
			System.out.println("yeeyy22");
			return true;
		} else 
			return false;

	}
	
	public boolean AddToCartMessageIsDisplayedGeneric(By locator, String name) { 
		//in loc de 2 metode separate, am facut o singura metoda care primeste name ca parametru in functie de fiecare produs
		
		String text=driver.findElement(locator).getText();
		System.out.println(text);
		text=text.substring(10);
		System.out.println("----new text:------");
		System.out.println(text);
		if (text.contains(name) == true){
			System.out.println("yeeyy22");
			return true;
		} else 
			return false;

	}
	
	public double calculateFinalPrice(By locator_singlePrice) {
		double total_price=0;
		   String single_price, cantitate;
		   
		  single_price= driver.findElement(locator_singlePrice).getText();
		  System.out.println("single price is: " + single_price);
		  single_price=single_price.substring(1);
		  System.out.println("single price is now good: " + single_price);
		  System.out.println(driver.findElement(quantity).getAttribute("value"));
		 
		  cantitate= driver.findElement(quantity).getAttribute("value");

		  System.out.println("quantity is: " +cantitate);
		  int cant=Integer.parseInt(cantitate);
		  double single=Double.parseDouble(single_price);
		  
		  total_price=cant*single;
		   total_price = BigDecimal.valueOf(total_price).setScale(2, RoundingMode.DOWN).doubleValue();
		   System.out.println(total_price); 
		  System.out.println("total price is: " +total_price);
		
		return total_price;
	}
	
	public double calculateFinalPrice_receivingWebElement(WebElement singlePrice, WebElement quantity_element) {
		double total_price=0;
		   String single_price, cantitate;
		   
		 
		  single_price= singlePrice.getText();
		  System.out.println("single price is: " + single_price);
		  single_price=single_price.substring(1);
		  System.out.println("single price is now good: " + single_price);
		  System.out.println(driver.findElement(quantity).getAttribute("value"));
		 
		 // cantitate= driver.findElement(quantity).getAttribute("value");
		  cantitate= quantity_element.getAttribute("value");
		  
		  System.out.println("quantity is: " +cantitate);
		  int cant=Integer.parseInt(cantitate);
		  double single=Double.parseDouble(single_price);
		  
		  total_price=cant*single;
		   total_price = BigDecimal.valueOf(total_price).setScale(2, RoundingMode.DOWN).doubleValue();
		   System.out.println(total_price); 
		  System.out.println("total price is: " +total_price);
		
		return total_price;
	}
	
	public double calculateFinalPrice1(By locator_price, By locator_quantity) {
		double total_price=0;
		   
		   List <WebElement> list1=new ArrayList <WebElement>();
		   List <WebElement> list2=new ArrayList <WebElement>();
		  // double[] price_array = new double[list.size()];
		   double[] price_array = new double[2];
		   
		   list1=driver.findElements(locator_price);
		   System.out.println("Lista price are dimensiunea: " +list1.size());
		   
		   list2=driver.findElements(locator_quantity);
		   System.out.println("Lista quantity are dimensiunea: " +list2.size());
		   
		
		   for (int i=0; i<list1.size();i++) {
			   System.out.println(i);
			   price_array[i]=calculateFinalPrice_receivingWebElement(list1.get(i),list2.get(i));
			   System.out.println("----------------");
			   System.out.println("Pretul produsului" + i + "este: " + price_array[i]);	
			   total_price=total_price+price_array[i];
		   }
		
		   total_price = BigDecimal.valueOf(total_price).setScale(2, RoundingMode.DOWN).doubleValue();
		return total_price;
	}
	
	public double transformFinalPriceFromStringToDouble(By locator_totalPrice) {
		double total_price_double=0;
		   String total_price;
		   
		   total_price= driver.findElement(locator_totalPrice).getText();
		  System.out.println("total price from the Total area is: " + total_price);
		  total_price=total_price.substring(1);
		  System.out.println("total price from the Total area is now good: " + total_price);
		 
		   total_price_double= Double.parseDouble(total_price);
		   total_price_double = BigDecimal.valueOf(total_price_double).setScale(2, RoundingMode.DOWN).doubleValue();
		  		
		  System.out.println("total price from the Total area is now double: " + total_price_double);
		
		return total_price_double;
	}

}
