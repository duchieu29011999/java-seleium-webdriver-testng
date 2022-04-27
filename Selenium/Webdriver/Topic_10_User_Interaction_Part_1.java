package Webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.seleniumhq.jetty9.io.ByteBufferPool.Bucket;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_10_User_Interaction_Part_1 {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	Actions action;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

		action = new Actions(driver);

	}

	public void TC_01_Hover_Mouse() {
		driver.get("https://member.lazada.vn/user/register?spm=a2o4n.home.header.d6.1905e182CptV3C");

		// Verify Categories button is unDisplayed
		Assert.assertFalse(driver.findElement(By.xpath("//span[text()='Thiết Bị Điện Tử']")).isDisplayed());

		WebElement shortCutLazada = driver.findElement(By.xpath("//span[text()='Danh mục']"));

		action.moveToElement(shortCutLazada).perform();
		sleepInSecond(3);

		// Verify Categories button is Displayed
		Assert.assertTrue(driver.findElement(By.xpath("//span[text()='Thiết Bị Điện Tử']")).isDisplayed());

	}

	public void TC_02_Click_And_Hover() {
		driver.get("https://automationfc.github.io/jquery-tooltip/");

		action.moveToElement(driver.findElement(By.xpath("//input[@id='age']"))).perform();
		sleepInSecond(3);

		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='ui-tooltip-content']")).getText(),
				"We ask for your age only for statistical purposes.");

	}

	public void TC_03_web_fail() {
		driver.get("htpps://hn.telio.vn");

		action.moveToElement(
				driver.findElement(By.xpath("//nav[@class='navigation cdz-fix-left]/span[text()='Đồ uống']")))
				.perform();

		action.click(driver.findElement(By.xpath("nav[@class='navigation cdz-fix-left]//a[text()='Bia']"))).perform();

		// Dom=>Bia ở đấy là được lấy trong dom
		Assert.assertTrue(
				driver.findElement(By.xpath("//h1[@id='page-title-heading']/span[text()='Bia']")).isDisplayed());

		// UI=>get text ở trên giao điện.chữ không phải ở trong dom
		Assert.assertEquals(driver.findElement(By.xpath("//h1[@id='page-title-heading']/span")).getText(), "Bia");
	}

	public void TC_04_Click_and_Hover() {
		driver.get("https://automationfc.github.io/jquery-selectable/");

		// tao 1 list chứa hết tất cả 12 number;
		List<WebElement> allNumber = driver.findElements(By.xpath("//ol[@id='selectable']/li"));
		System.out.println("All number = " + allNumber.size());

		// 0-11:index
		// 1-2-3-.....-12:element value

		action.clickAndHold(allNumber.get(0)).moveToElement(allNumber.get(10)).release().perform();
		sleepInSecond(1);
		
		List<WebElement> allnumberSelected = driver.findElements(By.xpath("//ol[@id='selectable']/li[contains(@class,'ui-selected')]"));
		System.out.println("Number selected = " + allnumberSelected.size());
		
		Assert.assertEquals(allnumberSelected.size(), 9);

	}

	
	public void TC_05_Click_and_Hover_Radom() {
//		driver.get("https://automationfc.github.io/jquery-selectable/");
//
//		// tao 1 list chứa hết tất cả 12 number;
//		List<WebElement> allNumber = driver.findElements(By.xpath("//ol[@id='selectable']/li"));
//		System.out.println("All number = " + allNumber.size());
//
//		// Nhấn phím Ctrl xuống
//		action.keyDown(Keys.CONTROL).perform();
//
//		// Click vào các số 1 -3 -6 -9
//		action.click(allNumber.get(0)).click(allNumber.get(2)).click(allNumber.get(5)).click(allNumber.get(11))
//				.perform();
//
//		// Nhả phím Ctrl ra
//		action.keyUp(Keys.CONTROL).perform();
//
//		List<WebElement> allnumberSelected = driver
//				.findElements(By.xpath("//ol[@id='selectable']/li[contains(@class,'ui-selected')]"));
//		System.out.println("Number selected = " + allnumberSelected.size());
//
//		Assert.assertEquals(allnumberSelected.size(), 4);	
 	}
	
	@Test
	public void TC_06_Double_Click() {
		
		driver.get("https://automationfc.github.io/basic-form/index.html");
		
		action.doubleClick(driver.findElement(By.xpath("//button[@ondblclick='doubleClickMe()']"))).perform();
		sleepInSecond(2);
		
		Assert.assertTrue(driver.findElement(By.xpath("//p[@id='demo' and text()='Hello Automation Guys!']")).isDisplayed());
		
		
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
