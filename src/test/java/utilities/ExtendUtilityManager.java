package utilities;

import java.awt.Desktop;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import testbase.BaseClass;

public class ExtendUtilityManager implements ITestListener{
	
	public ExtentSparkReporter sparkReporter;
	public ExtentReports extent;
	public ExtentTest test;
	String reportName;
	
	//This will executed before all the test methods
	public void onStart(ITestContext context) {
		//To prepare Time Stamp
		SimpleDateFormat df=new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
		//Date from Java Util Packege
		Date date=new Date();
		String timeStamp=df.format(date);
		//Report Name
		reportName="Test-Report-"+timeStamp+".html";
		//Extent Spark Reporter->We can set UI of the Report
		sparkReporter=new ExtentSparkReporter("./reports/"+reportName);
		sparkReporter.config().setDocumentTitle("OpenCart Automation Report");
		sparkReporter.config().setReportName("Open cart Functional Testing");
		sparkReporter.config().setTheme(Theme.DARK);
		
		//ExtentReport ->We can mention common information about the test
		extent=new ExtentReports();
		extent.attachReporter(sparkReporter);
		extent.setSystemInfo("Application", "OPEN-CART");
		extent.setSystemInfo("module", "Admin");
		extent.setSystemInfo("Sub-module", "Customer");
		extent.setSystemInfo("environment", "QA");
		String OperatingSys = context.getCurrentXmlTest().getParameter("os");
	   extent.setSystemInfo("Operating System", OperatingSys);
	   String browserName = context.getCurrentXmlTest().getParameter("browser");
	  extent.setSystemInfo("Browser Name", browserName);
	  List<String> includedGroups = context.getCurrentXmlTest().getIncludedGroups();
	if(!includedGroups.isEmpty()) {
		extent.setSystemInfo("Groups", includedGroups.toString());
	}
	}
	
	public void onTestSuccess(ITestResult result) {
		//This Will create new entry in the report
		test = extent.createTest(result.getTestClass().getName());
		//To display groups in the report
		test.assignCategory(result.getMethod().getGroups());
		//Pass
		test.log(Status.PASS, result.getName()+ "Successfully Executed");
	  }
	
	public void onTestFailure(ITestResult result) {
	    test=extent.createTest(result.getTestClass().getName());
	    test.assignCategory(result.getMethod().getGroups());
	    test.log(Status.FAIL, result.getName()+"Got Failed");
	    test.log(Status.INFO, result.getThrowable().getMessage());
	    //Screen shot of the Failure
	    try {
	    	String imgPath=new BaseClass().captureScreen(result.getName());
	    	test.addScreenCaptureFromPath(imgPath);
	    }catch(Exception e) {
	    	e.printStackTrace();
	    }
	  } 
	
	public void onTestSkipped(ITestResult result) {
		test=extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.SKIP, result.getName()+"Got skipped");
		test.log(Status.INFO, result.getThrowable().getMessage());
	}
	public void onFinish(ITestContext context) {
	  extent.flush();
	  String pathOfExtentReport=System.getProperty("user.dir")+"/reports/"+reportName;
	  File extentReport=new File(pathOfExtentReport);
	  try {
		  Desktop.getDesktop().browse(extentReport.toURI());
	  }catch(Exception e) {
		  e.printStackTrace();
	  }
	  }
	
	

}
