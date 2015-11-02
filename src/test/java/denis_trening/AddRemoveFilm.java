package denis_trening;

import static org.junit.Assert.assertTrue;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.testng.annotations.Test;

import denis_trening_pages.TestBase;

public class AddRemoveFilm extends TestBase {
	private boolean acceptNextAlert = true;
	private StringBuffer verificationErrors = new StringBuffer();

	@Test(priority = 1)
	public void login() throws Exception {
		driver.get(baseUrl + "/php4dvd/");

		for (int count = 0;; count++) {
			if (count >= 30)
				throw new TimeoutException();
			try {
				driver.findElement(By.name("submit"));
				break;
			} catch (NoSuchElementException e) {
			}
			Thread.sleep(1000);
		}
		Thread.sleep(500);
		driver.findElement(By.id("username")).clear();
		driver.findElement(By.id("username")).sendKeys("admin");

		driver.findElement(By.name("password")).clear();
		driver.findElement(By.name("password")).sendKeys("admin");
		driver.findElement(By.name("submit")).click();
	}

	@Test(priority = 2)
	public void openPageOfAddFilm() throws Exception {
		for (int count = 0;; count++) {
			if (count >= 10)
				throw new TimeoutException();
			try {
				driver.findElement(By.cssSelector("img[alt=\"Add movie\"]"));
				break;
			} catch (NoSuchElementException e) {
			}
			Thread.sleep(1000);
		}
		driver.findElement(By.cssSelector("img[alt=\"Add movie\"]")).click();
	}

	@Test(priority = 3)
	public void insertInvalidData() throws Exception {
		Thread.sleep(2000);
		driver.findElement(By.name("name")).clear();
		driver.findElement(By.name("name")).sendKeys("Film name");
		driver.findElement(By.name("year")).clear();
		driver.findElement(By.name("year")).sendKeys("asder");
		driver.findElement(By.cssSelector("img[alt=\"Save\"]")).click();
		if ((driver
				.findElement(By
						.xpath("/html/body/div[@id='container']/div[@id='wrapper']/div[@id='content']/section/div[@class='content']/div[@class='addmovie']/form[@id='updateform']/table/tbody/tr[4]/td[2]/label[@class='error']"))
				.getText()).equals("Please enter a valid number")) {
			System.out.println("Invalid data in field 'Year' - error is rigth");
		} else {
			System.out.println("Invalid data in field 'Year' - error is wrong");
		}
		driver.findElement(By.id("submit")).click();
		driver.findElement(By.name("name")).clear();
		driver.findElement(By.name("name")).sendKeys("");
		driver.findElement(By.name("year")).clear();
		driver.findElement(By.name("year")).sendKeys("2010");
		driver.findElement(By.cssSelector("img[alt=\"Save\"]")).click();

		if ((driver
				.findElement(By
						.xpath("/html/body/div[@id='container']/div[@id='wrapper']/div[@id='content']/section/div[@class='content']/div[@class='addmovie']/form[@id='updateform']/table/tbody/tr[2]/td[2]/label[@class='error']"))
				.getText()).equals("This field is required")) {
			System.out.println("Empty field 'Title' - error is rigth");
		} else {
			System.out.println("Empty field 'Title' - error is wrong");
		}
		System.out.println(driver
				.findElement(By
						.xpath("/html/body/div[@id='container']/div[@id='wrapper']/div[@id='content']/section/div[@class='content']/div[@class='addmovie']/form[@id='updateform']/table/tbody/tr[4]/td[2]/label[@class='error']"))
				.getText());

		driver.findElement(By.id("submit")).click();
	}

	@Test(priority = 4)
	public void insertValidData() throws Exception {
		driver.findElement(By.name("name")).clear();
		driver.findElement(By.name("name")).sendKeys("My");
		driver.findElement(By.name("year")).clear();
		driver.findElement(By.name("year")).sendKeys("111");
		driver.findElement(By.cssSelector("img[alt=\"Save\"]")).click();
		Thread.sleep(1000);
	}

	@Test(priority = 30)
	public void deleteFilm() throws Exception {
		for (int count = 0;; count++) {
			if (count >= 10)
				throw new TimeoutException();
			try {
				driver.findElement(By.linkText("Home"));
				break;
			} catch (NoSuchElementException e) {
			}
			Thread.sleep(1000);
		}
		driver.findElement(By.linkText("Home")).click();
		driver.findElement(By.cssSelector("div.nocover")).click();
		driver.findElement(By.cssSelector("img[alt=\"Remove\"]")).click();
		// Thread.sleep(2000);
		assertTrue(closeAlertAndGetItsText().matches("^Are you sure you want to remove this[\\s\\S]$"));
	}

	@Test(priority = 999)
	public void logout() throws Exception {
		driver.findElement(By.linkText("Log out")).click();
		assertTrue(closeAlertAndGetItsText().matches("^Are you sure you want to log out[\\s\\S]$"));
	}

	private boolean isElementPresent(By by) {
		try {
			driver.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	private String closeAlertAndGetItsText() {
		try {
			Alert alert = driver.switchTo().alert();
			String alertText = alert.getText();
			if (acceptNextAlert) {
				alert.accept();
			} else {
				alert.dismiss();
			}
			return alertText;
		} finally {
			acceptNextAlert = true;
		}
	}
}
