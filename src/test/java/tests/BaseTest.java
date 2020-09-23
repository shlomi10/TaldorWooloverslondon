package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import pages.MainPage;
import pages.NewInPage;
import utilities.ExtentListener;
/**
 * @author Shlomi
 * @category Base test
 * @apiNote These is the setup for the runner
 */
@SuppressWarnings("javadoc")
public class BaseTest {

	WebDriver driver;
	NewInPage newInPage;
	MainPage mainPage;
	ExtentListener extentListener;

	@Parameters({ "browser" })
	@BeforeTest(alwaysRun = true)
	public void setup(String browser) {
		try {
			WebDriverManager.chromedriver().setup();
			if (browser.equalsIgnoreCase("Edge")) {
				driver = new EdgeDriver();
			} else if (browser.equalsIgnoreCase("chrome")) {
				driver = new ChromeDriver();
			} else if (browser.equalsIgnoreCase("IE")) {
				driver = new InternetExplorerDriver();
			}
		}catch (Exception e) {
			System.out.println("You enter wrong browser");
		}

		driver.manage().window().maximize();
		mainPage = new MainPage(driver);
		newInPage = new NewInPage(driver);
	
	}

	@AfterTest(alwaysRun = true)
	public void close() {
		driver.quit();
	}
}
