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

public class Topic_15_Wait_Part_III {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", projectPath + "\\browsers\\chromedriver.exe");
		driver = new ChromeDriver();

		driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
		driver.get("https://the-internet.herokuapp.com/dynamic_loading/2");
	}
	// implicitlyWait :

	@Test
	public void TC_01_Find_Element() {
		// Click to Start
		driver.findElement(By.xpath("//div [@id='start']/button")).click();

		// Loanding icon 5s
		// Verify text
		Assert.assertEquals(driver.findElement(By.xpath("//div [@id='finish']/h4")).getText(), "Hello World!");

	}

	
	public void TC_02_Find_Elements() {

	}

	public void TC_03() {

	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
//
