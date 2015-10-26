package denis_trening;

import static org.junit.Assert.assertTrue;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.testng.annotations.Test;

import denis_trening_pages.TestBase;

public class LoginTest extends TestBase {
	private boolean acceptNextAlert = true;
	private StringBuffer verificationErrors = new StringBuffer();

	@Test
	public void testUntitled() throws Exception {
		driver.get(baseUrl + "/php4dvd/");
		driver.findElement(By.name("password")).clear();
		driver.findElement(By.name("password")).sendKeys("admin");
		driver.findElement(By.id("username")).clear();
		driver.findElement(By.id("username")).sendKeys("admin");
		driver.findElement(By.name("submit")).click();
		driver.findElement(By.linkText("Nederlands")).click();
		driver.findElement(By.linkText("Gebruikers")).click();
		driver.findElement(By.name("email")).clear();
		driver.findElement(By.name("email")).sendKeys("admin");
		driver.findElement(By.id("password")).clear();
		driver.findElement(By.id("password")).sendKeys("admin");
		driver.findElement(By.linkText("Log uit")).click();
		assertTrue(closeAlertAndGetItsText().matches("^Weet je zeker dat je wilt uitloggen[\\s\\S]$"));
		driver.findElement(By.name("password")).clear();
		driver.findElement(By.name("password")).sendKeys("admin");
		driver.findElement(By.id("username")).clear();
		driver.findElement(By.id("username")).sendKeys("admin");
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
