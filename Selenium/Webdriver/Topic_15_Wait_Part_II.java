package Webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Sleeper;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_15_Wait_Part_II {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", projectPath + "\\browsers\\chromedriver.exe");
		driver = new ChromeDriver();

		// Timeout này apply duy nhất cho việc tìm elemet (findElement/findElements)
		// Nếu như không set thì timeout = 0
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("https://demo.nopcommerce.com/register?returnUrl=%2F");
	}
	// implicitlyWait :

	public void TC_01_Find_Element() {
		// Trả về (return) 1 element (WebElement)

		// Tìm element nhưng không thấy elment nào hết->0 matching node
		// Mỗi nửa giây sẽ tìm lại 1 lần cho đến khi hết timeout thì thôi.
		// Nếu như hết timeout mà vẫn không thấy element thì:
		// +Đánh fail testcase
		// +Throw(ném ra)1 exception:NoSuchElement-Unable to local element with....
		// trong tg đang tìm nếu element xuất hiện thì pass được điều kiện không cần
		// phải chờ cho đến hết timeout

		// driver.findElement(By.xpath("//input [@id='FirstName']"));

//		if(driver.findElement(By.xpath("//input [@id='FirstName']")).isDisplayed()) {
//			System.out.println("Go to if");
//		}else {
//			System.out.println("Go to else");
//		}

		// Tìm element nhưng chỉ có duy nhất 1 cái ->1 matching node
		// Tìm thấy element thấy ngay ->Return lại element đó =>
		// Không cần chờ đến timeout còn lại
		//
		boolean status = driver.findElement(By.xpath("//input [@id='Email']")).isDisplayed();
		System.out.println(status);

		if (status) {
			System.out.println("Go to if");
		} else {
			System.out.println("Go to else");
		}

		// Tìm element nhưng chỉ có nhiều hơn 1 cái ->n matching node
		driver.findElement(By.xpath("//input [@type='email']")).sendKeys("duchieu2901");

	}

	@Test
	public void TC_02_Find_Elements() {
		// Trả về (return) >= 1 elements (List <WebElement>)

		// Tìm element nhưng không thấy elment nào hết->0 matching node
		// Tìm element nhưng không thấy elment nào hết->0 matching node
		// Mỗi nửa giây sẽ tìm lại 1 lần cho đến khi hết timeout thì thôi.
		// Nếu như hết timeout mà vẫn không thấy element thì:
		// +Không đánh fail testcase
		// +Trả về 1 cái list empty (không chứa element nào hết)

		// List<WebElement> radionButton =
		// driver.findElements(By.xpath("//input[@type='radio']"));
		// System.out.println("Size = " + radionButton.size());
		// Assert.assertTrue(radionButton.isEmpty());

		// Tìm element nhưng chỉ có duy nhất 1 cái ->1 matching node
		// Tìm thấy element ngay nhưng kết quả trả về là 1 list chứa Element (1 element

		// List<WebElement> emailTextBox = driver.findElements(By.xpath("//input
		// [@id='Email']"));
		// System.out.println("Size = " + emailTextBox.size());
		// Assert.assertFalse(emailTextBox.isEmpty());
		// IsEmpty():kiểm tra một chuỗi nào đó có tồn tại phần tử không.
		// trả về theo giá trị true và flase

		// emailTextBox.get(0).sendKeys("hieu2@gmail.com");

		// Tìm element nhưng chỉ có nhiều hơn 1 cái ->n matching node
		// Tìm thấy element ngay nhưng kết quả trả về là 1 list chứa nhiều element
		// Cần thao tác nhiều field/elements

		List<WebElement> textboxes = driver.findElements(By.xpath(" //input[@type='text']"));
		System.out.println("Size " + textboxes.size());
		for (WebElement textbox : textboxes) {
			textbox.sendKeys("List WebElement");

		}

	}

	public void TC_03() {

	}

	@AfterClass
	public void afterClass() {
		// driver.quit();
	}
	// findElement
	// findElements
	// Tìm element nhưng không thấy elment nào hết->0 matching node
	// =>Kết quả trả về là gì?
	// Kq:hàm findElement:

	// Tìm element nhưng chỉ có duy nhất 1 cái ->1 matching node
	// =>Kết quả trả về là gì?
	// Tìm element nhưng chỉ có nhiều hơn 1 cái ->n matching node
	// =>Kết quả trả về là gì?

	// Hai hàm này đều bị ảnh hưởng bởi timeout của imolicitWait

}
