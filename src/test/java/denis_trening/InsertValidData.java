package denis_trening;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.testng.annotations.Test;

import denis_trening_pages.TestBase;

public class InsertValidData extends TestBase {
	private boolean acceptNextAlert = true;
	private StringBuffer verificationErrors = new StringBuffer();

//	@Test(priority = 15)
	public void testUntitled() throws Exception {
		driver.findElement(By.name("name")).clear();
		driver.findElement(By.name("name")).sendKeys("My");
		driver.findElement(By.name("year")).clear();
		driver.findElement(By.name("year")).sendKeys("111");
		driver.findElement(By.cssSelector("img[alt=\"Save\"]")).click();
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
