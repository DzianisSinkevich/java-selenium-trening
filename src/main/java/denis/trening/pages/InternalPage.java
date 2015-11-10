package denis.trening.pages;

import static org.openqa.selenium.support.ui.ExpectedConditions.alertIsPresent;
import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class InternalPage extends AnyPage {
	public static final String HOME_LINK = "nav a[href = 'http://localhost/php4dvd/']";

	public InternalPage(PageManager pages) {
		super(pages);
	}

	public InternalPage ensurePageLoaded() {
		super.ensurePageLoaded();
		wait.until(presenceOfElementLocated(By.cssSelector("nav")));
		return this;
	}

	@FindBy(css = HOME_LINK)
	private WebElement homeLink;

	@FindBy(css = "nav a[href $= '?go=profile']")
	private WebElement userProfileLink;

	@FindBy(css = "nav a[href $= '?go=users']")
	private WebElement userManagementLink;

	@FindBy(css = "nav a[href = './?go=add']")
	private WebElement filmAddLink;

	@FindBy(css = "nav a[href $= '?logout']")
	private WebElement logoutLink;
	
	@FindBy(xpath = "//div[@class='title']")
	private WebElement searchedFilms;

	@FindBy(id = "q")
	private WebElement searchField;

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
	
	public  WebElement getSearchedFilms(){
		return searchedFilms;
	}
}
