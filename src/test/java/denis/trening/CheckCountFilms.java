package denis.trening;

import static org.junit.Assert.assertTrue;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.testng.annotations.Test;

import denis_trening_pages.TestBase;

public class CheckCountFilms extends TestBase {
	private boolean acceptNextAlert = true;
	private StringBuffer verificationErrors = new StringBuffer();

	@Test(priority = 1, groups = "test")
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

	@Test(priority = 2, groups = "test")
	public void checkAmount() throws Exception {
		Thread.sleep(3000);
		System.out.println("Size - " + driver.findElements(By.cssSelector("[class='movie_box']")).size());
	}

	@Test(priority = 3, groups = "test")
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
