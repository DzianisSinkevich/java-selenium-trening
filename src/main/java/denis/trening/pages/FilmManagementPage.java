package denis.trening.pages;

import static org.openqa.selenium.support.ui.ExpectedConditions.alertIsPresent;
import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;

public class FilmManagementPage extends InternalPage {

	public FilmManagementPage(PageManager pages) {
		super(pages);
	}

	private Select permissionDropdown() {
		return new Select(driver.findElement(By.name("permission")));
	}

	public InternalPage clickRemoveFilm() {
		driver.findElement(By.cssSelector("img[alt=\"Remove\"]")).click();
		wait.until(alertIsPresent()).accept();
		return pages.internalPage;
	}

	public String getRole() {
		return permissionDropdown().getFirstSelectedOption().getText();
	}

	public FilmManagementPage setRole(String text) {
		permissionDropdown().selectByVisibleText(text);
		return this;
	}

	public FilmManagementPage ensurePageLoaded() {
		super.ensurePageLoaded();
		wait.until(presenceOfElementLocated(By.cssSelector("[class='maininfo_full']")));
		return this;
	}
}
