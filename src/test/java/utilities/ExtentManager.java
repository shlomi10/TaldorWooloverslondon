package utilities;

import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.imageio.ImageIO;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
/**
 * @author Shlomi
 * @category extent manager
 * @apiNote extent setup
 */
@SuppressWarnings("javadoc")
public class ExtentManager {
	public ExtentReports extent;
	public ExtentTest test;
	public ExtentHtmlReporter htmlReporter;
	public String reportDate;
	public String filePath;

	public void init() {
		reportDate = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss").format(new Date());
		filePath = ".\\reports\\Woolovers Report " + reportDate;
		new File(filePath).mkdirs();

		htmlReporter = new ExtentHtmlReporter(filePath + "/report.html");
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);

		htmlReporter.config().setDocumentTitle("Automation report on Woolovers");
		htmlReporter.config().setReportName("Woolovers Test");
		htmlReporter.config().setEncoding("windows-1255");
	}

	public void create_test(String testName) {
		test = extent.createTest(testName);
	}

	public String CaptureScreen() throws AWTException, IOException {
		String picDate = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss").format(new Date());
		String imagePath = filePath + "/pic" + picDate + ".jpg";
		Robot robot = new Robot();
		BufferedImage screenShot = robot
				.createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
		ImageIO.write(screenShot, "jpg", new File(imagePath));
		return imagePath;
	}
}
