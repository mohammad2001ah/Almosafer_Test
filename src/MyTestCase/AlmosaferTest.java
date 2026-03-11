package MyTestCase;

import java.awt.RenderingHints.Key;
import java.nio.Buffer;
import java.time.Duration;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class AlmosaferTest {
	WebDriver driver = new EdgeDriver();
//	WebDriver driver = new ChromeDriver();
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

	@Test(priority = 3, enabled = false)
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

	@Test(priority = 4, enabled = false)
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

	@Test(priority = 5, enabled = true)
	public void HotelSearch() throws InterruptedException {
		WebElement HotelButton = driver.findElement(By.id("tab-hotels"));
		HotelButton.click();
		String[] Place = { "amman", "dubai", "jeddah", "muscat" };
		int randomIndex = rand.nextInt(Place.length);
		String country = Place[randomIndex];
		driver.findElement(By.id("DesktopSearchWidget_Destination_InputField_Test_Id")).sendKeys(country);
		Thread.sleep(2500);
		driver.findElement(By.id("DesktopSearchWidget_Destination_InputField_Test_Id")).sendKeys(Keys.ARROW_DOWN,
				Keys.ENTER);
		driver.findElement(By.id("DesktopSearchWidget_Dates_Check_In_InputField_Test_Id")).click();
		driver.findElement(By.xpath("//div[@aria-label='Fri Mar 13 2026']")).click();
		driver.findElement(By.xpath("//div[@aria-label='Mon Mar 30 2026']")).click();
	}
	
	@Test(priority = 6,enabled = true)
	public void Rooms() throws InterruptedException {
		WebElement roomElement= driver.findElement(By.xpath("//input[@placeholder='Select rooms']"));
		roomElement.click();
		
		driver.findElement(By.cssSelector("[data-testid='DesktopSearchWidget_Guests_Reservation_Option_Test_Id_C_2']")).click();
		Thread.sleep(1000);
		
		List< WebElement> AdultsAndChildSelect = driver.findElements(By.cssSelector(".MuiSelect-select.MuiSelect-outlined.MuiInputBase-input.MuiOutlinedInput-input.alm-desktop-ida957"));
		AdultsAndChildSelect.get(0).click();
		driver.findElement(By.xpath("//div[text()='3 Adults']")).click();
		Thread.sleep(1000);
		AdultsAndChildSelect.get(1).click();
		driver.findElement(By.xpath("//li[@data-value='3']")).click();
		
		List< WebElement> ChildAge = driver.findElements(By.cssSelector(".MuiSelect-select.MuiSelect-outlined.MuiInputBase-input.MuiOutlinedInput-input.alm-desktop-ida957"));
		ChildAge.get(2).click();
		driver.findElement(By.xpath("//li[@data-value='4']")).click();
		ChildAge.get(3).click();
		driver.findElement(By.xpath("//li[@data-value='5']")).click();
		ChildAge.get(4).click();
		driver.findElement(By.xpath("//li[@data-value='5']")).click();
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		}
}
