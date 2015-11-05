package denis.trening;

import static org.junit.Assert.assertTrue;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.testng.annotations.Test;

import denis_trening_pages.TestBase;

public class DeleteFilm extends TestBase {
	int kolFilms = 0;
	int kolFilmsOld = 0;

	private boolean acceptNextAlert = true;
	private StringBuffer verificationErrors = new StringBuffer();

	@Test
	public void deleteFilm() throws Exception {
		driver.get(baseUrl + "/php4dvd/");
		for (int count = 0;; count++) {
			if (count >= 10)
				throw new TimeoutException();
			try {
				driver.findElement(By.cssSelector("img[alt=\"Add movie\"]"));
				break;
			} catch (NoSuchElementException e) {
			}
		}
		kolFilmsOld = driver.findElements(By.cssSelector("[class^='movie_box']")).size();
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
		}
		kolFilms = driver.findElements(By.cssSelector("[class^='movie_box']")).size();
		assert(kolFilms == kolFilmsOld - 1);
	}

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
