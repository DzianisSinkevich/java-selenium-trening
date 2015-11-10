package denis.trening.pages;

import static org.openqa.selenium.support.ui.ExpectedConditions.alertIsPresent;
import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

import java.util.List;

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

	public int afterAmountFilms;

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
	private List<WebElement> searchedFilms;

	@FindBy(id = "q")
	private WebElement searchField;

	@FindBy(xpath = "/html/body/div[@id='container']/div[@id='wrapper']/div[@id='content']/section/div[@id='results']/*/*/div[@class='movie_cover']/div[@class='nocover']")
	private List<WebElement> forAmountFilms;

	@FindBy(xpath = "/html/body/div[@id='container']/div[@id='wrapper']/div[@id='content']/section/div[@id='results']/a[1]")
	private WebElement firstFilm;

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
		driver.navigate().refresh();
		return pages.internalPage;
	}

	public InternalPage clearingSearchField() {
		String selectAll = Keys.chord(Keys.CONTROL + "a");
		searchField.sendKeys(selectAll + Keys.DELETE + Keys.ENTER);
		clickHomePage();
		return pages.internalPage;
	}

	public List<WebElement> getSearchedFilms() {
		return searchedFilms;
	}

	public void checkAmountAfter() {
		afterAmountFilms = forAmountFilms.size();
		System.out.println("NewafterAmountFilms " + afterAmountFilms);
	}

	public boolean confirmAmountsAdd() {
		clearingSearchField();
		System.out.println("afterAmountFilms " + afterAmountFilms);
		System.out.println("forAmountFilms " + forAmountFilms.size());
		if (afterAmountFilms + 1 == forAmountFilms.size()) {
			return true;
		}
		return false;
	}

	public FilmManagementPage clickToFilm(String title) {
		List<WebElement> elements;
		elements = getSearchedFilms();
		for (WebElement el : elements) {
			if (el.getText().equals(title)) {
				el.click();
				break;
			}
		}
		return pages.filmManagementPage;
	}

	public FilmManagementPage clickToFirstFilm() {
		firstFilm.click();
		return pages.filmManagementPage;
	}

	public boolean confirmAmountsDelete() {
		clearingSearchField();
		driver.navigate().refresh();
		pages.internalPage.ensurePageLoaded();
		System.out.println("afterAmountFilms " + afterAmountFilms);
		System.out.println("forAmountFilms " + forAmountFilms.size());
		if (afterAmountFilms - 1 == forAmountFilms.size()) {
			return true;
		}
		return false;
	}
}
