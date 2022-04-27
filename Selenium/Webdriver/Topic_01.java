package Webdriver;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_01 {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");

	//WebDriver driver;
	WebDriverWait explicitWait;
	Select select;
	JavascriptExecutor jsExecutor;

	@BeforeClass
	public void beforeClass() {
//		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
//		driver = new FirefoxDriver();
		
		System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();

		explicitWait = new WebDriverWait(driver, 30);
		jsExecutor =(JavascriptExecutor) driver;
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		//driver.get("https://www.facebook.com/");
	}


	@Test
	public void TC_01_JQuery() {
		driver.get("https://jqueryui.com/resources/demos/selectmenu/default.html");

		// 5
		selectItemCustomDropdown("//span[@id='number-button']", "//ul[@id='number-menu']//div", "5");
		sleepInSecond(2);
		// 10
		selectItemCustomDropdown("//span[@id='number-button']", "//ul[@id='number-menu']//div", "10");
		sleepInSecond(2);
		// 8
		selectItemCustomDropdown("//span[@id='number-button']", "//ul[@id='number-menu']//div", "8");
		sleepInSecond(2);
		// 18
		selectItemCustomDropdown("//span[@id='number-button']", "//ul[@id='number-menu']//div", "18");
		sleepInSecond(2);

	}

	public void TC_02_Angular() {
		driver.get("https://ej2.syncfusion.com/angular/demos/?_ga=2.262049992.437420821.1575083417-524628264.1575083417#/material/drop-down-list/data-binding");
		
		selectItemCustomDropdown("//ejs-dropdownlist[@id='games']//span[contains(@class,'e-search-icon')]", "//div[@id='games_popup']//li", "Badminton");
		sleepInSecond(3);
		
		//getText
		//Assert.assertEquals(driver.findElement(By.xpath("//select[@name='games']/option")).getText(), "Badminton");
		
		//Displayed

		//Assert.assertTrue(driver.findElement(By.xpath("//select[@name='games']/option[text()='Badminton']")).isDisplayed());
		
		//Select (getFirstSelectOption().getText())
		//select = new Select(driver.findElement(By.xpath("//select[@name='games']")));
		//Assert.assertEquals(select.getFirstSelectedOption().getText(), "Badminton");
		
		//cả 3 đều không chạy được .phải dùng đến hàm của javaSCRip và dom để giải quy
		
		Assert.assertEquals(getAngularSeletedValueByJS(), "Badminton");
		
		selectItemCustomDropdown("//ejs-dropdownlist[@id='games']//span[contains(@class,'e-search-icon')]", "//div[@id='games_popup']//li", "Golf");
		sleepInSecond(3);
		
		selectItemCustomDropdown("//ejs-dropdownlist[@id='games']//span[contains(@class,'e-search-icon')]", "//div[@id='games_popup']//li", "Snooker");
		sleepInSecond(3);
		
	}

	@Test
	public void TC_03_React() {
		driver.get("https://react.semantic-ui.com/maximize/dropdown-example-selection/");
		
		selectItemCustomDropdown("//div[@role='listbox']//i", "//div[@role='listbox']//span[@class='text']", "Elliot Fu");
		sleepInSecond(2);
		Assert.assertTrue(driver.findElement(By.xpath("//div[text()='Elliot Fu']")).isDisplayed());
		
		selectItemCustomDropdown("//div[@role='listbox']//i", "//div[@role='listbox']//span[@class='text']", "Matt");
		sleepInSecond(2);
		Assert.assertTrue(driver.findElement(By.xpath("//div[text()='Matt']")).isDisplayed());
		
		selectItemCustomDropdown("//div[@role='listbox']//i", "//div[@role='listbox']//span[@class='text']", "Christian");
		sleepInSecond(2);
		Assert.assertTrue(driver.findElement(By.xpath("//div[text()='Christian']")).isDisplayed());
	}

	@Test
	public void TC_04_VueJS() {
		driver.get("https://mikerodham.github.io/vue-dropdowns/");
		
		selectItemCustomDropdown("//li[@class='dropdown-toggle']", "//ul[@class='dropdown-menu']//a", "First Option");
		sleepInSecond(2);
		Assert.assertEquals(driver.findElement(By.xpath("//li[@class='dropdown-toggle']")).getText().trim(), "First Option");
		
		selectItemCustomDropdown("//li[@class='dropdown-toggle']", "//ul[@class='dropdown-menu']//a", "Second Option");
		sleepInSecond(2);
		Assert.assertEquals(driver.findElement(By.xpath("//li[@class='dropdown-toggle']")).getText().trim(), "Second Option");
		
		selectItemCustomDropdown("//li[@class='dropdown-toggle']", "//ul[@class='dropdown-menu']//a", "Third Option");
		sleepInSecond(2);
		Assert.assertEquals(driver.findElement(By.xpath("//li[@class='dropdown-toggle']")).getText().trim(), "Third Option");
		
	}
	@Test
	public void TC_05_Editable() {
		driver.get("https://react.semantic-ui.com/maximize/dropdown-example-search-selection/");
		
		selectItemEditDropdown("//input[@class='search']", "//div[@role='combobox']//span[@class='text']", "Algeria");
		sleepInSecond(2);
		Assert.assertTrue(driver.findElement(By.xpath("//div[text()='Algeria']")).isDisplayed());
		
		selectItemEditDropdown("//input[@class='search']", "//div[@role='combobox']//span[@class='text']", "Belgium");
		sleepInSecond(2);
		Assert.assertTrue(driver.findElement(By.xpath("//div[text()='Belgium']")).isDisplayed());
		
		selectItemEditDropdown("//input[@class='search']", "//div[@role='combobox']//span[@class='text']", "Benin");
		sleepInSecond(2);
		Assert.assertTrue(driver.findElement(By.xpath("//div[text()='Benin']")).isDisplayed());	
	}
	
	public void selectItemEditDropdown(String parentXpath, String allItemXpanth, String expectedValueItem) {
		// 1-Input vào 1 element(textbox) bất kì của dropdown để nó xở hết tất cả các item ra
		driver.findElement(By.xpath(parentXpath)).sendKeys(expectedValueItem);
		sleepInSecond(1);
		// 2-Chở cho all item được load lên
		// ExpectedConditions chọn class
		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(allItemXpanth)));

		// 3-Lưu nó vào list chứa trong chứa nhưng element
		List<WebElement> allItems = driver.findElements(By.xpath(allItemXpanth));
		int allItemsNumber = allItems.size();
		// 19 Item

		// 4-Lấy ra text của từng element
		// Tradition for
		for (int i = 0; i < allItemsNumber; i++) {
			String actualValueItem = allItems.get(i).getText().trim();
			System.out.println(actualValueItem);
			// 5-Kiếm tra có bằng text cần tìm hay không
			if (actualValueItem.equals(expectedValueItem)) {
				// 6-Nếu như có click vào -thoát khỏi vòng lăpj
				allItems.get(i).click();
				break;
			}
//			if (allItems.get(5).getText().equals("")) {
//
//			}
			// Nếu như không có thì tiếp tục duyệt nhưng item khác cho đến khi hết all item

		}
//		// For -Each
//		for (WebElement item : allItems) {
//			if (item.getText().equals(expectedValueItem)) {
//				item.click();
//				break;
//			}
//		}

	}

	public void selectItemCustomDropdown(String parentXpath, String allItemXpanth, String expectedValueItem) {
		// 1-Click vào 1 element bất kì của dropdown để nó xở hết tất cả các item ra
		driver.findElement(By.xpath(parentXpath)).click();
		sleepInSecond(1);
		// 2-Chở cho all item được load lên
		// ExpectedConditions chọn class
		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(allItemXpanth)));

		// 3-Lưu nó vào list chứa trong chứa nhưng element
		List<WebElement> allItems = driver.findElements(By.xpath(allItemXpanth));
		int allItemsNumber = allItems.size();
		// 19 Item

		// 4-Lấy ra text của từng element
		// Tradition for
		for (int i = 0; i < allItemsNumber; i++) {
			String actualValueItem = allItems.get(i).getText().trim();
			System.out.println(actualValueItem);
			// 5-Kiếm tra có bằng text cần tìm hay không
			if (actualValueItem.equals(expectedValueItem)) {
				// 6-Nếu như có click vào -thoát khỏi vòng lăpj
				allItems.get(i).click();
				break;
			}
//			if (allItems.get(5).getText().equals("")) {
//
//			}
			// Nếu như không có thì tiếp tục duyệt nhưng item khác cho đến khi hết all item

		}
//		// For -Each
//		for (WebElement item : allItems) {
//			if (item.getText().equals(expectedValueItem)) {
//				item.click();
//				break;
//			}
//		}

	}

	public String getAngularSeletedValueByJS() {
		return (String) jsExecutor.executeScript("return document.querySelector(\"$(\"select[name='game'] option\").text");	
	}
	
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
