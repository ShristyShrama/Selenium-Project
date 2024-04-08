package com.simplilearn.Pizza_Hut;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PizzaHut
{
	    private WebDriver driver;

	    public PizzaHut(WebDriver driver)
	    {
	        this.driver = driver;
	    }
	    
	    public void setDeliveryLocation(String location) throws InterruptedException
	    {
	        WebElement locationInput = driver.findElement(By.xpath("//input[@placeholder='Enter your location for delivery']"));
	        locationInput.sendKeys(location);
	        Thread.sleep(2000);
	        locationInput.sendKeys(Keys.ENTER);
	    }
	    
	    public boolean isDealsPage() 
	    {
	        String currentUrl = driver.getCurrentUrl();
	        return currentUrl.contains("deals");
	    }
	    
	    public void goToSidesAndAddItem() throws InterruptedException 
	    {
	        WebElement sidesMenu = driver.findElement(By.xpath("(//span[contains(text(),'Sides')])[2]"));
	        sidesMenu.click();
	        Thread.sleep(2000);
	        WebElement addItemButton = driver.findElement(By.xpath("(//div[@class='mt-auto'])[1]//button"));
	        addItemButton.click();
	    }

	    public boolean isProductDisplayedInBasket(String productName) 
	    {
	        WebElement basketItem = driver.findElement(By.xpath("//div/div[text()='" + productName + "']"));
	        return basketItem.isDisplayed();
	    }
	    
	    public void goToDrinksPage()
	    {
	    	WebElement drinksmenu = driver.findElement(By.xpath("(//span[contains(text(),'Drinks')])[2]"));
			drinksmenu.click();
	    }
	    
	    public void addDrinks()
	    {
	    	WebElement firstdrink = driver.findElement(By.xpath("(//div[@class='mt-auto'])[1]//button"));
			firstdrink.click();	
			WebElement secdrink = driver.findElement(By.xpath("(//div[@class='mt-auto'])[2]//button"));
			secdrink.click();
	    }
	    
	    public void clickCheckoutAndValidate() throws InterruptedException 
	    {
	        WebElement checkoutButton = driver.findElement(By.xpath("//a[@class='button button--primary  justify-between']"));
	        checkoutButton.click();
	        
	        Thread.sleep(2000);

	        String checkoutPageUrl = driver.getCurrentUrl();
	        if (!checkoutPageUrl.contains("checkout")) 
	        {
	            throw new AssertionError("URL does not contain 'checkout'");
	        }
	    }
	    
	    public boolean isOnlinePaymentRadioButtonSelected() 
	    {
	        WebElement paymentBtn = driver.findElement(By.id("payment-method--razorpay"));
	        return paymentBtn.isSelected();
	    }
	    
	    public void clickCOD()
	    {
	    	WebElement codbtn = driver.findElement(By.xpath("(//i)[8]"));
			codbtn.click();
	    }
	    
	    public void enterCustomerDetails(String name, String mobile, String email) {
	        WebElement nameInput = driver.findElement(By.id("checkout__name"));
	        nameInput.sendKeys(name);

	        WebElement mobileInput = driver.findElement(By.id("checkout__phone"));
	        mobileInput.sendKeys(mobile);

	        WebElement emailInput = driver.findElement(By.id("checkout__email"));
	        emailInput.sendKeys(email);
	    }
	    
	    public void applyVoucherAndValidate(String voucherCode) throws InterruptedException {
	        // Click on the Voucher
	        WebElement coupon = driver.findElement(By.xpath("//span[text() = 'Coupon']"));
	        coupon.click();

	        // Enter the voucher code
	        WebElement codeInput = driver.findElement(By.name("voucherId"));
	        codeInput.sendKeys(voucherCode);

	        // Click on Apply
	        WebElement applyButton = driver.findElement(By.xpath("//span[text()='Apply']"));
	        applyButton.click();
	        Thread.sleep(2000);

	        // Validate if an error is displayed
	        WebElement error = driver.findElement(By.xpath("//span[contains(text(), 'Sorry, we don’t')]"));
	        if (error.isDisplayed()) 
	        {
	            // Close the voucher pop up
	            WebElement cancel = driver.findElement(By.xpath("//button[text()='Cancel']"));
	            cancel.click();
	            Thread.sleep(2000);

	            // User should again navigate to the Basket page
	            WebElement basketPage = driver.findElement(By.xpath("//span[text()='Your Basket']"));
	            basketPage.isDisplayed();
	        } else 
	        {
	            throw new AssertionError("Error message not displayed for incorrect voucher");
	        }
	    }
}
