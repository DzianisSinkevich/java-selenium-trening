package denis.trening;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.testng.annotations.Test;

import denis_trening_pages.TestBase;

public class CheckSearchAreFound extends TestBase {
	int kolFilms = 0;
	String existName = "";

	private boolean acceptNextAlert = true;
	private StringBuffer verificationErrors = new StringBuffer();

	@Test(priority = 1)
	public void checkOfExistingName() throws Exception {
		driver.findElement(By.linkText("Home")).click();
		for (int count = 0;; count++) {
			if (count >= 10)
				throw new TimeoutException();
			try {
				driver.findElement(By.id("q"));
				break;
			} catch (NoSuchElementException e) {
			}
		}
		
		existName = driver.findElement(By.xpath("//section/div[@id='results']/a[1]/*/div[@class='title']")).getText();
		System.out.println("existName - " + existName);
	}

	@Test(priority = 2)
	public void search() throws Exception {
		driver.findElement(By.id("q")).sendKeys(existName + Keys.ENTER);
	}

	@Test(priority = 3)
	public void checkAmount() throws Exception {
		for (int count = 0;; count++) {
			if (count >= 10)
				throw new TimeoutException();
			try {
				driver.findElement(By.id("results"));
				break;
			} catch (NoSuchElementException e) {
			}
		}
		kolFilms = driver.findElements(By.cssSelector("[class^='movie_box']")).size();
		if (kolFilms > 0) {
			System.out.println("ERROR - Films are found. Amount of films = " + kolFilms);
		}
		assert(kolFilms == 0);
	}

	@Test(priority = 4)
	public void reactions() throws Exception {
		if (kolFilms > 0) {
			System.out.println("Film is in DB");
		} else {
			System.out.println("Film not in DB");
		}
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
