package Webdriver;

import static org.testng.Assert.assertEquals;

import java.util.Set;
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

public class Topic_13_Windown_Tap_Part_1 {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	WebDriverWait explictiWait;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", projectPath + "\\broewser\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		explictiWait = new WebDriverWait(driver, 10);
	}

	@Test
	public void TC_01_Only_2_Window_Tabs() {
		driver.get("https://automationfc.github.io/basic-form/index.html");

		// Lấy ra ID của tab trước khi click
		String parentWindowIDString = driver.getWindowHandle();
		System.out.println(parentWindowIDString);

		driver.findElement(By.xpath("//a[text()='GOOGLE']")).click();
		sleepInSecond(3);

		swtichToWindowByID(parentWindowIDString);

		Assert.assertTrue(driver.findElement(By.xpath("//img[@class='lnXdpd']")).isDisplayed());
		//// img[@id='hplogo'] chạy cho firefox
		String googleWindowID = driver.getWindowHandle();

		swtichToWindowByID(googleWindowID);
		sleepInSecond(2);

		driver.findElement(By.xpath("//a[text()='FACEBOOK']")).click();

	}

	@Test
	public void TC_02_Genater_Than_2_Windows_Tab() {
		driver.get("https://automationfc.github.io/basic-form/index.html");

		String parentID = driver.getWindowHandle();

		driver.findElement(By.xpath("//a[text()='GOOGLE']")).click();
		sleepInSecond(2);

		switchToWindowByTitle("Google");
		sleepInSecond(2);
		Assert.assertTrue(driver.findElement(By.xpath("//img[@class='lnXdpd']")).isDisplayed());

		switchToWindowByTitle("SELENIUM WEBDRIVER FORM DEMO");
		sleepInSecond(2);

		driver.findElement(By.xpath("//a[text()='FACEBOOK']")).click();
		sleepInSecond(2);

		switchToWindowByTitle("Facebook - Đăng nhập hoặc đăng ký");
		sleepInSecond(2);
		Assert.assertTrue(driver.findElement(By.xpath("//input[@id='email']")).isDisplayed());

		switchToWindowByTitle("SELENIUM WEBDRIVER FORM DEMO");
		sleepInSecond(2);

		driver.findElement(By.xpath("//a[text()='TIKI']")).click();
		sleepInSecond(2);

		switchToWindowByTitle("Tiki - Mua hàng online giá tốt, hàng chuẩn, ship nhanh");
		sleepInSecond(2);
		Assert.assertTrue(driver.findElement(By.xpath("//input[@type='text']")).isDisplayed());

		// Chỉ giữ lại parent còn lại đóng hết các tab đã mở
		closeWindowTabExceptParent(parentID);
	}

	@Test
	public void TC_03_Live_Guru_Compar_Product() {
		driver.get("http://live.techpanda.org/");

		driver.findElement(By.xpath("//a[text()='Mobile']")).click();
		String parentID = driver.getWindowHandle();

		driver.findElement(By.xpath(
				"//a[text()='IPhone']/parent::h2//following-sibling::div[@class='actions']//a[text()='Add to Compare']"))
				.click();

		Assert.assertEquals(
				driver.findElement(By.xpath("//span[text()='The product IPhone has been added to comparison list.']"))
						.getText(),
				"The product IPhone has been added to comparison list.");

		driver.findElement(By.xpath(
				"//a[text()='Sony Xperia']/parent::h2//following-sibling::div[@class='actions']//a[text()='Add to Compare']"))
				.click();

		Assert.assertEquals(driver
				.findElement(By.xpath("//span[text()='The product Sony Xperia has been added to comparison list.']"))
				.getText(), "The product Sony Xperia has been added to comparison list.");

		driver.findElement(By.xpath("//button[@title='Compare']")).click();
		sleepInSecond(2);

		switchToWindowByTitle("Products Comparison List - Magento Commerce");

		Assert.assertEquals(driver.findElements(By.xpath("//h2[@class='product-name']/a")).size(), 2);

		closeWindowTabExceptParent(parentID);
		sleepInSecond(2);

//		driver.findElement(By.xpath("//input[@id='search']")).sendKeys("Sony Xperia");
//		driver.findElement(By.xpath("//button [@title='Search']")).click();
		driver.findElement(By.xpath("//a[text()='Clear All']")).click();

		explictiWait.until(ExpectedConditions.alertIsPresent());
		driver.switchTo().alert().accept();

		Assert.assertTrue(
				driver.findElement(By.xpath("//span[text()='The comparison list was cleared.']")).isDisplayed());

	}

	// >= 2 windows/2 tab
	public void switchToWindowByTitle(String exppectedWindowTitle) {
		// lấy ra tất cả các window/tab đang có
		Set<String> allWindowIDs = driver.getWindowHandles();
		System.out.println("Số lượng của sổ + tab đang có" + allWindowIDs.size());

		// Đuyệt qua các giá trị trong set
		for (String windowID : allWindowIDs) {
			// swtich vào từng window /tab cho trước
			driver.switchTo().window(windowID);

			// get ra tittle của từng window /tab
			String actuaWindowTitle = driver.getTitle();

			// Kiểm tra nếu như cái nào mà bằng vs title mong muốn
			if (actuaWindowTitle.equals(exppectedWindowTitle)) {

				// Thoát khỏi vòng lặp không duyệt nữa
				break;

			}
		}

	}

	// 2 window/2 tab
	public void swtichToWindowByID(String parentID) {
		// lấy ra tất cả các ID của window/tab đang có
		Set<String> allWindownsID = driver.getWindowHandles();

		// duyệt qua từng id
		for (String windowID : allWindownsID) {
			System.out.println(windowID);
			// Nếu như ID nào mà khác với ID parent ID thì nhảy vào hàm if
			if (!windowID.equals(parentID)) {
				// Switch vào window/tab theo ID
				driver.switchTo().window(windowID);
				break;
			}

		}

	}

	public void sleepInSecond(long timeInSecond) {
		try {
			Thread.sleep(timeInSecond * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void closeWindowTabExceptParent(String parentID) {
		// Lấy ra tất cả các ID của window/tab đamg có
		Set<String> allWindowsID = driver.getWindowHandles();

		// Duyệt qua từng ID
		for (String windows : allWindowsID) {
			if (!windows.equals(parentID)) {
				driver.switchTo().window(windows);
				// chỉ đóng tab/windows đang active (drive)
				driver.close();
				sleepInSecond(2);

			}
			if (driver.getWindowHandles().size() == 1) {
				driver.switchTo().window(parentID);
				break;

			}

		}

	}

	@AfterClass
	public void afterClass() {
		// không đóng window /tab => chỉ đóng brower
		driver.quit();
	}

}

////mỗi 1 tap /window sẽ có ID đại điện
////Lấy ra cái ID của tab/window  hiện tại đang active
//String currenWindownID = driver.getWindowHandle();//chọn cái trên
//System.out.println(currenWindownID);
//
//Set<String> windowIDs = driver.getWindowHandles();//chon set <String>
//System.out.println("Lan 1");
//
//for (String id : windowIDs) {
//	System.out.println(id);
//}
//driver.findElement(By.xpath("//a[text()='GOOGLE']")).click();
// windowIDs = driver.getWindowHandles();
// System.out.println("Lan 2");
//	
//	for (String id : windowIDs) {
//		System.out.println(id);
//	}
