package denis.trening;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.testng.annotations.Test;

import denis_trening_pages.TestBase;

public class AddFilmInvalid extends TestBase {
	int kolFilms = 0;
	int kolFilmsOld = 0;

	private boolean acceptNextAlert = true;
	private StringBuffer verificationErrors = new StringBuffer();

	@Test(priority = 1)
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
		
	}

	@Test(priority = 2)
	public void insertInvalidData() throws Exception {
		driver.findElement(By.cssSelector("img[alt=\"Add movie\"]")).click();
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
		driver.findElement(By.name("name")).sendKeys("Film name");
		driver.findElement(By.name("year")).clear();
		driver.findElement(By.name("year")).sendKeys("asder");
		driver.findElement(By.cssSelector("img[alt=\"Save\"]")).click();
		assert((driver
				.findElement(By
						.xpath("/html/body/div[@id='container']/div[@id='wrapper']/div[@id='content']/section/div[@class='content']/div[@class='addmovie']/form[@id='updateform']/table/tbody/tr[4]/td[2]/label[@class='error']"))
				.getText()).equals("Please enter a valid number"));
		driver.findElement(By.id("submit")).click();
		driver.findElement(By.name("name")).clear();
		driver.findElement(By.name("name")).sendKeys("");
		driver.findElement(By.name("year")).clear();
		driver.findElement(By.name("year")).sendKeys("2010");
		driver.findElement(By.cssSelector("img[alt=\"Save\"]")).click();
		assert((driver
				.findElement(By
						.xpath("/html/body/div[@id='container']/div[@id='wrapper']/div[@id='content']/section/div[@class='content']/div[@class='addmovie']/form[@id='updateform']/table/tbody/tr[2]/td[2]/label[@class='error']"))
				.getText()).equals("This field is required"));
		driver.findElement(By.id("submit")).click();
	}

	@Test(priority = 3)
	public void insertEpmtyField() throws Exception {

		driver.findElement(By.id("submit")).click();
		driver.findElement(By.name("name")).clear();
		driver.findElement(By.name("name")).sendKeys("");
		driver.findElement(By.name("year")).clear();
		driver.findElement(By.name("year")).sendKeys("2010");
		driver.findElement(By.cssSelector("img[alt=\"Save\"]")).click();
		assert((driver
				.findElement(By
						.xpath("/html/body/div[@id='container']/div[@id='wrapper']/div[@id='content']/section/div[@class='content']/div[@class='addmovie']/form[@id='updateform']/table/tbody/tr[2]/td[2]/label[@class='error']"))
				.getText()).equals("This field is required"));
		driver.findElement(By.id("submit")).click();
	}

	@Test(priority = 4)
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
		assert(kolFilms == kolFilmsOld);
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
