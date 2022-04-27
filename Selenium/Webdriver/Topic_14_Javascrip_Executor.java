package Webdriver;

import java.util.Random;
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

public class Topic_14_Javascrip_Executor {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	JavascriptExecutor jsExecutor;

	String email, userID, password, loginPageUrl, CustomeID;
	String name, doInput, doOutput, address, city, state, pin, phone;

	By nameBy = By.name("name");
	By dobBy = By.name("dob");
	By addrBy = By.name("addr");
	By cityBy = By.name("city");
	By stateBy = By.name("state");
	By pinnoBy = By.name("pinno");
	By telephonenoBy = By.name("telephoneno");
	By emailidBy = By.name("emailid");
	By passwordBy = By.name("password");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", projectPath + "\\browsers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

		jsExecutor = (JavascriptExecutor) driver;

		driver.get("http://demo.guru99.com/v4");
		name = "Donald Truck";
		doInput = "04/05/1958";
		doOutput = "1958-04-05";
		address = "912 Vilagge Centen";
		city = "Saint Louis";
		state = "Missoiri";
		pin = "631433";
		phone = "3148386567";
		email = generateEmail();

		loginPageUrl = driver.getCurrentUrl();
		driver.findElement(By.xpath("//a[text()=\"here\"]")).click();

		driver.findElement(By.name("emailid")).sendKeys(email);

		driver.findElement(By.name("btnLogin")).click();

		userID = driver.findElement(By.xpath("//td[text()=\"User ID :\"]/following-sibling::td")).getText();
		password = driver.findElement(By.xpath("//td[text()=\"Password :\"]/following-sibling::td")).getText();

		driver.get(loginPageUrl);

		driver.findElement(By.name("uid")).sendKeys(userID);
		driver.findElement(By.name("password")).sendKeys(password);

		driver.findElement(By.xpath("//input[@type='submit']")).click();

		Assert.assertEquals(driver.findElement(By.xpath("//marquee[@class='heading3']")).getText(),
				"Welcome To Manager's Page of Guru99 Bank");
	}

	@Test
	public void TC_01_Remove_Attibute() {
		
		driver.findElement(By.xpath("//a[text()=\"New Customer\"]")).click();

		driver.findElement(nameBy).sendKeys(name);
		
		removeAttributeInDOM("//input[@id='dob']", "type");
		driver.findElement(dobBy).sendKeys(doOutput);
		driver.findElement(addrBy).sendKeys(address);
		driver.findElement(cityBy).sendKeys(city);
		driver.findElement(stateBy).sendKeys(state);
		driver.findElement(pinnoBy).sendKeys(pin);
		driver.findElement(telephonenoBy).sendKeys(phone);
		driver.findElement(emailidBy).sendKeys(email);
		driver.findElement(passwordBy).sendKeys(password);

		driver.findElement(By.name("sub")).click();
		// Thread.sleep(10000);

		Assert.assertEquals(driver.findElement(By.className("heading3")).getText(),
				"Customer Registered Successfully!!!");
		Assert.assertEquals(
				driver.findElement(By.xpath("//td[text()=\"Customer Name\"]/following-sibling::td")).getText(), name);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()=\"Birthdate\"]/following-sibling::td")).getText(),
				doOutput);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()=\"Address\"]/following-sibling::td")).getText(),
				address);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()=\"City\"]/following-sibling::td")).getText(),
				city);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()=\"State\"]/following-sibling::td")).getText(),
				state);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()=\"Pin\"]/following-sibling::td")).getText(), pin);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()=\"Mobile No.\"]/following-sibling::td")).getText(),
				phone);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()=\"Email\"]/following-sibling::td")).getText(),
				email);

	}

	@Test
	public void TC_02_Create_An_Account() {
		driver.get("http://live.techpanda.org/");
		
		clickToElementByJS("//div[@id='header-account']//a[@title='My Account']");
		sleepInSecond(3);
		

	}

	public void hightlightElement(String locator) {
		WebElement element = getElement(locator);
		String originalStyle = element.getAttribute("style");
		jsExecutor.executeScript("arguments[0].setAttribute('style', arguments[1])", element,
				"border: 2px solid red; border-style: dashed;");
		sleepInSecond(1);
		jsExecutor.executeScript("arguments[0].setAttribute('style', arguments[1])", element, originalStyle);
	}

	public WebElement getElement(String locator) {
		return driver.findElement(By.xpath(locator));
	}

	public void removeAttributeInDOM(String locator, String attributeRemove) {
		jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", getElement(locator));
	}

	public void sleepInSecond(long timeInSecond) {
		try {
			Thread.sleep(timeInSecond * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String generateEmail() {
		Random rand = new Random();
		return "hieudeptrai" + rand.nextInt(9999) + "@github.io";
	}
	public void clickToElementByJS(String locator) {
		jsExecutor.executeScript("arguments[0].click();", getElement(locator));
		// getElement(locator = driver.findElement(By.xpath(locator));
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
