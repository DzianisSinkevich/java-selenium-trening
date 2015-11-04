package denis.trening;

import static org.junit.Assert.assertTrue;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.testng.annotations.Test;

import denis_trening_pages.TestBase;

public class AddFilmValid extends TestBase {
	private boolean acceptNextAlert = true;
	private StringBuffer verificationErrors = new StringBuffer();

	// @Test(priority = 1)
	// public void login() throws Exception {
	// driver.get(baseUrl + "/php4dvd/");
	//
	// for (int count = 0;; count++) {
	// if (count >= 30)
	// throw new TimeoutException();
	// try {
	// driver.findElement(By.name("submit"));
	// break;
	// } catch (NoSuchElementException e) {
	// }
	// }
	// driver.findElement(By.id("username")).clear();
	// driver.findElement(By.id("username")).sendKeys("admin");
	//
	// driver.findElement(By.name("password")).clear();
	// driver.findElement(By.name("password")).sendKeys("admin");
	// driver.findElement(By.name("submit")).click();
	// }

	@Test(priority = 2)
	public void openPageOfAddFilm() throws Exception {
		driver.findElement(By.linkText("Home")).click();
		for (int count = 0;; count++) {
			if (count >= 10)
				throw new TimeoutException();
			try {
				driver.findElement(By.cssSelector("img[alt=\"Add movie\"]"));
				break;
			} catch (NoSuchElementException e) {
			}
		}
		driver.findElement(By.cssSelector("img[alt=\"Add movie\"]")).click();
	}

	@Test(priority = 4)
	public void insertValidData() throws Exception {
		for (int count = 0;; count++) {
			if (count >= 10)
				throw new TimeoutException();
			try {
				driver.findElement(By.name("name"));
				break;
			} catch (NoSuchElementException e) {
			}
		}
		driver.findElement(By.name("name")).clear();
		driver.findElement(By.name("name")).sendKeys("My");
		driver.findElement(By.name("year")).clear();
		driver.findElement(By.name("year")).sendKeys("111");
		driver.findElement(By.cssSelector("img[alt=\"Save\"]")).click();
		System.out.println("Film is created");
	}

	// @Test(priority = 999)
	// public void logout() throws Exception {
	// driver.findElement(By.linkText("Log out")).click();
	// assertTrue(closeAlertAndGetItsText().matches("^Are you sure you want to
	// log out[\\s\\S]$"));
	// }

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
