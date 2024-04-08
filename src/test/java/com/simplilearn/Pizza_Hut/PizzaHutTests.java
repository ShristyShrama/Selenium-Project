package com.simplilearn.Pizza_Hut;

import java.io.IOException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;

public class PizzaHutTests
{
	ChromeDriver driver;
	private PizzaHut pizzaOrderPage;
	
	@BeforeClass
	public void setup() throws IOException
	{
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		
		ExcelReader excelReader = new ExcelReader();
        String filePath = "C:\\Users\\Lenovo\\Documents\\Projects\\Pizza-Hut-App-Selenium\\Pizza-Hut\\src\\test\\resources\\testdata\\data.xlsx";
        String sheetName = "Sheet1"; 
        int rowNumber = 0; 
        int cellNumber = 0; 

        String url = excelReader.readURLFromExcel(filePath, sheetName, rowNumber, cellNumber);
        driver.get(url);
        
        pizzaOrderPage = new PizzaHut(driver);
	}
	
	@Test
	@Parameters({"deliveryLocation", "name", "mobile", "email", "voucherCode"})
	public void testPizzaOrder(String deliveryLocation, String name, String mobile, String email, String voucherCode) throws InterruptedException
	{	

		//Set delivery location 
		pizzaOrderPage.setDeliveryLocation(deliveryLocation);
		
		//Validate that url has 'deals' text 
		Thread.sleep(5000);
		Assert.assertTrue(pizzaOrderPage.isDealsPage(), "URL does not contain 'deals'");
		
		//Go to sides and add any item that is below 200
		pizzaOrderPage.goToSidesAndAddItem();
		Thread.sleep(3000);
		
		//Validate the product is added
		Assert.assertTrue(pizzaOrderPage.isProductDisplayedInBasket("Cheezy Sprinkled Fries"),
                "Cheezy Sprinkled Fries is not displayed in the basket");
		
		//Navigates to drink page
		pizzaOrderPage.goToDrinksPage();
		Thread.sleep(3000);
		
		//Add any 2 drinks
		pizzaOrderPage.addDrinks();
		
		//Click on checkout btn & validate checkout page
		pizzaOrderPage.clickCheckoutAndValidate();
		
		//Validate that the Online Payment radio button is selected by default
		Assert.assertTrue(pizzaOrderPage.isOnlinePaymentRadioButtonSelected(),
                "Online Payment radio button is not selected by default");
		
		//Change the Payment option to Cash
		pizzaOrderPage.clickCOD();

		//Validate that the I agree checkbox is checked by default
		WebElement checkbox = driver.findElement(By.xpath("(//span[normalize-space()='I agree to receive promotional communication.'])[1]"));
		checkbox.isSelected();
		
		//Enter name, mobile, and email address
		pizzaOrderPage.enterCustomerDetails(name, mobile, email);
		
		//Click on the Apply Gift Card link
	    WebElement link = driver.findElement(By.xpath("//span[text() = 'Apply Gift Card']"));
	    link.click();
	    Thread.sleep(2000);
		
		//Click on the Voucher
		//Give the Voucher code as 12345 and submit
		//Validate if an error is coming that the number is incorrect
		//Close the voucher pop up
	    //User should again navigate to your Basket page
	    pizzaOrderPage.applyVoucherAndValidate(voucherCode);
		
	}
	
	@AfterClass
	public void teardown()
	{
		driver.close();
	}

}

