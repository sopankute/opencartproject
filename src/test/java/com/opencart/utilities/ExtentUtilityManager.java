package com.opencart.utilities;

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
import com.opencart.testclass.BaseClass;

public class ExtentUtilityManager implements ITestListener {

	public ExtentSparkReporter sparkReporter;
	public ExtentReports extent;
	public ExtentTest test;
	String reportFileName;

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
//		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
//		Date date = new Date();
//		String currentDateTimeStamp = dateFormat.format(date);

		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());

//		File name
		reportFileName = "Test-Report-" + timeStamp + ".html";

//		set  UI of report like theme, color
		sparkReporter = new ExtentSparkReporter(".\\reports\\"+reportFileName);
//		Title of report
		sparkReporter.config().setDocumentTitle("Opencart Automation Report");
//		Name of report
		sparkReporter.config().setReportName("Opencart Functional Testing");
//		Theme
		sparkReporter.config().setTheme(Theme.DARK);

		extent = new ExtentReports();
//		attach below info to report
		extent.attachReporter(sparkReporter);
		extent.setSystemInfo("Application", "OpenCart");
		extent.setSystemInfo("Module", "Admin");
		extent.setSystemInfo("SubModule", "Customer");
		extent.setSystemInfo("User Name", System.getProperty("user.name")); // who's running application
		extent.setSystemInfo("Enviroment", "QA");

//		getting OS and Browser name from xml
		String os = context.getCurrentXmlTest().getParameter("os");
		extent.setSystemInfo("Operating System", os);
		String browser = context.getCurrentXmlTest().getParameter("browser");
		extent.setSystemInfo("Browser", browser);

//		getting included groups
		List<String> includedGroups = context.getCurrentXmlTest().getIncludedGroups();
		if (!includedGroups.isEmpty()) {
			extent.setSystemInfo("Groups", includedGroups.toString());
		}
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
//		adding Class name to report which having that successful Test method
		test = extent.createTest(result.getTestClass().getName());
//		adding group name to report
		test.assignCategory(result.getMethod().getGroups());

		test.log(Status.PASS, result.getName() + " got successfully executed.");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
//		adding Class name to report which having that successful Test method
		test = extent.createTest(result.getTestClass().getName());
//		adding group name to report
		test.assignCategory(result.getMethod().getGroups());

		test.log(Status.FAIL, result.getName() + " got Failed");
		test.log(Status.INFO, result.getThrowable().getMessage());

		try {
			String imgPath = new BaseClass().captureScreen(result.getName());
			test.addScreenCaptureFromPath(imgPath);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
//		adding Class name to report which having that successful Test method
		test = extent.createTest(result.getTestClass().getName());
//		adding group name to report
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.SKIP, result.getName() + " got Skipped");
		test.log(Status.INFO, result.getThrowable().getMessage());
	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		extent.flush();

		String pathOfExtentRopert = System.getProperty("user.dir")+ "\\reports\\"+reportFileName;
		File extentReport = new File(pathOfExtentRopert);

		try {
			Desktop.getDesktop().browse(extentReport.toURI());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
