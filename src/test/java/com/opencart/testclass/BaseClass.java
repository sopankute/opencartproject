package com.opencart.testclass;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseClass {

	public static WebDriver driver;
	public Logger log;
	public Properties prop;

	@BeforeClass(groups = { "Sanity", "Regression", "Master", "DataDriven" })
	@Parameters({ "os", "browser" })
	public void setup(String os, String browser) throws MalformedURLException {

		// load properties file
		prop = new Properties();
		try {
			FileReader file = new FileReader(".//src//test//resources//config.properties");
			prop.load(file);
		} catch (Exception e) {
			e.printStackTrace();
			log.info("reading file exception");
		}

		// load logs for Log4j2.xml file
		log = (Logger) LogManager.getLogger(this.getClass());

		
		if(prop.getProperty("execution_env").equalsIgnoreCase("remote")) {
			
			DesiredCapabilities capabilities=new DesiredCapabilities();
			
//			Operating system
			if(os.equalsIgnoreCase("windows")) {
				capabilities.setPlatform(Platform.WIN10);
			}
			else if(os.equalsIgnoreCase("linux")) {
				capabilities.setPlatform(Platform.LINUX);
			}
			else if(os.equalsIgnoreCase("mac")) {
				capabilities.setPlatform(Platform.MAC);
			}else {
				System.out.println("BaseClass.setup() | No matching platform.");
				return;
			}
			
//			Browser
			switch (browser.toLowerCase()) {
			case "chrome":
				capabilities.setBrowserName("chrome");
				break;
			case "edge":
				capabilities.setBrowserName("MicrosoftEdge");
				break;
			case "firefox":
				capabilities.setBrowserName("firefox");
				break;

			default:
				System.out.println("BaseClass.setup() | Invalid Browser");
				return;
			}
			
			driver = new RemoteWebDriver(new URL("http://192.168.1.5:4444/wd/hub"), capabilities);
			
		}
		
		if(prop.getProperty("execution_env").equalsIgnoreCase("local")) {
			
			switch (browser) {
			case "chrome":
				driver = new ChromeDriver();
				break;
			case "edge":
				driver = new EdgeDriver();
				break;
			case "firefox":
				driver = new FirefoxDriver();
				break;

			default:
				System.out.println("BaseClass.setup() | Invalid Browser");
				return;
			}
		}
		

		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

//		reading url from propeties file
		driver.get(prop.getProperty("url"));
		driver.manage().window().maximize();

		log.info("Application launched");
	}

	@AfterClass(groups = { "Sanity", "Regression", "Master", "DataDriven" })
	public void teardown() {
		driver.close();
		log.info("Application has been closed.");
	}

	public String randomEmail() {
		return RandomStringUtils.randomAlphanumeric(5);
	}

	public String randomTelephone() {
		return RandomStringUtils.randomNumeric(10);
	}

	public String randomPassword() {
		String generatedNumber = RandomStringUtils.randomNumeric(3);
		String generatedString = RandomStringUtils.randomAlphabetic(3);

		return (generatedString + "@" + generatedNumber);
	}

	public String captureScreen(String testName) {
		// TODO Auto-generated method stub

		String timeStamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		String targetFilePath = System.getProperty("user.dir") + "\\screenshots\\" + testName + "_" + timeStamp
				+ ".png";

		TakesScreenshot screenshot = (TakesScreenshot) driver;
		File sourceFile = screenshot.getScreenshotAs(OutputType.FILE);

		File targetFile = new File(targetFilePath);

		sourceFile.renameTo(targetFile);

		return targetFilePath;
	}
}
