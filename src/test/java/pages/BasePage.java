package pages;

import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * @author Shlomi
 * @category Main methods
 * @apiNote These functions are for all pages
 */

@SuppressWarnings({ "javadoc", "static-method"})
public class BasePage {

	public WebDriver driver;
	public WebDriverWait wait;
	public Actions action;
	public Select mySelection;
	
	// constructor
	public BasePage(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver, 10);
	}

	// function to navigate to URL
	public void navigateToURL(String URL) {
		driver.navigate().to(URL);
	}
	
	// function to get the current URL
	public String getURLCurrentUrl() {
		return driver.getCurrentUrl();
	}
	
	// function to get back webElement
	public WebElement getWebElement(By elem) {
		return driver.findElement(elem);
	}
	
	//function to get select field
	public void selectOptionFromDropBox(By elem, String val) {
		mySelection = new Select(driver.findElement(elem));
		mySelection.selectByValue(val);
	}
	
	// function to click on element
	public void clickOnElem(By elem) {
		driver.findElement(elem).click();
	}
	
	// function to click safe
	public void safeClick(By elem) {
		pause(2000);
		driver.findElement(elem).click();
		}
		
	//function to pause runing 
	public void pause(int delay) {
		try {
			Thread.sleep(delay);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	// function to wait for element to be clickable
	public void waitForElementToBeClickable(By elem) {
		wait.until(ExpectedConditions.elementToBeClickable(elem));
	}

	// function to wait for element to be visible
	public void waitForElementToBeVisible(By elem) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(elem));
	}
	
	// function to wait for element to be presented
	public void waitForElementToBePresented(By elem) {
		wait.until(ExpectedConditions.presenceOfElementLocated(elem));
	}
	
	// function to wait for text to be in element
	public void waitForTextToBePresentedInElement(By elem, String text) {
		wait.until(ExpectedConditions.textToBePresentInElementLocated(elem, text));
	}
	
	// function to validate if element is displayed
	public boolean IsElementDisplayed(By elem) {
		return driver.findElement(elem).isDisplayed();
	}

	// function to get text from element
	public String getTextFromElement(By elem) {
		return driver.findElement(elem).getText();
	}

	// function to move courser to element
	public void mouseHooverFromElement(By elem) {
		action = new Actions(driver);
		WebElement element1 = driver.findElement(elem);
		action.moveToElement(element1).build().perform();
	}

	// function to get any attribute from element
	public String getAttributeFromElement(By elem, String attribute) {
		return driver.findElement(elem).getAttribute(attribute);
	}

	// function to get number text of elements
	public List<Double> getPricesTextFromElements(By elem) {
		List<WebElement> elementList = driver.findElements(elem);
		List<Double> doubleList = new ArrayList<>();
		for (WebElement element : elementList) {
			String text = element.getText();
			StringBuilder res = new StringBuilder();
			for(int i=0;i<text.length();i++) {
				if((text.charAt(i)>='0' && text.charAt(i)<='9') || text.charAt(i)=='.') {
					res.append(text.charAt(i));
				}
			}
			doubleList.add(Double.valueOf(res.toString()));
		}
		return doubleList;
	}
	
	// function to get sum prices
	public double sumPrices(List<Double> prices) {
		double totalPrice = 0.0;
		for (double price : prices) {
			totalPrice += price;
		}
		return totalPrice;
	}
	
	// function to validate that prices are appearing in ascending order
	public boolean ascPrices(List<Double> prices) {
		for (int i = 1; i <prices.size(); i++) {
			if(prices.get(i)< prices.get(i-1))
				return false;
		}
		return true;
	}
	
}
