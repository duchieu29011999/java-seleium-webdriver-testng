package Webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_06_Textbox_TextArea {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String email ;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
		email ="nguyenduchieu@gmail.com";
	}

	@Test
	public void TC_01_Register() { 
		driver.get("https://demo.guru99.com/v4/index.php");
		
		driver.findElement(By.xpath("//a[text()=\"here\"]")).click();
		
		driver.findElement(By.xpath("//input[@name=\"emailid\"]")).sendKeys(email);
		driver.findElement(By.xpath("//input[@name=\"emailid\"]")).sendKeys(email);

	}

	@Test
	public void TC_02_Login() {

	}

	@Test
	public void TC_03_New_Customer() {

	}
	
	@Test
	public void TC_04_Edit_Customer() {

	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
