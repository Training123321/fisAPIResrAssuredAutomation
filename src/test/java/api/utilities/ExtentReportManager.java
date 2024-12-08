package api.utilities;

import java.sql.Date;
import java.text.SimpleDateFormat;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;


public class ExtentReportManager implements ITestListener{
	
	public ExtentSparkReporter extentSparkReporter;
	 public   ExtentReports extentReports;
	 public   ExtentTest extentTest;
	   String reportName;
	  public void onStart(ITestContext context) {
		  String timeStamp=new SimpleDateFormat("yyyy.MM.DD.HH.mm.ss").format(new Date(0));
		  reportName="Test-Report_" + timeStamp+".html";
		  extentSparkReporter= new ExtentSparkReporter(".\\Reports\\"+ reportName);
		  extentSparkReporter.config().setDocumentTitle("RestAssuredAPITestRepprt");
		  extentSparkReporter.config().setReportName("GET  BPI API info");
		  extentSparkReporter.config().setTheme(Theme.DARK);
		  
		  extentReports=new ExtentReports();
		  extentReports.attachReporter(extentSparkReporter);
		  extentReports.setSystemInfo("Operating System", System.getProperty("os.name"));
		  extentReports.setSystemInfo("User Name", System.getProperty("user.name"));
		  
		  
	  }
	  public void onTestSuccess(ITestResult result) {
		  extentTest= extentReports.createTest(result.getName());
		  extentTest.log(Status.PASS, "Test Passed");
		    // not implemented
	  }
	  
	 public  void onTestFailure(ITestResult result) {
		  extentTest= extentReports.createTest(result.getName());
		  extentTest.log(Status.FAIL, "Test Failed");
		  extentTest.log(Status.FAIL, result.getThrowable().getMessage());
		  }
	 public void onFinish(ITestContext context) {
		 extentReports.flush();
		 
		 
		  }
}
