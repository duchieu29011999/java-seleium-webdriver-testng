package Webdriver;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.By.ByCssSelector;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.testng.Assert.assertEquals;

public class Topic_12_IFrame {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	
	public void TC_01_IFrame() {
		driver.get("https://automationfc.com/2020/02/18/training-online-automation-testing/");

		// Switch vào iframe của facebook
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[contains(@title,'Facebook')]")));

		Assert.assertEquals(driver.findElement(By.xpath("//a[@title='Automation FC']")).getText(), "Automation FC");

		// Assert.assertEquals(driver.findElement(By.xpath("//a[@title=\"Automation
		// FC\"]/parent::div/following-sibling::div")), "3,211 likes");
		String likeText = driver.findElement(By.xpath("//a[@title='Automation FC']/parent::div/following-sibling::div"))
				.getText();
		int likeNumber = Integer.parseInt(likeText.split(" ")[0].replace(",", ""));
		System.out.println(likeNumber);

		assertThat(likeNumber, greaterThan(3200));

		// Switch to Top Windown
		driver.switchTo().defaultContent();

		Assert.assertEquals(driver.findElement(By.xpath("//h1[@class='post-title']")).getText(),
				"[Training Online] – Fullstack Selenium WebDriver Framework in Java (Livestream)");

		// Swithch to Top Facebbok
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[contains(@title,'Facebook')]")));

		Assert.assertEquals(driver.findElement(By.xpath("//a[@title='Automation FC']")).getText(), "Automation FC");

		
	}

	@Test
	public void TC_02_Homework_TC_07() {
		driver.get("https://kyna.vn/");

		// Switch to chat iframe
		driver.switchTo()
				.frame(driver.findElement(By.xpath("//div[@class='face-content']//following-sibling::iframe")));

		String likeText = driver.findElement(By.xpath("//a[@title='Kyna.vn']/parent::div/following-sibling::div"))
				.getText();
		int likeNumber = Integer.parseInt(likeText.split("K")[0].replace("", ""));
		System.out.println(likeNumber);

		assertThat(likeNumber, greaterThan(150));

		// Switch to Top Windows
		driver.switchTo().defaultContent();
	
	
		//step 4 5
//		// Switch to chat
//		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@id='cs_chat_iframe']")));
//		
//		driver.findElement(By.xpath("//input[@ng-model='login.username']")).sendKeys("Nguyeen Duc Hieu");
//		driver.findElement(By.xpath("//input[@ng-model='login.phone']")).sendKeys("0388647614");
//		driver.findElement(By.xpath("//textarea[@ng-model='login.content']")).sendKeys("Tester");
//		driver.findElement(By.xpath("//input[@value='Gửi tin nhắn']")).click();
//
//		Assert.assertEquals(driver.findElement(By.xpath(
//				"//select[@id='serviceSelect']/parent::div//following-sibling::div[contains(@class,' ng-binding')]"))
//				.getText(), "Bạn chưa chọn dịch vụ hỗ trợ");
		
		//step 6 7
		driver.findElement(By.xpath("//input[@id='live-search-bar']")).sendKeys("Excel");
		
		driver.findElement(By.xpath("//button[@class='search-button']")).click();
		

		List<WebElement> courseNameHeadre = driver.findElements(By.cssSelector("div.content h4"));
		
		List<String> couresNameText = new ArrayList<String>();
		
		for (WebElement courseElement : courseNameHeadre) {
			System.out.println(courseElement.getText());
			couresNameText.add(courseElement.getText());
		}
		for (String coursName : couresNameText) {
			Assert.assertTrue(coursName.contains("Excel"));
			
		}

	}
//	String  likes = "167K lượt thích";
//	String likeNumberString = likes.split("K")[0];
//	System.out.println(likeNumberString);

	public void sleepInSecond(long timeInSecond) {
		try {
			Thread.sleep(timeInSecond * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
