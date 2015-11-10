package denis.trening.pages;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class HomePage extends InternalPage {

	public HomePage(PageManager pages) {
		super(pages);
	}

	@FindBy(css = "//div[@class='title']")
	private WebElement searchedFilms;

	public HomePage searchedFilms(String text) {
		title.sendKeys(text);
		return this;
	}

	public HomePage ensurePageLoaded() {
		super.ensurePageLoaded();
		wait.until(presenceOfElementLocated(By.id("results")));
		return this;
	}

}
