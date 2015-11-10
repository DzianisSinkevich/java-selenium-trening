package denis.trening.applogic2;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import denis.trening.applogic.FilmHelper;
import denis.trening.model.Film;

public class FilmHelper2 extends DriverBasedHelper implements FilmHelper {

	public FilmHelper2(ApplicationManager2 manager) {
		super(manager.getWebDriver());
	}

	@FindBy(css = "nav a[href = './?go=add']")
	private WebElement firstFilm;

	@Override
	public void create(Film film) {
		pages.internalPage.checkAmountAfter();
		pages.internalPage.clickFilmAddLink().ensurePageLoaded().setTitleField(film.getTitle())
				.setYearField(film.getYear()).clickAddButton();
	}

	@Override
	public void delete(Film film) {
		pages.internalPage.checkAmountAfter();
		pages.internalPage.clickToFirstFilm();
		pages.filmManagementPage.ensurePageLoaded().clickRemoveFilm();
	}

	@Override
	public List<Film> search(String title) {
		List<WebElement> elements;
		List<Film> films = new ArrayList<Film>();
		pages.internalPage.clickSearchLink(title);
		elements = pages.internalPage.getSearchedFilms();
		for (WebElement el : elements) {
			films.add(new Film().setTitle(el.getText()));
		}
		return films;
	}

	@Override
	public boolean isFilmAdded(Film film) {
		List<Film> films = search(film.getTitle());
		if (pages.internalPage.confirmAmountsAdd()) {
			for (Film someFilm : films) {
				if (someFilm.getTitle().equals(film.getTitle())) {
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public boolean isFilmDeleted(Film film) {
		List<Film> films = search(film.getTitle());
		if (pages.internalPage.confirmAmountsDelete()) {
			return true;
		}
		return false;
	}

}
