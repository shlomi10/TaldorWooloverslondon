package tests;

import org.testng.annotations.Test;
/**
 * @author Shlomi
 * @category main runner
 * @apiNote These is the main runner of tests
 */
@SuppressWarnings("javadoc")
public class MainRunner extends BaseTest {

	String URL = "https://www.wooloverslondon.com/";

	@Test(priority = 1, groups = { "Open Web Site test" }, description = "Open the web site test")
	public void openWebSite() {
		mainPage.getWebSite(URL);
	}

	@Test(priority = 2, groups = { "Up Footer test" }, description = "navigate to new in page")
	public void moveToNewIn() {
		mainPage.closePopUp();
		mainPage.moveToNewIn();
	}
	
	@Test(priority = 3, groups = { "change currency test" }, description = "change currency")
	public void changeCurrency() {
		newInPage.changeTheCurrency();
	}
	
	@Test(priority = 4, groups = { "set filters and sort" }, description = "filters and sort")
	public void setFiltersAndSort() {
		newInPage.selectFilters();
		newInPage.sortItems();
	}
	
	@Test(priority = 5, groups = { "validate prices" }, description = "validate prices")
	public void validatePrices() {
		newInPage.validatePrices();
	}
	
}