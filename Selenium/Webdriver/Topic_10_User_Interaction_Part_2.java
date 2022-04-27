package Webdriver;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_10_User_Interaction_Part_2 {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	JavascriptExecutor jsExecutor;
	Actions action;
	String jsHelperPath = projectPath + "\\DragAnDrop\\drag_and_drop_helper.js";

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

		// dùng để thử cho các màn hình nhỏ.
//		driver.manage().window().setPosition(new Point(0, 0));
//		driver.manage().window().setSize(new Dimension(1366, 768));
		jsExecutor = (JavascriptExecutor) driver;

		action = new Actions(driver);

	}

	public void TC_07_Right_Click() {
		driver.get("http://swisnl.github.io/jQuery-contextMenu/demo.html");

		action.contextClick(driver.findElement(By.xpath("//span[text()='right click me']"))).perform();
		sleepInSecond(3);

		// verify Quit not contain (visible/ hover status)
		Assert.assertTrue(driver.findElement(By.xpath("//li[contains(@class,'context-menu-icon-quit')"
				+ " and not(contains(@class,'context-menu-visible')) and not(contains(@class,'context-menu-visible'))]"))
				.isDisplayed());

		// hover to quit
		action.moveToElement(driver.findElement(By.xpath("//li[contains(@class,'context-menu-icon-quit') "
				+ "and not(contains(@class,'context-menu-visible')) and not(contains(@class,'context-menu-visible'))]")))
				.perform();
		sleepInSecond(3);

		// verify Quit contain (visible/ hover status)
		Assert.assertTrue(driver.findElement(By.xpath("//li[contains(@class,'context-menu-icon-quit') "
				+ "and (contains(@class,'context-menu-visible')) and (contains(@class,'context-menu-visible'))]"))
				.isDisplayed());

		// Click to click
		driver.findElement(By.xpath("//li[contains(@class,'context-menu-icon-quit') "
				+ "and (contains(@class,'context-menu-visible')) and (contains(@class,'context-menu-visible'))]"))
				.click();
		sleepInSecond(3);

		// Verify alert displayed
		Assert.assertEquals(driver.switchTo().alert().getText(), "clicked: quit");

		driver.switchTo().alert().accept();

	}

	public void TC_08_Drag_Drop_HTML4() {
		driver.get("https://automationfc.github.io/kendo-drag-drop/");

		WebElement sourceCircle = driver.findElement(By.xpath("//div[@id='draggable']"));
		WebElement targetCircle = driver.findElement(By.xpath("//div[@id='droptarget']"));

//		scrollToElement(driver.findElement(By.xpath("//div[@class='kq-exmaple-runner tabstrip-container']")));
		sleepInSecond(5);

		action.dragAndDrop(sourceCircle, targetCircle).perform();

		Assert.assertEquals(targetCircle.getText(), "You did great!");
	}

	public void TC_09_Drap_Drop_HTML5_Jquery() throws IOException {
		driver.get("https://automationfc.github.io/drag-drop-html5/");

		String sourceRectangleCss = "#column-a";
		String targetRectangleCss = "#column-b";

		String jsHelperContent = getContentFile(jsHelperPath);

		jsHelperContent = jsHelperContent + "$(\"" + sourceRectangleCss + "\").simulateDragDrop({ dropTarget: \""
				+ targetRectangleCss + "\"});";
		jsExecutor.executeScript(jsHelperContent);
		sleepInSecond(2);

		Assert.assertTrue(driver.findElement(By.xpath("//div[@id='column-a']/header[text()='B']")).isDisplayed());

		jsExecutor.executeScript(jsHelperContent);
		sleepInSecond(2);
		Assert.assertTrue(driver.findElement(By.xpath("//div[@id='column-a']/header[text()='A']")).isDisplayed());

	}

	@Test
	public void TC_10_Drap_Drop_HTML5_Position() throws AWTException {
		driver.get("https://automationfc.github.io/drag-drop-html5/");
		sleepInSecond(2);

		dragAndDropHTML5ByOffset("//div[@id='column-a']", "//div[@id='column-b']");
		sleepInSecond(2);
		Assert.assertTrue(driver.findElement(By.xpath("//div[@id='column-a']/header[text()='B']")).isDisplayed());

		dragAndDropHTML5ByOffset("//div[@id='column-a']", "//div[@id='column-b']");
		sleepInSecond(2);
		Assert.assertTrue(driver.findElement(By.xpath("//div[@id='column-a']/header[text()='A']")).isDisplayed());

	}

	public void sleepInSecond(long timeInSecond) {
		try {
			Thread.sleep(timeInSecond * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String getContentFile(String filePath) throws IOException {
		Charset cs = Charset.forName("UTF-8");
		FileInputStream stream = new FileInputStream(filePath);
		try {
			Reader reader = new BufferedReader(new InputStreamReader(stream, cs));
			StringBuilder builder = new StringBuilder();
			char[] buffer = new char[8192];
			int read;
			while ((read = reader.read(buffer, 0, buffer.length)) > 0) {
				builder.append(buffer, 0, read);
			}
			return builder.toString();
		} finally {
			stream.close();
		}
	}

	public void dragAndDropHTML5ByOffset(String sourceLocator, String targetLocator) throws AWTException {

		WebElement source = driver.findElement(By.xpath(sourceLocator));
		WebElement target = driver.findElement(By.xpath(targetLocator));

		// Setup robot
		Robot robot = new Robot();
		robot.setAutoDelay(500);

		// Get size of elements
		Dimension sourceSize = source.getSize();
		Dimension targetSize = target.getSize();

		// Get center distance
		int xCentreSource = sourceSize.width / 2;
		int yCentreSource = sourceSize.height / 2;
		int xCentreTarget = targetSize.width / 2;
		int yCentreTarget = targetSize.height / 2;

		Point sourceLocation = source.getLocation();
		Point targetLocation = target.getLocation();
		System.out.println(sourceLocation.toString());
		System.out.println(targetLocation.toString());

		// Make Mouse coordinate center of element
		sourceLocation.x += 20 + xCentreSource;
		sourceLocation.y += 110 + yCentreSource;
		targetLocation.x += 20 + xCentreTarget;
		targetLocation.y += 110 + yCentreTarget;

		System.out.println(sourceLocation.toString());
		System.out.println(targetLocation.toString());

		// Move mouse to drag from location
		robot.mouseMove(sourceLocation.x, sourceLocation.y);

		// Click and drag
		robot.mousePress(InputEvent.BUTTON1_MASK);
		robot.mouseMove(((sourceLocation.x - targetLocation.x) / 2) + targetLocation.x,
				((sourceLocation.y - targetLocation.y) / 2) + targetLocation.y);

		// Move to final position
		robot.mouseMove(targetLocation.x, targetLocation.y);

		// Drop
		robot.mouseRelease(InputEvent.BUTTON1_MASK);
	}

//	public void scrollToElement(WebElement element) {
//		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", element);
//	}
	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
