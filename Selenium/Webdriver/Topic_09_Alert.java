package Webdriver;


import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_09_Alert {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	Alert alert;
	WebDriverWait explicitWait;
	By resultText = By.xpath("//p[@id=\"result\"]");
	String username = "admin";
	String passwork = "admin";
	String oldUrl = "http://the-internet.herokuapp.com/basic_auth";
	String chormeAuthenFile = projectPath + "\\autoIT\\authen_chrome.exe";

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", projectPath + "\\browsers\\chromedriver.exe");
		driver = new ChromeDriver();

		explicitWait = new WebDriverWait(driver, 30);

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

	}

	public void TC_01_Accept_Alert() {
		driver.get("https://automationfc.github.io/basic-form/index.html");

		driver.findElement(By.xpath("//button[text()=\"Click for JS Alert\"]")).click();

		// switch vào alert
		alert = driver.switchTo().alert();

		
		sleepInSecond(3);

		Assert.assertEquals(alert.getText(), "I am a JS Alert");

		alert.accept();

		sleepInSecond(3);

		Assert.assertEquals(driver.findElement(resultText).getText(), "You clicked an alert successfully");

	}

	public void TC_02_Confirm_Alert() {
		driver.get("https://automationfc.github.io/basic-form/index.html");

		driver.findElement(By.xpath("//button[text()=\"Click for JS Confirm\"]")).click();

		alert = driver.switchTo().alert();

		sleepInSecond(3);

		Assert.assertEquals(alert.getText(), "I am a JS Confirm");

		alert.dismiss();// chonj vao cancel

		sleepInSecond(3);

		Assert.assertEquals(driver.findElement(resultText).getText(), "You clicked: Cancel");
	}

	public void TC_03_Confirm_Alert() {
		String loginValue = "Tester";

		driver.get("https://automationfc.github.io/basic-form/index.html");

		driver.findElement(By.xpath("//button[text()=\"Click for JS Prompt\"]")).click();

		alert = driver.switchTo().alert();

		alert.sendKeys(loginValue);

		sleepInSecond(3);

		Assert.assertEquals(alert.getText(), "I am a JS prompt");

		alert.accept();

		sleepInSecond(3);

		Assert.assertEquals(driver.findElement(resultText).getText(), "You entered: " + loginValue);

	}

	public void TC_04_Authentication_Alert_01() {
		driver.get("http://" + username + ":" + passwork + "@" + "the-internet.herokuapp.com/basic_auth");
		// http://admin:admin@the-internet.herokuapp.com/basic_auth
		Assert.assertTrue(driver
				.findElement(By.xpath("//p[contains(text(),'Congratulations! You must have the proper credentials.')]"))
				.isDisplayed());

	}

	public void TC_05_Authentication_Alert_02() {
		driver.get("http://the-internet.herokuapp.com");

		String basicAuthentication = driver.findElement(By.xpath("//a[text()=\"Basic Auth\"]")).getAttribute("href");
		// lấy link :http://the-internet.herokuapp.com/basic_auth
		System.out.println(basicAuthentication);
		// Input :http://the-internet.herokuapp.com/basic_auth
		// Output:http://admin:admin@the-internet.herokuapp.com/basic_auth
		driver.get(getAutheticattionUrl(basicAuthentication, username, passwork));
//		driver.get(getAutheticattionUrl(basicAuthentication, "admin", "admin"));

		Assert.assertTrue(driver
				.findElement(By.xpath("//p[contains(text(),'Congratulations! You must have the proper credentials.')]"))
				.isDisplayed());

	}

	@Test
	public void TC_06_Authentication_Alert_02_AutoIT() throws IOException {
		Runtime.getRuntime().exec(new String[] {chormeAuthenFile,username,passwork});
		
		driver.get("http://the-internet.herokuapp.com/basic_auth");
		
		Assert.assertTrue(driver
				.findElement(By.xpath("//p[contains(text(),'Congratulations! You must have the proper credentials.')]"))
				.isDisplayed());


	}

	public String getAutheticattionUrl(String url, String username, String passwork) {

		String urlValue[] = oldUrl.split("//");

		url = urlValue[0] + "//" + username + ":" + passwork + "@" + urlValue[1];

		return url;
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
