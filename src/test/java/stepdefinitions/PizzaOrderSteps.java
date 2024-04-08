package stepdefinitions;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;

public class PizzaOrderSteps
{
	ChromeDriver driver;
	
	@Before
	public void setup()
	{
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		System.out.println("Setup");
	}
	
	@Given("User launch Pizzahut application with {string}")
	public void userLaunchPizzahutApplication(String url)
	{	
		driver.get(url);
	}
	
	@When("User see pop up for delivery asking for enter location")
	public void userSeePopUpForDeliveryAskingForEnterLocation()
	{
		driver.findElement(By.xpath("//input[@placeholder='Enter your location for delivery']"));
	}
	
	@Then("User type address as {string}")
	public void userTypeLocation(String location) throws InterruptedException
	{
		driver.findElement(By.xpath("//input[@placeholder='Enter your location for delivery']")).sendKeys(location);
		Thread.sleep(2000);
	}
	
	@And("User select first auto populate drop down option")
	public void userSelectFirstAutoPopulateDropdownOption() 
	{
		driver.findElement(By.xpath("//input[@placeholder='Enter your location for delivery']")).sendKeys(Keys.ENTER);
	}
	
	@When("User navigate to deails page")
	public void userNavigateToDealsPage()
	{
		driver.getCurrentUrl().contains("deals");
	}
	
	@Then("User validate vegetarian radio button flag is off")
	public void userValidateVegiterianRadioButtonIsOff() throws InterruptedException
	{
		Thread.sleep(2000);
		driver.findElement(By.xpath("(//span[@class='py-4 px-5 border rounded-full flex items-center cursor-pointer bg-grey-light border-grey-light justify-start'])[1]")).isDisplayed();
	}
	
	@And("User clicks on Pizzas menu bar option")
	public void userClicksOnPizzaMenuOption() throws InterruptedException
	{
		driver.findElement(By.xpath("(//span[contains(text(),'Pizza')])[2]")).click();
		Thread.sleep(2000);
	}
	
	@When("User select add button of any pizza from Recommended")
	public void userSelectAddButtonOfAnyPizzaFromRecommended() throws InterruptedException
	{
		driver.findElement(By.xpath("(//div[@class='mt-auto'])[1]//button")).click();
		Thread.sleep(2000);
	}
	
	@Then("User see that the pizza is getting added under Your Basket")
	public void userSeeThatPizzaIsGettingAddedUnderBasket()
	{
		driver.findElement(By.xpath("//a[@title='Click to make changes']")).isDisplayed();
	}
	
	@And("User validate pizza price plus Tax is checkout price")
	public void userValidatePizzaPlusTaxIsCheckoutPrice()
	{
		String pizzapriceelement = driver.findElement(By.xpath("//span[@class='subtotal']")).getText();
		String taxelement = driver.findElement(By.xpath("//span[contains(text(),'₹8.70')]")).getText();
		String totalamountelement = driver.findElement(By.xpath("//span[@class='amountdue']")).getText();
		
		String pizzaprice = (pizzapriceelement.replace("₹", ""));
		String tax = (taxelement.replace("₹", ""));
		String totalamount = (totalamountelement.replace("₹", ""));
		
		totalamount = pizzaprice + tax;
	}
	
	@Then("User validate checkout button contains Item count")
	public void userValidateCheckoutButtonContainsItemCount()
	{
		driver.findElement(By.xpath("//span[contains(text(),'1 item')]")).isDisplayed();
	}
	
	@And("User validate checkout button contains total price count")
	public void userValidateCheckoutButtonContainsTotalPriceCount()
	{
		driver.findElement(By.xpath("//span[@class='amountdue']")).isDisplayed();
	}
	
	@Then("User clicks on Drinks option")
	public void userClicksOnDrinksOption() throws InterruptedException
	{
		driver.findElement(By.xpath("(//span[contains(text(),'Drinks')])[2]")).click();
		Thread.sleep(2000);
	}
	
	@And("User select Pepsi option to add into the Basket")
	public void userSelectPepsiOptionToAddIntoBasket()
	{
		driver.findElement(By.xpath("(//div[@class='mt-auto'])[1]//button")).click();
	}
	
	@Then("User see 2 items are showing under checkout button")
	public void userSee2ItemsAreShowingUnderCheckoutButton()
	{
		driver.findElement(By.xpath("//span[contains(text(),'2 item')]")).isDisplayed();
	}
	
	@And("User see total price is now more than before")
	public void userSeeTotalPriceIsNowMoreThanBefore()
	{
		String aftertotalamountelement = driver.findElement(By.xpath("//span[@class='amountdue']")).getText();
		
		String aftertotalamount = (aftertotalamountelement.replace("₹", ""));
		
	}
	
	@Then("User remove the Pizza item from Basket")
	public void userRemovePizzaItemFromBasket()
	{
		driver.findElement(By.xpath("(//button[@data-synth='basket-item-remove--margherita-bestseller-pan-personal'])[1]")).click();
		
	}
	
	@And("see Price tag got removed from the checkout button")
	public void userSeePriceTagGotRemovedFromCheckoutButton()
	{
		WebElement totalamountelement = driver.findElement(By.xpath("//span[@class='amountdue']"));
	}
	
	@And("User see 1 item showing in checkout button")
	public void userSee1ItemShowingInCheckoutButton()
	{
		driver.findElement(By.xpath("//span[contains(text(),'1 item')]")).isDisplayed();
	}
	
	@Then("User Clicks on Checkout button")
	public void userClicksOnCheckoutButton()
	{
		driver.findElement(By.xpath("(//span[contains(@class,'absolute inset-0 flex-center')])[2]")).click();
	}
	
	@And("User see minimum order required pop up is getting displayed")
	public void userSeeMinimumOrderRequiredPopUpIsGettingDisplayed()
	{
		driver.findElement(By.xpath("//div[@class='pt-20 bg-white p-20 m-20 rounded text-center shadow relative']")).isDisplayed();
	}
	
	@After
	public void teardown()
	{
		driver.quit();
		System.out.println("Teardown");
	}
	
}
