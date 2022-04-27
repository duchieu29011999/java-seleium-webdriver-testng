package Webdriver;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import javax.swing.text.html.HTML;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class Topic_14_javascirp_Executor_01 {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	JavascriptExecutor jsExecutor;
	String html5Message;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", projectPath + "\\broewserss\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		jsExecutor = (JavascriptExecutor) driver;
	}

	
	public void TC_01_Browser_Elememt() {
		navigateToUrlByJS("http://live.techpanda.org/");

		String techpanda = (String) executeForBrowser("return document.domain");
		System.out.println("Live Guru Domaine = " + techpanda);
		Assert.assertEquals(techpanda, "live.techpanda.org");

		String homePagerURl = (String) executeForBrowser("return document.URL");
		System.out.println("Home Page URL = " + homePagerURl);
		assertEquals(homePagerURl, "http://live.techpanda.org/");

		hightlightElement("//a[text()='Mobile']");
		clickToElementByJS("//a[text()='Mobile']");

		hightlightElement("//a[text()=\"Samsung Galaxy\"]/parent::h2/following-sibling::div[@class='actions']/button");
		clickToElementByJS("//a[text()=\"Samsung Galaxy\"]/parent::h2/following-sibling::div[@class='actions']/button");

		String innerText = getInnerText();

		Assert.assertTrue(innerText.contains("Samsung Galaxy was added to your shopping cart."));

		assertTrue(areExpectedTextInInnerText("Samsung Galaxy was added to your shopping cart."));

		hightlightElement("//a[text()='Customer Service']");
		clickToElementByJS("//a[text()='Customer Service']");

		String CustomerService = (String) executeForBrowser("return document.title");
		System.out.println("Customer Service title  = " + CustomerService);
		assertEquals(CustomerService, "Customer Service");

		hightlightElement("//input[@id='newsletter']");
		scrollToElementOnTop("//input[@id='newsletter']");
		sleepInSecond(3);

		hightlightElement("//input[@id='newsletter']");
		sendkeyToElementByJS("//input[@id='newsletter']", generateEmail());
		sleepInSecond(3);

		clickToElementByJS("//button[@class='button']");

		Assert.assertTrue(getInnerText().contains("Thank you for your subscription."));

		navigateToUrlByJS("https://demo.guru99.com/v4/");

		Assert.assertEquals(executeForBrowser("return document.domain;"), "demo.guru99.com");

	}

	@Test
	public void TC_02_Validation_Message() {
		driver.get("https://automationfc.github.io/html5/index.html");
		
		driver.findElement(By.name("submit-btn")).click();
		sleepInSecond(1);
		html5Message = getElementValuedationMessage("//input[@id='fname']");
		Assert.assertEquals(html5Message, "Please fill out this field.");
		System.out.println(html5Message);
		
		driver.findElement(By.id("fname")).sendKeys("Duc Hieu");
		driver.findElement(By.name("submit-btn")).click();
		sleepInSecond(1);
		html5Message = getElementValuedationMessage("//input[@id='pass']");
		Assert.assertEquals(html5Message, "Please fill out this field.");
		System.out.println(html5Message);
		
		driver.findElement(By.id("pass")).sendKeys("123456");
		driver.findElement(By.name("submit-btn")).click();
		sleepInSecond(1);
		html5Message = getElementValuedationMessage("//input[@id='em']");
		Assert.assertEquals(html5Message, "Please fill out this field.");
		System.out.println(html5Message);
		
		driver.findElement(By.id("em")).sendKeys("132@435sada");
		driver.findElement(By.name("submit-btn")).click();
		sleepInSecond(1);
		html5Message = getElementValuedationMessage("//input[@id='em']");
		Assert.assertEquals(html5Message, "Please match the requested format.");
		System.out.println(html5Message);
		
		driver.findElement(By.id("em")).clear();
		driver.findElement(By.id("em")).sendKeys("132@435sada@123");
		driver.findElement(By.name("submit-btn")).click();
		sleepInSecond(1);
		html5Message = getElementValuedationMessage("//input[@id='em']");
		Assert.assertEquals(html5Message, "A part following '@' should not contain the symbol '@'.");
		System.out.println(html5Message);
		
		driver.findElement(By.id("em")).clear();
		driver.findElement(By.id("em")).sendKeys("hieu@gmail.com");
		driver.findElement(By.name("submit-btn")).click();
		sleepInSecond(1);
		html5Message = getElementValuedationMessage("//select");
		Assert.assertEquals(html5Message,  "Please select an item in the list.");
		System.out.println(html5Message);
	}

	@Test
	public void TC_03() {

	}

	// String domain = (String) jsExecutor.executeScript("return document.domain");
	// get ra domain cuar trang web
	public Object executeForBrowser(String javaScript) {
		return jsExecutor.executeScript(javaScript);
	}

	public String getInnerText() {
		return (String) jsExecutor.executeScript("return document.documentElement.innerText;");
	}

	// kiếm tra đoạn text có trong
	public boolean areExpectedTextInInnerText(String textExpected) {
		String textActual = (String) jsExecutor
				.executeScript("return document.documentElement.innerText.match('" + textExpected + "')[0];");
		return textActual.equals(textExpected);
	}

	// chạy đến cuối trang
	public void scrollToBottomPage() {
		jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
	}

	// chuyển trang
	public void navigateToUrlByJS(String url) {
		jsExecutor.executeScript("window.location = '" + url + "'");
	}

	public void hightlightElement(String locator) {
		WebElement element = getElement(locator);
		
		//Luư lại trạng thái trước khi highlight
		String originalStyle = element.getAttribute("style");
		jsExecutor.executeScript("arguments[0].setAttribute('style', arguments[1])", element,
				"border: 2px solid red; border-style: dashed;");
		sleepInSecond(1);
		jsExecutor.executeScript("arguments[0].setAttribute('style', arguments[1])", element, originalStyle);
	}

	public void clickToElementByJS(String locator) {
		jsExecutor.executeScript("arguments[0].click();", getElement(locator));
		// getElement(locator = driver.findElement(By.xpath(locator));
	}

	public void scrollToElementOnTop(String locator) {
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", getElement(locator));
	}

	public void scrollToElementOnDown(String locator) {
		jsExecutor.executeScript("arguments[0].scrollIntoView(false);", getElement(locator));
	}

	public void sendkeyToElementByJS(String locator, String value) {
		jsExecutor.executeScript("arguments[0].setAttribute('value', '" + value + "')", getElement(locator));
	}

	public void removeAttributeInDOM(String locator, String attributeRemove) {
		jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", getElement(locator));
	}

	public String getElementValidationMessage(String locator) {
		return (String) jsExecutor.executeScript("return arguments[0].validationMessage;", getElement(locator));
	}
	
	public String getElementValuedationMessage(String locator) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		WebElement element = getElement(locator);
		return (String) jsExecutor.executeScript(" return arguments[0].validationMessage;", element);
	}


	public boolean isImageLoaded(String locator) {
		boolean status = (boolean) jsExecutor.executeScript(
				"return arguments[0].complete && typeof arguments[0].naturalWidth != 'undefined' && arguments[0].naturalWidth > 0",
				getElement(locator));
		if (status) {
			return true;
		}
		return false;
	}

	public WebElement getElement(String locator) {
		return driver.findElement(By.xpath(locator));
	}

	public boolean areJQueryAndJSLoadedSuccess(WebDriver driver) {
		WebDriverWait explicitWait = new WebDriverWait(driver, 30);
		ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				try {
					return ((Long) ((JavascriptExecutor) driver).executeScript("return jQuery.active") == 0);
				} catch (Exception e) {
					return true;
				}
			}
		};
		ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				return ((JavascriptExecutor) driver).executeScript("return document.readyState").toString()
						.equals("complete");
			}
		};
		return explicitWait.until(jQueryLoad) && explicitWait.until(jsLoad);
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
	
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
