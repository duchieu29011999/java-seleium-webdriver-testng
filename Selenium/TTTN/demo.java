package TTTN;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class demo {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	JavascriptExecutor jsExecutor;
	By loginButton = By.cssSelector(".fhs-btn-login");
	By loginUserName = By.cssSelector("#login_username");
	By loginPassWord = By.cssSelector("#login_password");
	By summerRadio = By.xpath("//input[@value='Summer']");
	By checkedCheckbox = By.xpath("//span[contains(text(),'Checked')]/preceding-sibling::span/input");
	By IndeterminateCheckbox = By.xpath("//span[contains(text(),'Indeterminate')]/preceding-sibling::span/input");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();

		jsExecutor = (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

	}

	@Test
	public void TC_01_Button() {
		// Step 01
		driver.get("https://www.fahasa.com/customer/account/create?attempt=2");

		// Step 02
		driver.findElement(By.cssSelector(".popup-login-tab-login")).click();

		// Step 03
		// Disabled field
		Assert.assertFalse(isElementEnabled(loginButton));

		// Step 04
		driver.findElement(loginUserName).sendKeys("hieu@gmail.com");
		driver.findElement(loginPassWord).sendKeys("123456");

		sleepInSecond(2);

		// Step 05
		// Enmabled field
		Assert.assertTrue(isElementEnabled(loginButton));

		// Step 06
		driver.navigate().refresh();

		driver.findElement(By.cssSelector(".popup-login-tab-login")).click();

		// Step 07
		removeDisabledAttributeByJS(loginButton);

		sleepInSecond(5);

		// Enabled field
		Assert.assertTrue(isElementEnabled(loginButton));

		// Step 08
		driver.findElement(loginButton).click();

		sleepInSecond(2);

		// Step 09
		Assert.assertEquals(driver
				.findElement(By.xpath(
						"//label[text()='Số điện thoại/Email']/following-sibling::div[@class='fhs-input-alert']"))
				.getText(), "Thông tin này không thể để trống");

		Assert.assertEquals(driver
				.findElement(By.xpath("//label[text()='Mật khẩu']/following-sibling::div[@class='fhs-input-alert']"))
				.getText(), "Thông tin này không thể để trống");

	}

	@Test
	public void TC_02_Default_Checkbox_Or_RadioButton() {
		// Step 01
		driver.get("https://www.fahasa.com/customer/account/create?attempt=2");

		// Step 02
		driver.findElement(By.cssSelector(".popup-login-tab-login")).click();

		// Step 03
		// Disabled field
		Assert.assertFalse(isElementEnabled(loginButton));

		// Step 04
		driver.findElement(loginUserName).sendKeys("hieu@gmail.com");
		driver.findElement(loginPassWord).sendKeys("123456");

		// Step 05
		driver.findElement(loginButton).click();

		sleepInSecond(2);

		// Step 06
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='fhs-popup-msg fhs-login-msg']")).getText(),
				"Số điện thoại/Email hoặc Mật khẩu sai!");

		// Step 07
		driver.navigate().refresh();

		driver.findElement(By.cssSelector(".popup-login-tab-login")).click();

		// Step 08
		driver.findElement(loginUserName).sendKeys("0388647614");
		driver.findElement(loginPassWord).sendKeys("181201020");

		driver.findElement(loginButton).click();

		// Step 09
		Assert.assertEquals(driver.findElement(By.xpath("//span[@class='member-value']")).getText(), "Hiếu Đức");

	}

	public void sleepInSecond(long timeInSecond) {
		try {
			Thread.sleep(timeInSecond * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public boolean isElementEnabled(By by) {
		if (driver.findElement(by).isEnabled()) {
			System.out.println("Element is Enabled");
			return true;
		} else {
			System.out.println("Element is not diEnabled");
			return false;
		}
	}

	public boolean isElementSelected(By by) {
		if (driver.findElement(by).isSelected()) {
			System.out.println("Element is Selected");
			return true;
		} else {
			System.out.println("Element is not de-selected");
			return false;
		}
	}

	public boolean isElementSelected(WebElement element) {
		if (element.isSelected()) {
			System.out.println("Element is Selected");
			return true;
		} else {
			System.out.println("Element is not de-selected");
			return false;
		}
	}

	public void checkToCheckboxorRadio(WebElement element) {
		if (!element.isSelected()) {
			element.click();
		}
	}

	public void UncheckToCheckbox(WebElement element) {
		if (element.isSelected()) {
			element.click();
		}
	}

	public void removeDisabledAttributeByJS(By by) {
		WebElement element = driver.findElement(by);
		jsExecutor.executeScript("arguments[0].removeAttribute('disabled')", element);// muốn xóa cái khác thì chuyền
																						// vafo (""
	}

	public void clickToElementByJS(WebElement element) {
		jsExecutor.executeScript("arguments[0].click();", element);
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
