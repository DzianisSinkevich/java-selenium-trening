package denis.trening.applogic2;

import java.util.List;

import denis.trening.applogic.FilmHelper;
import denis.trening.model.Film;

public class FilmHelper2 extends DriverBasedHelper implements FilmHelper {

	public FilmHelper2(ApplicationManager2 manager) {
		super(manager.getWebDriver());
	}

	@Override
	public void create(Film film) {
		pages.internalPage.clickFilmAddLink().ensurePageLoaded().setTitleField(film.getTitle())
				.setYearField(film.getYear()).clickAddButton();
	}

	@Override
	public void delete(Film film) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Film> search(String title) {
		pages.internalPage.clickSearchLink(title);
		return null;
	}

	@Override
	public boolean isFilmAdded(Film film) {
		search(film.getTitle());
		return pages.internalPage.readAllFilmsName(film.getTitle());
	}

}
