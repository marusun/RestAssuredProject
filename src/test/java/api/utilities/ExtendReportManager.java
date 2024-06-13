package api.utilities;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtendReportManager implements ITestListener {

	public ExtentSparkReporter sparkReporter;
	public ExtentReports  extentReport;
	public ExtentTest  test;
	
	String repName;
	
	public void onStart(ITestContext testContext) {
		
		String timeStamp=new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		
		repName="Test_Report"+timeStamp+"html";
		
		sparkReporter=new ExtentSparkReporter("./reports/"+repName);//specify location of report
		sparkReporter.config().setDocumentTitle("RestAssuredAutomationProject");//title of report
		sparkReporter.config().setReportName("Pet Store API's");//reporter name
		sparkReporter.config().setTheme(Theme.DARK);
		extentReport=new ExtentReports();
		extentReport.attachReporter(sparkReporter);
		
		extentReport.setSystemInfo("Application", "Maruthi Pet Store API");
		extentReport.setSystemInfo("Operating System", System.getProperty("os.name"));
		extentReport.setSystemInfo("UserName", System.getProperty("user.name"));
		extentReport.setSystemInfo("Environment","QA");
		extentReport.setSystemInfo("User", "Maruthi Kumar");
		
	}
	
	public void onTestSuccess(ITestResult result) {
		test=extentReport.createTest(result.getName());
		test.assignCategory(result.getMethod().getGroups());
		test.createNode(result.getName());
		test.log(Status.PASS, "Test Is Passed");
		
		
	}
	
	public void onTestFailure(ITestResult result) {
		test=extentReport.createTest(result.getName());
		test.assignCategory(result.getMethod().getGroups());
		test.createNode(result.getName());
		test.log(Status.FAIL, "Test Is Failed");
		test.log(Status.FAIL, result.getThrowable().getMessage());
		
		
		
	}
	
	public void onTestSkipped(ITestResult result) {
		test=extentReport.createTest(result.getName());
		test.assignCategory(result.getMethod().getGroups());
		test.createNode(result.getName());
		test.log(Status.SKIP, "Test Is Skipped");
		test.log(Status.SKIP, result.getThrowable().getMessage());
		
		
		
	}
	
	public void onFinish(ITestContext testContext) {
		extentReport.flush();
		
		
		
		
		
	}
	



	
}
