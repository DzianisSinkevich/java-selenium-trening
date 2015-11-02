package denis_trening;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.testng.annotations.Test;

import denis_trening_pages.TestBase;

public class LoginTest extends TestBase {
	private boolean acceptNextAlert = true;
	private StringBuffer verificationErrors = new StringBuffer();

//	@Test (priority = 30)
	public void testUntitled() throws Exception {
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
