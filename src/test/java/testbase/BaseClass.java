package testbase;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
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
//Keep all the common methods for all the test cases here.
//Every test cases should extend from the base class
	public static WebDriver driver;
	public Logger logger;
	public Properties properties;
	
	@Parameters({"os","browser"})
	@BeforeClass(groups= {"Sanity","Reg","Master"})
	public void setUP(String operatingSystem,String browserName) throws IOException {
		
		//Loading config.properties File
		properties=new Properties();
		FileInputStream oFis=new FileInputStream("./src/test/resources/config.properties");
		properties.load(oFis);
		
		//Log Manager is a predefined class
		logger=LogManager.getLogger(this.getClass());
		
		if(properties.getProperty("execution_env").equalsIgnoreCase("remote")) {
			
			DesiredCapabilities capabilities=new DesiredCapabilities();
			//Operating system
			if(operatingSystem.equalsIgnoreCase("window")) {
				capabilities.setPlatform(Platform.WIN11);
				
			}
			else if(operatingSystem.equalsIgnoreCase("mac")) {
				capabilities.setPlatform(Platform.MAC);
			}
			else if(operatingSystem.equalsIgnoreCase("linux")) {
				capabilities.setPlatform(Platform.LINUX);
			}
			else {
				System.out.println("No matching operating system");
				return;
			}
			
			//browser
			switch(browserName.toLowerCase()) {
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
				System.out.println("No matching browser");
				return;
			}
			driver=new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capabilities);
		}
		if(properties.getProperty("execution_env").equalsIgnoreCase("local")){
		switch(browserName.toLowerCase()) {
		case "chrome":
			driver=new ChromeDriver();
			break;
			
		case "edge":
			driver=new EdgeDriver();
			break;
			
		case "firefox":
			driver=new FirefoxDriver();
			break;
		default:
			System.out.println("Invalid browser name");
			return;
			
		}
		}
		
		driver.get(properties.getProperty("url"));
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		
	}
	
	@AfterClass(groups= {"Sanity","Reg","Master"})
	public void tearUP() {
		driver.quit();
	}
	
	public String captureScreen(String name) {
		String timeStamp=new SimpleDateFormat("YYYYMMddhhmmss").format(new Date());
	TakesScreenshot screenShot=(TakesScreenshot)driver;
	File sourceFile=screenShot.getScreenshotAs(OutputType.FILE);
	String filePath=System.getProperty("user.dir")+"/screenshot/"+name+"_"+timeStamp+".png";
	File destFile=new File(filePath);
	sourceFile.renameTo(destFile);
	return filePath;
	
	}
}
