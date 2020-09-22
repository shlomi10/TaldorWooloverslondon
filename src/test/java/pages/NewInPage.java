package pages;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
/**
 * @author Shlomi
 * @category new in page
 * @apiNote These functions are to test the Woolovers new in page
 */
@SuppressWarnings({ "javadoc" })
public class NewInPage extends BasePage {

	public NewInPage(WebDriver driver) {
		super(driver);
	}

	private By shippingLocationBtn = By.cssSelector("li.nav-dropdown-container.country-selection.country-selection--rest-of-world");
	private By currencyOptions = By.id("gle_selectedCurrency");
    private final String currencyVal = "USD";
    private By saveBTNcurrency = By.xpath("//input[@data-action='SaveAndClose']");
    private By resetFilter = By.cssSelector("div.js-reset-filters.hidden-xs.active");    
    private By filterBtn = By.xpath("//h2[@class='listing-navigation__title js-show-filters']");   
    private By womenOptionAtFilter = By.xpath("//a[@title='Women']");  
    private By womenOptionStatus = By.xpath("//a[@title='Women']/..");  
    private final String expectedStatusBTN = "active"; 
    private By dressOptionAtFilter = By.xpath("//a[@title='Dress']");   
    private By dressOptionStatus = By.xpath("//a[@title='Dress']/..");
    private By resultNumber = By.xpath("//div[@class='filter-controls hidden-xs']//span");   
    private final String expectedResultNumber = "12"; 
    private String actualResultNumber;
    private By applyFilterBtn = By.cssSelector("div.filter-controls.hidden-xs>.btn.js-apply-filters");
    private By sortBtn = By.cssSelector(".listing-navigation__title.js-show-sort-by");   
    private By priceLowToHighOption = By.xpath("//a[@title='Price Low to High']");
    private By pricesList = By.xpath("//span[@data-originalprice]");
    private Double sum = 0.0;
    private final int expectedLimitPrice = 270;
    
    // function to change the currency to USD
	public void changeTheCurrency() {
		clickOnElem(shippingLocationBtn);		
		//pause(2000);
		//waitForElementToBeClickable(currencyOptions);
		safeClick(currencyOptions);
		//clickOnElem(currencyOptions);
		pause(2000);
		selectOptionFromDropBox(currencyOptions, currencyVal);		
		clickOnElem(saveBTNcurrency);
	}
	
	// function to clear filters and to choose new ones
	public void selectFilters() {	
		//pause(2000);
		//clickAndWaitOnElem(resetFilter);
		//clickOnElem(resetFilter);
		safeClick(resetFilter);
		//pause(2000);
		//clickAndWaitOnElem(filterBtn);
		//clickOnElem(filterBtn);
		safeClick(filterBtn);
		waitForElementToBeClickable(womenOptionAtFilter);
		//pause(2000);
		//clickOnElem(womenOptionAtFilter);
		//clickOnElem(womenOptionAtFilter);
		safeClick(womenOptionAtFilter);
		pause(2000);
		assertEquals(getAttributeFromElement(womenOptionStatus, "class"), expectedStatusBTN, "the women filter is not marked");
		//pause(2000);
		//waitForElementToBeClickable(dressOptionAtFilter);
		//clickOnElem(dressOptionAtFilter);
		safeClick(dressOptionAtFilter);
		pause(2000);
		assertEquals(getAttributeFromElement(dressOptionStatus,"class"), expectedStatusBTN, "the dress filter is not marked");
		//pause(2000);
		waitForTextToBePresentedInElement(resultNumber, expectedResultNumber);
		actualResultNumber = getTextFromElement(resultNumber);
		actualResultNumber = actualResultNumber.substring(actualResultNumber.indexOf('(')+1,actualResultNumber.indexOf(')'));
		assertEquals(actualResultNumber, expectedResultNumber, "result number is not matched the expected result");
		//pause(2000);
		//clickOnElem(applyFilterBtn);
		safeClick(applyFilterBtn);
	}

	// function to sort items
	public void sortItems() {
		//pause(2000);
		//clickAndWaitOnElem(sortBtn);
		//clickOnElem(sortBtn);
		safeClick(sortBtn);
		//clickAndWaitOnElem(priceLowToHighOption);
		clickOnElem(priceLowToHighOption);
		waitForElementToBePresented(resetFilter);
	}

	//function to validate price of items
	public void validatePrices() {
		pause(2000);
		List <Double> prices = getPricesTextFromElements(pricesList);
		assertTrue(ascPrices(prices), "items are not sorted in ascending order");
		sum = sumPrices(prices);
		assertTrue(sum < expectedLimitPrice, "items total price is higher than $270");
	}

}
