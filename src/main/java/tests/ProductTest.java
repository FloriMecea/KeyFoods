package tests;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import pages.LoginPage;
import pages.NavMenuPage;
import pages.ProductPage;
import utils.BaseTest;
import utils.TestNGListener;

@Listeners(TestNGListener.class)
public class ProductTest extends BaseTest{

	@Parameters({"user","pass"})
	@Test(priority=0)
	public void products(String username, String password) throws InterruptedException {
		NavMenuPage navMenu= new NavMenuPage(driver);
		//login in aplicatie
		navMenu.navigateTo(navMenu.loginLink);
        LoginPage loginPage= new LoginPage(driver);
		
		loginPage.loginInApp(username, password);
		
		
		assertTrue(loginPage.loginSuccessMessageIsDisplayed());
		Thread.sleep(2000);
		
		
		navMenu.navigateTo(navMenu.homeLink);
		navMenu.searchProduct("Pretzels",navMenu.searchListContainsProduct1);

		Thread.sleep(2000);
		assertEquals(navMenu.readURL(),"https://keyfood.ro/product/rold-gold-tiny-twists-pretzels/");
		
		ProductPage productPage= new ProductPage(driver);
		productPage.click(productPage.addToCartButton);
		
	//	assertTrue(productPage.AddToCartMessageIsDisplayed(productPage.messageAddedToCart));
		assertTrue(productPage.AddToCartMessageIsDisplayedGeneric(productPage.messageAddedToCart,"Pretzels"));

	   navMenu.navigateTo(productPage.viewCartButton);
	   navMenu.click(productPage.plusButton);
	   Thread.sleep(2000);
	//   assertEquals(productPage.calculateFinalPrice(productPage.singlePrice),productPage.transformFinalPriceFromStringToDouble(productPage.totalPrice));
	//   Thread.sleep(2000);
	   
	   //Brown Coconut
	   navMenu.searchProduct("Brown Coconut",navMenu.searchListContainsProduct2);
       Thread.sleep(2000);
	   assertEquals(navMenu.readURL(),"https://keyfood.ro/product/fresh-brown-coconut/");
	   productPage.click(productPage.addToCartButton);
	   Thread.sleep(2000);
	  // assertTrue(productPage.AddToCartMessageIsDisplayed1(productPage.messageAddedToCart));  //fie folosim asta fie metoda comuna de jos pt fiecare produs cautat
	   assertTrue(productPage.AddToCartMessageIsDisplayedGeneric(productPage.messageAddedToCart,"Brown Coconut"));
	   navMenu.navigateTo(productPage.viewCartButton);
	   Thread.sleep(2000);
	   assertEquals(productPage.calculateFinalPrice1(productPage.singlePrice, productPage.quantity),productPage.transformFinalPriceFromStringToDouble(productPage.totalPrice));
	
	   navMenu.navigateTo(productPage.checkoutButton);
	   Thread.sleep(2000);
	   assertEquals(navMenu.readURL(),"https://keyfood.ro/checkout-2/");
	   
	   navMenu.navigateTo(productPage.termsAndCondition);
	   navMenu.navigateTo(productPage.placeOrder);
	   Thread.sleep(2000);
	//   assertTrue(productPage.AddToCartMessageIsDisplayedGeneric(productPage.orderNumber,"Pretzels"));
	   assertTrue(driver.findElement(productPage.orderNumber).isDisplayed());
	}
}
