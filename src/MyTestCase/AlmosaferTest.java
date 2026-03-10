package MyTestCase;

import java.awt.RenderingHints.Key;
import java.time.Duration;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class AlmosaferTest {
	WebDriver driver = new EdgeDriver();
	String MyWebSite = "https://sa.almosafer.com/";
	Random rand = new Random();

	@BeforeTest
	public void MySetup() {
		driver.get(MyWebSite);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
	}

	@Test(priority = 1, enabled = true)
	public void ClosePopUP() {

		try {
			WebElement ContinueButton = driver.findElement(By.id("mui-5"));
			if (ContinueButton.isDisplayed()) {
				ContinueButton.click();
				Assert.assertFalse(driver.findElement(By.id("mui-5")).isDisplayed());
			}
		} catch (Exception e) {
			System.out.println("Popup not found, continuing...");
		}

	}

	@Test(priority = 2, enabled = false)
	public void ChangeLanguage() throws InterruptedException {
		WebElement BurgerMenu = driver.findElement(By.xpath("//div[@data-testid='leading__icon']"));
		BurgerMenu.click();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0,document.body.scrollHeight)");
		Thread.sleep(3000);
		WebElement ArabicLanguage = driver.findElement(By.xpath("//div[text()='عربي']"));
		ArabicLanguage.click();
		Assert.assertEquals(driver.findElement(By.tagName("html")).getAttribute("lang"), "ar");
		WebElement EnglishLanguage = driver.findElement(By.cssSelector("[data-testid='change_language']"));
		EnglishLanguage.click();
		Assert.assertEquals(driver.findElement(By.tagName("html")).getAttribute("lang"), "en");
	}

	@Test(priority = 3, enabled = true)
	public void SearchForFlight() throws InterruptedException {
		String[] countries = { "je", "ir", "sy" };
		String[] countries2 = { "jor", "aus", "sud" };
		int randomeIndex = rand.nextInt(countries.length);
		int randomeIndex1 = rand.nextInt(countries2.length);
		String country = countries[randomeIndex];
		String country2 = countries2[randomeIndex];
		// Origin
		driver.findElement(By.name("origin")).sendKeys(country);
		Thread.sleep(1000);
		driver.findElement(By.name("origin")).sendKeys(Keys.ARROW_DOWN, Keys.ENTER);
		// Destination
		driver.findElement(By.name("destination")).sendKeys(country2);
		Thread.sleep(1000);
		driver.findElement(By.name("destination")).sendKeys(Keys.ARROW_DOWN, Keys.ENTER);

	}

	@Test(priority = 4, enabled = true)
	public void SetDate1() throws InterruptedException {
		WebElement checkIn = driver.findElement(By.id("testIdPickerPrefix__DatePicker__DepartureDate"));
		checkIn.click();

		Thread.sleep(1000);
		driver.findElement(By.xpath("//button[@data-testid='FlightSearchCalendar__2026-03-13']")).click();
		Thread.sleep(500);
		boolean flag = true;
		while (flag) {
			String caption = driver.findElement(By.id("react-day-picker-2")).getText();
			if (caption.contains("February") && caption.contains("2027")) {
				flag = false;
				break;
			} else {
				WebElement nextButton = driver.findElement(By.xpath("//button[@name='next-month']"));
				nextButton.click();
				Thread.sleep(1000);
			}
		}
		driver.findElement(By.xpath("//button[@data-testid='FlightSearchCalendar__2027-02-25']")).click();
		Thread.sleep(200);
	}

}
