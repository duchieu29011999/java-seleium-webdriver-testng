package Webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_15_Wait_Part_I {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	WebDriverWait explicitWait;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", projectPath + "\\browsers\\chromedriver.exe");
		driver = new ChromeDriver();
		explicitWait = new WebDriverWait(driver, 10);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	public void TC_01_Displayed_Visible() {
		driver.get("http://automationpractice.com/index.php?controller=authentication&back=my-account");

		// Wait cho element hiển thị /visible
		// Chờ cho email hiển thị trong 10s
		// Có hiển thị trên IU (User Interface)
		// Có hiển thị trong DOM (HTML)
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='email']")));

		// Failed trong 10s
		explicitWait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='create_account_error']")));

	}

	public void TC_02_Undisplayed_Invisible_In_Dom() {
		// Wait cho element không hiển thị (undisplayed /invisible)
		// không hiển thị trên iu -người đùng không nhìn thấy và thao tác được
		// TH1: element vẫn có trong DOM
		// Tìm Element đầu tiên:tìm ngay thấy ngay trong DOM và apply điều kiện luôn
		// Pass điều kiện luôn không cần chờ hết timeout
		explicitWait
				.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@id='create_account_error']")));

		// Failed trong 10s
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//input[@id='email']")));

	}

	public void TC_03_Undisplayed_Invisible_out_Dom() {
		// Wait cho element không hiển thị (undisplayed /invisible)
		// không hiển thị trên iu -người đùng không nhìn thấy và thao tác được
		// TH2: element không trong DOM
		// Tìm Element đầu tiên :không tìm thấy element và phải chờ(bị ảnh hưởng của
		// implicitlyWait)
		// implicitlyWait :timeout /wait để tìm elements(cơ chế là tìm đi tìm lại cho
		// hết thời gian timeout ->10s)
		// Mới apply điều kiện của explicitWait vào(implicitlyWait) ->Pass
		explicitWait
				.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@id='edit_account_error']")));

	}

	public void TC_04_Presence() {
		// wait cho element có trong DOM
		// TH1:Element có trong DOM +hiển thị trên UI
		explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.id("email")));
		// TH2:Element cos trong DOM +không hiển thị lên trên UI
		explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@id='create_account_error']")));

		// Failed trong 10s
		explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@id='edit_account_error']")));

	}

	public void TC_05_Clickable() {
		// Button /Radio/Checkbox/Link/Dropdown=> Stable trước khi thao tác
		// explicitWait.until(ExpectedConditions.elementToBeClickable(By.id("SubmitLogin")));

		// Failed trong 10s
		driver.get("https://login.mailchimp.com/signup/");
		driver.findElement(By.id("email")).sendKeys("duchieu@gmail.com");
		driver.findElement(By.id("new_username")).sendKeys("hieudeptrai");
		driver.findElement(By.id("new_password")).sendKeys("@Hieu181201020");
		explicitWait.until(ExpectedConditions.elementToBeClickable(By.id("create-account")));
		// chờ để Sign up hiện lên và click.mục đích là chờ các step tiếp theo hiển thị
		driver.findElement(By.id("create-account")).click();
	}

	@Test
	public void TC_6_Staleness() {
		driver.get("https://hoanghamobile.com/");
		// phủ đỉnh với presence:không được phép có trong DOM
		// Wait cho 1 element Stalensess
		// Không có/không còn trong DOM
		// Step 1 : thao tác với element =>Error message xuất hiện (*) - hiển thị

		explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[@id='popup-modal']")));
		WebElement popup = driver.findElement(By.xpath("//div[@id='popup-modal']"));
		
		// Step 2 :Thao tác và ....=>(*)không còn xuất hiện
		driver.findElement(By.xpath("//a[@class='close-modal icon-minutes']")).click();
		// Step 3: Cần chờ cho (*) không còn trong DOM nữa luôn
		explicitWait.until(ExpectedConditions.stalenessOf(popup));
		//không pass do //div[@id='popup-modal']" đóng vẫn còn trong DOM.chưa tìm nào trang web nào thích hợp

	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	// invisi

}
