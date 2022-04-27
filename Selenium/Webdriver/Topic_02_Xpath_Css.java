package Webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_02_Xpath_Css {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	// String projectPath = System.getProperty("user.dir");
	// String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", projectPath + "\\browsers\\chromedriver.exe");
		driver = new ChromeDriver();

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

	}

	// @Test
	public void TC_01() {
		// 1- mo trinh duyet ra:open your browers
		// 2-nhap vao app Facebook:enter facebook page
		driver.get("https://m.facebook.com/?locale=vi_VN&_rdr");

		// 3-nhap vao email textbox
		// Action :Nhập/chọn/hover/drag_drop/get text/...
		// Tìm element:findEleemt (số ít)->1 người
		// Tìm element:findElement (số nhiều)->nhiều người

		// Css
		driver.findElement(By.cssSelector("#m_login_email")).sendKeys("hieu@gmail.com");
		driver.findElement(By.cssSelector("input[id=\"m_login_email\"]")).clear();
		driver.findElement(By.cssSelector("input[name=\"email\"]")).sendKeys("hieu@gmail.com");

		// Xpath
		driver.findElement(By.xpath("//input[@id=\"m_login_password\"]")).sendKeys("181201020");
		driver.findElement(By.xpath("//input[@id=\"m_login_password\"]")).clear();

		// Xpath text
		driver.findElement(By.xpath("//a[text()=\"English (UK)\"]")).click();

	}

	@Test
	public void TC_01_Login_Empty_Email_And_Password() {
		//
		driver.get("http://live.techpanda.org/");

		driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();

		driver.findElement(By.id("email")).sendKeys("");
		driver.findElement(By.name("login[password]")).sendKeys("");

		driver.findElement(By.id("send2")).click();

		Assert.assertEquals(driver.findElement(By.id("advice-required-entry-email")).getText(),
				"This is a required field.");

		Assert.assertEquals(driver.findElement(By.id("advice-required-entry-pass")).getText(),
				"This is a required field.");

	}

	@Test
	public void TC_02_Login_Invalid_Email() {
		driver.get("http://live.techpanda.org/");

		driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();

		driver.findElement(By.id("email")).sendKeys("12345@123.123");
		driver.findElement(By.name("login[password]")).sendKeys("123456");

		driver.findElement(By.id("send2")).click();
		
		Assert.assertEquals(driver.findElement(By.id("advice-validate-email-email")).getText(),
				"Please enter a valid email address. For example johndoe@domain.com.");


	}
	// Invalid:không hợp lệ
	// Incorrect:sai

	@Test
	public void TC_03_Login__Invalid_Password() {
		driver.get("http://live.techpanda.org/");

		driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();

		driver.findElement(By.id("email")).sendKeys("hieu@gmail.com");
		driver.findElement(By.name("login[password]")).sendKeys("123");

		driver.findElement(By.id("send2")).click();

		Assert.assertEquals(driver.findElement(By.id("advice-validate-password-pass")).getText(),
				"Please enter 6 or more characters without leading or trailing spaces.");

	}

	@Test
	public void TC_04_Login_Incorrect_Email() {
		driver.get("http://live.techpanda.org/");

		driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();

		driver.findElement(By.id("email")).sendKeys("automaiton@gmail.com");
		driver.findElement(By.name("login[password]")).sendKeys("123123123");

		driver.findElement(By.id("send2")).click();
		
		Assert.assertEquals(driver.findElement(By.xpath("//li[@class='error-msg']//span")).getText(),
				"Invalid login or password.");


	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
