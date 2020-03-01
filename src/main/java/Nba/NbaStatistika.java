package Nba;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.google.common.collect.Iterables;
import org.openqa.selenium.NoSuchElementException;

public class NbaStatistika {
    // All variables necessary that we will use
	public static String driverPath = "/Users/gjorgjebogdanovski/Desktop/";
	public static WebDriver driver;
	public static By searchInputIcon = By.className("stats-search__icon-text");
	public static By searchInput = By.className("stats-search__input");
	public static By searchEnter = By.className("stats-search__item");
	public static By rows = By.tagName("tr");
	public static By tabela = By.xpath("/html/body/main/div[2]/div/div/div[3]/div/div/div/nba-stat-table[1]/div[2]/div[1]/table");
	public static By kolona = By.className("first");
	public static By kolona_red = By.tagName("td");

	// Setting the property, and calling the firefox driver that we will use.
	public static void launchBrowser() {
		System.out.println("Launching firefox browser");
		System.setProperty("webdriver.firefox.driver", driverPath + "geckodriver");
		driver = new FirefoxDriver();
	}
    // Opening the website.
	public static void openApplication() {
		driver.get("https:stats.nba.com/");
		

	}
    
	// Closing the website
	public static void closeDriver() {
		if (driver != null) {
			driver.close();
		}
	}

	// Launching and opening the website.
	public static void launchAndOpen() {
		launchBrowser();
		openApplication();
	}
    
	// Entering the data to the input element on the stats.nba.com website.
	public static void searchInputProvideData(String searchTerm) {
		WebElement searchElement = driver.findElement(searchInputIcon);
		searchElement.click();
		WebElement searchInputElement = driver.findElement(searchInput);
		searchInputElement.sendKeys(searchTerm);
		WebElement searchInputElementIcon = driver.findElement(searchEnter);
		searchInputElementIcon.click();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	}

	// Going through the first table of the player profile page.
	public static void goThroughTheTable() {
		WebElement FirstTableOfThePlayerProfile = driver.findElement(tabela);
		List<WebElement> rowss = FirstTableOfThePlayerProfile.findElements(rows);

		for (WebElement row : rowss) {

			List<WebElement> columns = row.findElements(kolona);
			List<WebElement> column_rows = row.findElements(kolona_red);
			int columns_count = column_rows.size();

			for (WebElement col : columns ) {
				if (col.getText().contentEquals("BY YEAR")) {
					System.out.print("");
				} else {
					System.out.print(col.getText());
					System.out.print(" ");
				}
			}

			for (int column = 0; column < columns_count; column++) {

				if (column == 9) {

					System.out.print(column_rows.get(column).getText());
					System.out.println();

				}
			}

		}
	}

	public static void main(String[] args) throws IOException {
		//Getting an input from the user.
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter your favorite NBA Player");
		String searchTerm = scanner.nextLine();
		scanner.close();
		// Launching the website.
		launchAndOpen();
		// providing the data to the website input area
		searchInputProvideData(searchTerm);
		// Going through the table and getting the data.
		goThroughTheTable();
		// Closing the website.
		closeDriver();
	}
}

