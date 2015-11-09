package denis.trening.pages;

import static org.openqa.selenium.support.ui.ExpectedConditions.*;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;

public class InternalPage extends AnyPage {

	public InternalPage(PageManager pages) {
		super(pages);
	}

	public InternalPage ensurePageLoaded() {
		super.ensurePageLoaded();
		wait.until(presenceOfElementLocated(By.cssSelector("nav")));
		return this;
	}

	@FindBy(css = "nav a[href = 'http://localhost/php4dvd/']")
	private WebElement homeLink;

	@FindBy(css = "nav a[href $= '?go=profile']")
	private WebElement userProfileLink;

	@FindBy(css = "nav a[href $= '?go=users']")
	private WebElement userManagementLink;

	@FindBy(css = "nav a[href = './?go=add']")
	private WebElement filmAddLink;

	@FindBy(css = "nav a[href $= '?logout']")
	private WebElement logoutLink;

	@FindBy(id = "q")
	private WebElement searchField;

//	@FindAll(@FindBy(css = "[class*='movie_box']"))

	public InternalPage clickHomePage() {
		homeLink.click();
		return pages.internalPage;
	}

	public UserProfilePage clickUserProfilePage() {
		userProfileLink.click();
		return pages.userProfilePage;
	}

	public UserManagementPage clickUserManagementLink() {
		userManagementLink.click();
		return pages.userManagementPage;
	}

	public LoginPage clickLogoutLink() {
		logoutLink.click();
		wait.until(alertIsPresent()).accept();
		return pages.loginPage;
	}

	public FilmAddPage clickFilmAddLink() {
		filmAddLink.click();
		return pages.filmAddPage;
	}

	public InternalPage clickSearchLink(String title) {
		pages.internalPage.clickHomePage();
		searchField.click();
		searchField.sendKeys(title + Keys.ENTER);
		return pages.internalPage;
	}

	public boolean readAllFilmsName(String title) {

		List<WebElement> elements;
		elements = driver.findElements(By.xpath("/html/body/div[@id='container']/div[@id='wrapper']/div[@id='content']/section/div[@id='results']/*/*/div[@class='title']"));
		System.out.println("Amoun - " + elements.size());
		for (WebElement el : elements) {
			if (el.getAttribute("value").equals(title)) {
				System.out.println(el.getText());
				return true;
			}
		}
		return false;
	}
}
