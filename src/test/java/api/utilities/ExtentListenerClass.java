package api.utilities;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentListenerClass implements ITestListener {

	

	ExtentSparkReporter htmlReporter;//user interface
	ExtentReports reports;//environmental info
	ExtentTest  test;//entries for test
	
	public void configureReport()
	{
		String timestamp = new SimpleDateFormat("yyyy.mm.dd.hh.mm.ss").format(new Date());
				String reportName = "PetStoreAutomationTestReport-"+ timestamp +".html";
				htmlReporter = new ExtentSparkReporter(System.getProperty("user.dir")+"//Reports//" + reportName);
				reports = new ExtentReports();
				reports.attachReporter(htmlReporter);
				
				//add system information/environment info to reports
				reports.setSystemInfo("Machine", "testpc1");
				reports.setSystemInfo("OS", "windows 11");
				reports.setSystemInfo("user name:", "Satyashri");
				
				//configuration to change look and feel of report
				htmlReporter.config().setDocumentTitle("Extent Listener Report Demo");
				htmlReporter.config().setReportName("This is my First Report");
				htmlReporter.config().setTheme(Theme.DARK);
		
	   }
	//Onstart method is called when any test starts
	@Override
	public void onStart(ITestContext context) {
		configureReport();
		System.out.println("On start method invoked....");
		
	}

	@Override
	public void onFinish(ITestContext context) {
	System.out.println("On Finished method invoked....");
	reports.flush();//it is mandatory to call flush method to ensure information is written to started reporter.
	}
	
	//when test case get failed this method is called.
	@Override
	public void onTestFailure(ITestResult result) {
		System.out.println("Name of test method failed:" +result.getName());
		test = reports.createTest(result.getName());//create entry in html report
		test.log(Status.FAIL, MarkupHelper.createLabel("Name of the failed test case is: "+ result.getName(), ExtentColor.RED));
		
		String screenShotPath = System.getProperty("user.dir" + "\\Screenshots\\" + result.getName() + ".png");
		File screenShotFile = new File(screenShotPath);
		if(screenShotFile.exists()) {
			test.fail("Captured Screenshot is below:" +test.addScreenCaptureFromPath(screenShotPath));
		}
	}
	
	//when test case get skipped this method is called
	@Override
	public void onTestSkipped(ITestResult result) {
		System.out.println("Name of test method skipped:" + result.getName());
		test = reports.createTest(result.getName());
		test.log(Status.SKIP, MarkupHelper.createLabel("Name of the skip test case is: " + result.getName(),ExtentColor.YELLOW));
	}

	//when test case get started this method is called.
	@Override
	public void onTestStart(ITestResult result) {
		System.out.println("Name of test method started:" + result.getName());
	}
	
	//when test case get passed this method is called
	@Override
	public void onTestSuccess(ITestResult result) {
		System.out.println("Name of test method successfully executed:" + result.getName());
		test = reports.createTest(result.getName());
		test.log(Status.PASS, MarkupHelper.createLabel("Name of the passed test case is: " + result.getName(), ExtentColor.GREEN));
	
	}
	
}
