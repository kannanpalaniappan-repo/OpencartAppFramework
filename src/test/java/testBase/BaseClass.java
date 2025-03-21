package testBase;


import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
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
	public static Logger logger;
	public Properties prop;

	@BeforeClass(groups= {"Sanity","Regression","Master","DataDriven"})
	@Parameters({"browser","os"})
	public void setUp(String browser, String os) throws IOException {
		
		FileReader reader = new FileReader("./src//test//resources//config.properties");
		prop = new Properties();
		prop.load(reader);
		logger = LogManager.getLogger(this.getClass());
		
		
		if(prop.getProperty("env").equalsIgnoreCase("remote")){
			
			DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
			
			if(os.equalsIgnoreCase("windows")) {
				desiredCapabilities.setPlatform(Platform.WIN10);
			}
			else if(os.equalsIgnoreCase("mac")) {
				desiredCapabilities.setPlatform(Platform.MAC);
			}
			else {
				System.out.println("OS name doesn't match to any platform");
			}
			
			
			switch(browser.toLowerCase()) {
				case "chrome":desiredCapabilities.setBrowserName("chrome");break;
				case "edge":desiredCapabilities.setBrowserName("MicrosoftEdge");break;
				default: System.out.println("Not a valid browser"); return;
		    }
			
			
			
			driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), desiredCapabilities);
			
		}
		
		
		if(prop.getProperty("env").equalsIgnoreCase("local")) {
		
				switch(browser.toLowerCase()) {
				case "chrome":driver = new ChromeDriver();break;
				case "edge":driver = new EdgeDriver();break;
				case "firefox":driver = new FirefoxDriver();break;
				default: System.out.println("Not a valid browser"); return;
			}
		}
		
		
		
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.get(prop.getProperty("appurl"));
	}

	@AfterClass(groups= {"Sanity","Regression","Master","DataDriven"})
	public void tearDown() {
		driver.quit();
	}
	
	
	public String randomAlphabetic() {
		return RandomStringUtils.randomAlphabetic(5);	
	}
	
	public String randomAlphaNumeric() {
		return RandomStringUtils.randomAlphanumeric(5);	
	}
	
	public String randomNumeric() {
		return RandomStringUtils.randomNumeric(5);	
	}

	public String captureScreen(String testName) throws IOException{
		
		String timeStamp = new SimpleDateFormat("yyyymmddhhmmss").format(new Date());
		
		TakesScreenshot screenshot = (TakesScreenshot)driver;
		File sourceFile = screenshot.getScreenshotAs(OutputType.FILE);
		
		String targetFilePath = System.getProperty("user.dir")+"\\screenshots\\"+testName+"_"+timeStamp+".png";
		File targetFile = new File(targetFilePath);
		sourceFile.renameTo(targetFile);
		
		return targetFilePath;
	}
}
