package base;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.asserts.SoftAssert;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import utils.ElementActions;
import utils.ExcelReader;
import utils.ExtentReportClass;

public class TestBase {

	public static WebDriver driver;
	public static Properties config = new Properties();
	public static Properties locators = new Properties();
	public static FileInputStream fileInputStream;
	public static ExcelReader excelReader = new ExcelReader(
			System.getProperty("user.dir") + "\\src\\test\\resources\\excel\\testdata.xlsx");
	public ExtentReports extentReports = ExtentReportClass.getExtentReport();
	public static ExtentTest extentTest;
	public static SoftAssert softAssert = new SoftAssert();
	public static ElementActions elementActions = new ElementActions();
	public static WebDriverWait wait;

	@BeforeSuite
	public void setUp() throws IOException {
		if (driver == null) {
			fileInputStream = new FileInputStream(System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\Config.properties");
			config.load(fileInputStream);

			fileInputStream = new FileInputStream(System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\Locators.properties");
			locators.load(fileInputStream);
		}

		switch (config.getProperty("browser")) {
			case "chrome": {
				WebDriverManager.chromedriver().setup();
				driver = new ChromeDriver();
				break;
			}
			case "firefox": {
				WebDriverManager.firefoxdriver().setup();
				driver = new FirefoxDriver();
				break;
			}
			case "opera": {
				System.setProperty("webdriver.opera.driver",System.getProperty("user.dir") + "\\src\\test\\resources\\executables\\operadriver.exe");
				driver = new OperaDriver();
				break;
			}
		}

		driver.get(config.getProperty("pageUrl") + "/login.php");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Integer.parseInt(config.getProperty("implicitWait")),
				TimeUnit.SECONDS);
		wait = new WebDriverWait(driver, 10);
	}
	
	public void doLogIn(String email, String password) {
		elementActions.click("loginPageEmailInput_XPATH");
		elementActions.type("loginPageEmailInput_XPATH", email, true);
		elementActions.click("loginPagePasswordInput_XPATH");
		elementActions.type("loginPagePasswordInput_XPATH", password, true);
		elementActions.click("loginPageLogInButton_XPATH");
	}
	
	public void doLogOut() {
		driver.get(config.getProperty("pageUrl") + "/admin.php");
		elementActions.click("adminPageLogOutButton_XPATH");
	}
	
	public void fillFieldsToCreateBlog(String title, String description, String url, String filePath, String text) {
		elementActions.click("adminPageCreateNewBlogButton_XPATH");
		elementActions.click("bcwBlogTitleInput_ID");
		elementActions.type("bcwBlogTitleInput_ID", title, true);
		elementActions.click("bcwBlogDescriptionInput_ID");
		elementActions.type("bcwBlogDescriptionInput_ID", description, true);
		elementActions.click("bcwBlogUrlInput_ID");
		elementActions.type("bcwBlogUrlInput_ID", url, true);
		elementActions.type("bcwBlogUploadImageInput_ID", System.getProperty("user.dir") + filePath, false);
		elementActions.switchToIframe("bcwBlogTextEditorIFrame_XPATH");
		elementActions.click("bcwBlogTextEditorInput_XPATH");
		elementActions.type("bcwBlogTextEditorInput_XPATH", text, true);
		elementActions.switchBackFromIFrame();
		elementActions.click("bcwSaveBlogButton_XPATH");
	}
	
	public void deleteFirstBlog() {
		elementActions.scrollDown("adminPageContinueReadArrow_XPATH");
		elementActions.click("adminPageFirstBlogDeleteButton_CLASS");
		clickOkOnAlert();
	}
	
	public void goBack() {
		driver.navigate().back();
		extentTest.log(LogStatus.INFO, "Went back to previous page: " + driver.getCurrentUrl());
	}
	
	public void clickOkOnAlert() {
		driver.switchTo().alert().accept();
		extentTest.log(LogStatus.INFO, "Clicked \"OK\" on the alert");
	}

	public boolean isElementPresent(By by) {
		try {
			driver.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	public String getCurrentUrl() {
		return driver.getCurrentUrl();
	}
	
	public int getPageResponse(String pageUrl) throws IOException {
		URL url = new URL(pageUrl);
		HttpURLConnection connection = (HttpURLConnection)url.openConnection();
		connection.setRequestMethod("GET");
		connection.connect();
		return connection.getResponseCode();
	}

	@AfterSuite
	public void tearDown() {
		if (driver != null) {
			driver.quit();
		}
	}

}
