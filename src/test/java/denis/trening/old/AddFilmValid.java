package denis.trening.old;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.testng.annotations.Test;

import denis.trening.pages.TestBase;

public class AddFilmValid extends TestBase {
	int kolFilms = 0;
	int kolFilmsOld = 0;

	private boolean acceptNextAlert = true;
	private StringBuffer verificationErrors = new StringBuffer();

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
		kolFilmsOld = driver.findElements(By.cssSelector("[class^='movie_box']")).size();
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
	}

	@Test(priority = 5)
	public void checkOfAdding() throws Exception {
		driver.findElement(By.linkText("Home")).click();
		for (int count = 0;; count++) {
			if (count >= 10)
				throw new TimeoutException();
			try {
				driver.findElement(By.cssSelector("[class^='movie_box']"));
				break;
			} catch (NoSuchElementException e) {
			}
		}
		kolFilms = driver.findElements(By.cssSelector("[class^='movie_box']")).size();
		assert(kolFilms == kolFilmsOld + 1);
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
