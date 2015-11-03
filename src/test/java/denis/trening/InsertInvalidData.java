package denis.trening;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.testng.annotations.Test;

import denis_trening_pages.TestBase;

public class InsertInvalidData extends TestBase {
	private boolean acceptNextAlert = true;
	private StringBuffer verificationErrors = new StringBuffer();

//	@Test(priority = 20)
	public void testUntitled() throws Exception {
		Thread.sleep(2000);
		driver.findElement(By.name("name")).clear();
		driver.findElement(By.name("name")).sendKeys("Film name");
		driver.findElement(By.name("year")).clear();
		driver.findElement(By.name("year")).sendKeys("asder");
		driver.findElement(By.cssSelector("img[alt=\"Save\"]")).click();
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
		if ((driver
				.findElement(By
						.xpath("/html/body/div[@id='container']/div[@id='wrapper']/div[@id='content']/section/div[@class='content']/div[@class='addmovie']/form[@id='updateform']/table/tbody/tr[4]/td[2]/label[@class='error']"))
				.getText()).equals("Please enter a valid number")) {
			System.out.println("Invalid data in field 'Year' - error is rigth");
		} else {
			System.out.println("Invalid data in field 'Year' - error is wrong");
		}
		driver.findElement(By.id("submit")).click();
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
