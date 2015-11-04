package denis.trening;

import static org.junit.Assert.assertTrue;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.testng.annotations.Test;

import denis_trening_pages.TestBase;

public class DeleteFilm extends TestBase {
	Integer kolFilms;
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

	@Test
	public void deleteFilm() throws Exception {
		driver.get(baseUrl + "/php4dvd/");
		if (amountFilms() > 0) {
			for (int count = 0;; count++) {
				if (count >= 10)
					throw new TimeoutException();
				try {
					driver.findElement(By.cssSelector("div.nocover"));
					break;
				} catch (NoSuchElementException e) {
				}
			}
			driver.findElement(By.cssSelector("div.nocover")).click();
			driver.findElement(By.cssSelector("img[alt=\"Remove\"]")).click();
			assertTrue(closeAlertAndGetItsText().matches("^Are you sure you want to remove this[\\s\\S]$"));
			System.out.println("Film is deleted");
		} else {
			System.out.println("Not found any film");
		}
	}

	// @Test(priority = 999)
	// public void logout() throws Exception {
	// driver.findElement(By.linkText("Log out")).click();
	// assertTrue(closeAlertAndGetItsText().matches("^Are you sure you want to
	// log out[\\s\\S]$"));
	// }

	public Integer amountFilms() throws Exception {
		for (int count = 0;; count++) {
			if (count >= 10)
				throw new TimeoutException();
			try {
				driver.findElement(By.id("results"));
				break;
			} catch (NoSuchElementException e) {
			}
			Thread.sleep(1000);
		}
		kolFilms = driver.findElements(By.cssSelector("[class^='movie_box']")).size();
		System.out.println("Amount of films  - " + kolFilms);
		return kolFilms;
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
