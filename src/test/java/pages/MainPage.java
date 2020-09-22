package pages;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
/**
 * @author Shlomi
 * @category main page
 * @apiNote These functions are to test the Woolovers main page
 */
@SuppressWarnings({ "javadoc" })
public class MainPage extends BasePage {

	// constructor
	public MainPage(WebDriver driver) {
		super(driver);
	}

	private By btnFirstPopUpClose  = By.cssSelector("span.glClose");
	private By btnSecondPopUpClose = By.xpath("//img[@aria-label='Popup Close Button']");
	private By newInOption = By.xpath("//li[contains(@class,'New Arrivals')]/a[contains(@href,'new-styles')]");
	private String expectedURL = "https://www.wooloverslondon.com/new-styles";
	private By basket = By.cssSelector(".quick-basket-tab");

	//open webSite
	public void getWebSite(String siteURL) 
	{
		navigateToURL(siteURL);
	}
	
	// function to close the pop up
	public void closePopUp() 
	{
		waitForElementToBeClickable(btnFirstPopUpClose);
		clickOnElem(btnFirstPopUpClose);
		mouseHooverFromElement(basket);
		waitForElementToBeClickable(btnSecondPopUpClose);
		clickOnElem(btnSecondPopUpClose);
	}
	
	// function to move to new in page
	public void moveToNewIn() {
		waitForElementToBeClickable(newInOption);
		waitForElementToBeVisible(newInOption);
		clickOnElem(newInOption);
		assertTrue(getURLCurrentUrl().contains(expectedURL));
	}
}
