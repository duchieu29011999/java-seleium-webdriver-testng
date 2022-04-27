package Webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_11_Popup {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	WebDriverWait explicitWait;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", projectPath + "\\broewserss\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

		explicitWait = new WebDriverWait(driver, 60);
	}

	@Test
	public void TC_01_Fixed_Popup() {
		driver.get("https://www.zingpoll.com/");

		driver.findElement(By.id("Loginform")).click();

		// Chờ cho 1 element được hiện thị
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//form[@id='loginForm']")));
		Assert.assertTrue(driver.findElement(By.xpath("//form[@id='loginForm']")).isDisplayed());
		sleepInSecond(4);

		driver.findElement(By.xpath("//div[@id='Login']//button[@class='close']")).click();
		sleepInSecond(4);

		// Chờ cho 1 element không còn hiển thị
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//form[@id='loginForm']")));
		Assert.assertFalse(driver.findElement(By.xpath("//form[@id='loginForm']")).isDisplayed());

	}
	

	@Test
	public void TC_02_Fixed_Popup() {
		driver.get("https://bni.vn/");
		sleepInSecond(60);

		// Chờ cho 1 element được hiện thị
//		explicitWait.until(
//				ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='sgpb-popup-dialog-main-div']")));
		Assert.assertTrue(driver.findElement(By.xpath("//div[@id='sgpb-popup-dialog-main-div']")).isDisplayed());

		driver.findElement(By.xpath("//input[@class='sgpb-simple-inputs js-subs-submit-btn']")).click();

		// Chờ cho 1 element có thể click được không
		explicitWait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//img[@class='sgpb-popup-close-button-1']")));

		driver.findElement(By.xpath("//img[@class='sgpb-popup-close-button-1']")).click();

		explicitWait.until(
				ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@id='sgpb-popup-dialog-main-div")));
		Assert.assertFalse(driver.findElement(By.xpath("//div[@id='sgpb-popup-dialog-main-div")).isDisplayed());

	}

	public void TC__Fixed_Popup_HomeWork_1() {
//		driver.get("https://ngoaingu24h.vn/");
//
//		// Kiểm tra popup Đăng nhập không hiển thị
//		// explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@id='modal-login-v1']//div[@class='modal-dialog']")));
//		Assert.assertTrue(
//				driver.findElement(By.xpath("//div[@id='modal-login-v1']//div[@class='modal-dialog']")).isDisplayed());
//		sleepInSecond(3);
//
//		// Click vào Button Đăng nhập
//		explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class='login_ icon-before']")));
//		driver.findElement(By.xpath("//button[@class='login_ icon-before']")).click();
//
//		// Kiểm tra popup Đăng nhập hiển thị
//		explicitWait.until(ExpectedConditions
//				.invisibilityOfElementLocated(By.xpath("//div[@id='modal-login-v1']//div[@class='modal-dialog']")));
//		Assert.assertTrue(
//				driver.findElement(By.xpath("//div[@id='modal-login-v1']//div[@class='modal-dialog']")).isDisplayed());

	}

	@Test
	public void TC_03_Ramdom_Popup_In_Dom() {
		//Step 1
		driver.get("https://blog.testproject.io/");
		
		//Step 2
		//Có xuất hiện-đóng popup -qua step tiếp theo
		//không xuất hiện-qua step tiếp theo
		if(driver.findElement(By.xpath("//div[@class='mailch-wrap']")).isDisplayed()) {
			//Chờ cho 1 element có thể click được hay không
			explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='close-mailch']")));
			driver.findElement(By.xpath("//div[@id='close-mailch']")).click();
		}
		//Step 3:
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//section[@id='search-2']//input[@class='search-field']")));
		driver.findElement(By.xpath("//section[@id='search-2']//input[@class='search-field']")).sendKeys("selenium");
		

	}

	public void TC_04_Ramdom_Popup_Not_In_Dom() {

	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	public void sleepInSecond(long timeInSecond) {
		try {
			Thread.sleep(timeInSecond * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
//visibility- hiển thị-visible/
//invisibility-không còn hiển thị
