package denis.trening.pages;

import static org.openqa.selenium.support.ui.ExpectedConditions.alertIsPresent;
import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import denis.trening.model.Film;

public class InternalPage extends AnyPage {
	public static final String HOME_LINK = "nav a[href = 'http://localhost/php4dvd/']";
	public static final String USER_PROFILE_LINK = "nav a[href $= '?go=profile']";
	public static final String USER_MANAGEMENT_LINK = "nav a[href $= '?go=users']";
	public static final String FILM_ADD_LINK = "nav a[href = './?go=add']";
	public static final String LOGOUT_LINK = "nav a[href $= '?logout']";
	public static final String SEARCHED_FILMS = "//div[@class='title']";
	public static final String SEARCHED_FIELD = "q";
	public static final String FOR_AMOUNT_LINK = "//div[@class='movie_cover']/div[@class='nocover']";
	public static final String FIRST_FILM = "//div[@id='results']/a[1]/*/div[@class='title']";
	public static final String LOGIN_FORM = "login";

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

	@FindBy(css = USER_PROFILE_LINK)
	private WebElement userProfileLink;

	@FindBy(css = USER_MANAGEMENT_LINK)
	private WebElement userManagementLink;

	@FindBy(css = FILM_ADD_LINK)
	private WebElement filmAddLink;

	@FindBy(css = LOGOUT_LINK)
	private WebElement logoutLink;

	@FindBy(xpath = SEARCHED_FILMS)
	private List<WebElement> searchedFilms;

	@FindBy(id = SEARCHED_FIELD)
	private WebElement searchField;

	@FindBy(xpath = FOR_AMOUNT_LINK)
	private List<WebElement> forAmountFilms;

	@FindBy(xpath = FIRST_FILM)
	private WebElement firstFilm;

	@FindBy(id = "login")
	private List<WebElement> loginForm;

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
		clearingSearchField();
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
//		System.out.println("NewafterAmountFilms " + afterAmountFilms);
	}

	public boolean confirmAmountsAdd() {
		clearingSearchField();
		// System.out.println("afterAmountFilms " + afterAmountFilms);
		// System.out.println("forAmountFilms " + forAmountFilms.size());
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

	public InternalPage firstFilmTitle(Film film) {
		film.setTitle(firstFilm.getText());
		return pages.internalPage;
	}

	public FilmManagementPage clickToFirstFilm() {
		firstFilm.click();
		return pages.filmManagementPage;
	}

	public boolean confirmAmountsDelete() {
		clearingSearchField();
		driver.navigate().refresh();
		pages.internalPage.ensurePageLoaded();
		// System.out.println("afterAmountFilms " + afterAmountFilms);
		// System.out.println("forAmountFilms " + forAmountFilms.size());
		if (afterAmountFilms - 1 == forAmountFilms.size()) {
			return true;
		}
		return false;
	}

	public boolean checkIsLoginPage() {
		if (loginForm.size() > 0) {
			return true;
		}
		return false;
	}
}
