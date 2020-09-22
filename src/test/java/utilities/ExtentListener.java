package utilities;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
/**
 * @author Shlomi
 * @category extent listener
 * @apiNote listeners
 */
@SuppressWarnings("javadoc")
public class ExtentListener extends ExtentManager implements ITestListener {

	String str;

	@Override
	public void onTestStart(ITestResult result) {
		create_test(result.getName());
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		System.out.println("The success test name is: " + result.getName());
		test.log(Status.INFO, "The test name: " + result.getMethod().getDescription());
		test.log(Status.PASS, result.getMethod().getDescription() + " succeded");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		System.out.println("The failed test name is: " + result.getName());
		try {
			test.fail(result.getTestName(),
					MediaEntityBuilder.createScreenCaptureFromPath(CaptureScreen()).build());
		} catch (Exception e) {
			e.printStackTrace();
		}
		test.log(Status.INFO, "The test name: " + result.getMethod().getDescription());
		test.fail(result.getThrowable());
		test.log(Status.FAIL, result.getMethod().getDescription() + " failed");
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onStart(ITestContext context) {
		init();

	}

	@Override
	public void onFinish(ITestContext context) {
		extent.flush();

	}

}
