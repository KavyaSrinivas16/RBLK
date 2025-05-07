package generic_Utilities;

import java.sql.SQLException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {

	/* Create Object */
	public DataBaseUtility dbLib = new DataBaseUtility();
	public FileUtility fLib = new FileUtility();
	public ExcelUtility eLib = new ExcelUtility();
	public JavaUtility jLib = new JavaUtility();
	public WebDriver driver = null;
	public WebDriverUtility wlib = new WebDriverUtility();
	public static WebDriver sdriver = null;

//	@BeforeSuite(groups = { "smokeTest", "regressionTest" })
//	public void configBS() throws Throwable {
//		System.out.println("===Connect to DB , Report Config===");
//		dbLib.getDbconnection();
//	}

	// @Parameters ("BROWSER")
	@BeforeClass()
	public void configBC() throws Throwable {

		System.out.println("==Launch the BROWSER==");

		// String BROWSER = fLib.getDataFromPropertiesFile("browser");
		String BROWSER = System.getProperty("browser", fLib.getDataFromPropertiesFile("browser"));
		if (BROWSER.equals("chrome")) {
			WebDriverManager.chromedriver().setup();
			ChromeOptions chromeOptions = new ChromeOptions();
			driver = new ChromeDriver(chromeOptions);
		} else if (BROWSER.equals("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			FirefoxOptions firefoxOption = new FirefoxOptions();
			driver = new FirefoxDriver(firefoxOption);
		} else if (BROWSER.equals("edge")) {
			WebDriverManager.edgedriver().setup();
			EdgeOptions edgeOption = new EdgeOptions();
			driver = new EdgeDriver(edgeOption);
		} else {
			WebDriverManager.chromedriver().setup();
			ChromeOptions chromeOptions = new ChromeOptions();
			driver = new ChromeDriver(chromeOptions);
		}
		sdriver = driver;
		UtilityClassObject.setDriver(driver);
	}
	
	
//
//	@AfterClass()
//	public void configAC() {
//		System.out.println("==Close the BROWSER==");
//		driver.quit();
//	}

//	@AfterSuite(groups = { "smokeTest", "regressionTest" })
//	public void configAS() throws SQLException {
//		System.out.println("===close Db , Report backUP====");
//		dbLib.closeDbconnection();
//	}
}
