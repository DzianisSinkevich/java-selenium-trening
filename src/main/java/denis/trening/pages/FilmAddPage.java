package denis.trening.pages;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class FilmAddPage extends InternalPage {

	public FilmAddPage(PageManager pages) {
		super(pages);
	}

	@FindBy(name = "name")
	private WebElement title;

	@FindBy(name = "year")
	private WebElement year;

	@FindBy(css = "nav [alt = 'Save']")
	private WebElement addButton;

	public String getUsername() {
		return title.getAttribute("value");
	}

	public FilmAddPage setTitleField(String text) {
		title.sendKeys(text);
		return this;
	}

	public String getEmail() {
		return year.getAttribute("value");
	}

	public FilmAddPage setYearField(String text) {
		year.sendKeys(text);
		return this;
	}

	public void clickAddButton() {
		addButton.click();
	}

	public FilmAddPage ensurePageLoaded() {
		super.ensurePageLoaded();
		wait.until(presenceOfElementLocated(By.cssSelector("[name='name']")));
		return this;
	}

}
