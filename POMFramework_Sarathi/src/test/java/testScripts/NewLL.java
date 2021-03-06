package testScripts;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.SQLException;
import java.util.Hashtable;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.openqa.selenium.By;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import applicationPages.ApplicationStatusPage;
import applicationPages.CommonPortal_ServiceEnabling;
import applicationPages.Data_Refreshfrom_Database;
import applicationPages.Flows;
import applicationPages.LLModule;
import applicationPages.LLSlotBookingPagee;
import applicationPages.LoginRTO;
import applicationPages.NewLLRegistrtionPage;
import applicationPages.OnlineTest;
import frameworkLibrary.BaseClass;

public class NewLL extends BaseClass {
	WebDriver driver;
	LLModule llmodule;
	Flows validate;
	ApplicationStatusPage appstatus;

	@BeforeMethod
	public void Predatacheck() throws IOException {
		loaddata();
		System.setProperty("webdriver.chrome.driver", "BrowserServers\\chromedriver.exe");
		ChromeOptions capability = new ChromeOptions();
		capability.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
		capability.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
		capability.addArguments("use-fake-device-for-media-stream");
		capability.addArguments("use-fake-ui-for-media-stream");
		capability.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.ACCEPT_AND_NOTIFY);
		Map<String, Object> preferences = new Hashtable<String, Object>();
		capability.setExperimentalOption("prefs", preferences);
		preferences.put("plugins.always_open_pdf_externally", true);
		driver = new ChromeDriver(capability);
		String env = getdata("ApplicationEnvironment");
		if (env.equalsIgnoreCase("SarathiCOV")) {
			driver.get(prop.getProperty("SarathiCOV"));
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			System.out.println("Welcome to SarathiCOV");
		} else if (env.equalsIgnoreCase("SarathiProd")) {
			driver.get(prop.getProperty("SarathiProd"));
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			System.out.println("Welcome to Sarathiprod");
		} else if (env.equalsIgnoreCase("CAS_SarathiCOV")) {
			driver.get(prop.getProperty("CAS_SarathiCOV"));
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			System.out.println("Welcome to CAS_SarathiCOV");
		} else if (env.equalsIgnoreCase("CAS_SarathiProd")) {
			driver.get(prop.getProperty("CAS_SarathiProd"));
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			System.out.println("Welcome to CAS_SarathiProd");
		} else {
			System.out.println("Application URL is not matched.Please try again");
		}

	}

	@Test(invocationCount = 1)
	public void NewLL_Public() throws ClassNotFoundException, IOException, SQLException, InterruptedException {
		llmodule = new LLModule(driver);
		llmodule.ArrangeDataSet();
		llmodule.SelectState();
		llmodule.Initiate_Process();
		llmodule.Validating_Flows();
	}

	@Test(invocationCount = 10)
	public void LLBacklog() throws IOException, ClassNotFoundException, SQLException, InterruptedException {
		llmodule = new LLModule(driver);
		llmodule.ArrangeDataSet();
		llmodule.Submit_LLBacklog();

	}

	@Test(invocationCount = 1)
	public void ApplicationStatus() {
		appstatus = new ApplicationStatusPage(driver);

		try {
			// llmodule.StartAPP();
			llmodule = new LLModule(driver);
			llmodule.SelectState();

			appstatus.Clickon_Application_Status();
			appstatus.Set_Application_Number();
			appstatus.Set_DateofBirth();
			appstatus.Clickon_Submit_Button();
			validate = new Flows(driver);
			validate.VerifyFlows();
			validate.RTOLevel_Status();
		} catch (IOException | ClassNotFoundException | SQLException | InterruptedException e) {
			e.printStackTrace();
		}

	}

	@Test(invocationCount = 1)
	public void onlinetest() throws ClassNotFoundException, IOException, SQLException, InterruptedException {
		System.setProperty("webdriver.chrome.driver", "BrowserServers\\chromedriver.exe");
		ChromeOptions capability = new ChromeOptions();
		capability.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
		capability.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
		capability.addArguments("use-fake-device-for-media-stream");
		capability.addArguments("use-fake-ui-for-media-stream");
		Map<String, Object> preferences = new Hashtable<String, Object>();
		capability.setExperimentalOption("prefs", preferences);
		preferences.put("plugins.always_open_pdf_externally", true);
		WebDriver driver = new ChromeDriver(capability);
		driver.get("https://sarathicov.nic.in:8443/sarathiservice/stateSelection.do");
		driver.manage().window().maximize();
		NewLLRegistrtionPage NewLLRegistrtionPage = new NewLLRegistrtionPage(driver);
		NewLLRegistrtionPage.Selectstatename("Madhya Pradesh");
		OnlineTest onlinetest = new OnlineTest(driver);
		onlinetest.navigatetonewwindow();
		onlinetest.SelectLanguageandPin();

	}

	@Test(invocationCount = 5)
	public void LLSlot() throws UnknownHostException {

		System.out.println("Start Time" + GetCurrentTimeStamp());
		System.out.println(InetAddress.getLocalHost());
		System.setProperty("webdriver.chrome.driver", "BrowserServers\\chromedriver.exe");
		ChromeOptions capability = new ChromeOptions();
		capability.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
		capability.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
		capability.addArguments("use-fake-device-for-media-stream");
		capability.addArguments("use-fake-ui-for-media-stream");
		Map<String, Object> preferences = new Hashtable<String, Object>();
		capability.setExperimentalOption("prefs", preferences);
		preferences.put("plugins.always_open_pdf_externally", true);
		WebDriver driver = new ChromeDriver(capability);
		driver.get("https://sarathicov.nic.in:8443/slots/llslotbook.do?id=sarlltest");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

		driver.findElement(By.xpath("//input[@name='subtype']")).click();
		driver.findElement(By.xpath("//input[@id='applno1']")).sendKeys(getdata("Appno"));
		driver.findElement(By.xpath("//input[@id='dob']")).sendKeys("01/06/1984");
		driver.findElement(By.xpath("//input[@value='  SUBMIT  ']")).click();
		LLSlotBookingPagee LLSlotBookingPage = new LLSlotBookingPagee(driver);
		LLSlotBookingPage.Clickon_ProceedtoBookSlot_Button();
		LLSlotBookingPage.Booking_Slot();
		LLSlotBookingPage.Clickon_Bookslotbutton();
		LLSlotBookingPage.Clickon_Confirm_slotbookingbutton();
		System.out.println("End Time" + GetCurrentTimeStamp());

	}

	@Test(invocationCount = 1)
	public void Navigation() throws ClassNotFoundException, IOException, SQLException, InterruptedException {
		String guess = getdata("State");

		while ("West Bengal".equals(guess))
			;
		{
			System.setProperty("webdriver.chrome.driver", "BrowserServers\\chromedriver.exe");
			ChromeOptions capability = new ChromeOptions();
			capability.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
			capability.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
			capability.addArguments("use-fake-device-for-media-stream");
			capability.addArguments("use-fake-ui-for-media-stream");
			Map<String, Object> preferences = new Hashtable<String, Object>();
			capability.setExperimentalOption("prefs", preferences);
			preferences.put("plugins.always_open_pdf_externally", true);
			WebDriver driver = new ChromeDriver(capability);
			driver.get("https://sarathi.parivahan.gov.in/sarathiservice/stateSelection.do");
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
			guess = getdata("State");
			WebElement st = driver.findElement(By.xpath("//select[@name='stName']"));
			Select s = new Select(st);
			loaddata();
			s.selectByVisibleText(prop.getProperty(getdata("State")));
			driver.findElement(By.xpath("//a[text()='Login']")).click();
			LoginRTO RTO = new LoginRTO(driver);
			RTO.setUserName(getdata("UserName"));
			driver.findElement(By.xpath("//input[@id='password']")).sendKeys(getdata("Password"));
			// driver.findElement(By.xpath("//input[@id='frmsubmt']")).click();
			// Scanner sc = new Scanner(System.in);
			// System.out.println("Enter Captcha:");
			// String txr = sc.next();
			// driver.findElement(By.xpath("//input[@id='logmode']")).sendKeys(txr);
			JFrame Dialog = new JFrame();
			String name = JOptionPane.showInputDialog(Dialog, "Enter Captcha");
			driver.findElement(By.xpath("//input[@id='logmode']")).sendKeys(name);
			driver.findElement(By.xpath("//input[@name='submit']")).click();
			CommonPortal_ServiceEnabling Enable = new CommonPortal_ServiceEnabling(driver);
			Enable.Clickon_Switchto();
			Enable.Navigate_CommonPortal();
			driver.findElement(By.xpath("//a[text()='Slots']")).click();
			driver.findElement(By.xpath("//a[text()='LogOut']")).click();
			guess = getdata("State");
			System.out.println(guess);
		}

	}

	@Test(invocationCount = 1)
	public void URLNavigations() throws IOException {
		llmodule = new LLModule(driver);
		// llmodule.ArrangeDataSet();
		try {
			llmodule.SelectState();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		driver.close();

	}

	@AfterMethod
	public void CloseBrowser() {
		System.out.println("**************After Method***************");
	}

}
