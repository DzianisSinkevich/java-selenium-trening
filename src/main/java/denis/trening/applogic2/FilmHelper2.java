package denis.trening.applogic2;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import denis.trening.applogic.FilmHelper;
import denis.trening.model.Film;

public class FilmHelper2 extends DriverBasedHelper implements FilmHelper {

	public FilmHelper2(ApplicationManager2 manager) {
		super(manager.getWebDriver());
	}

	@Override
	public void create(Film film) {
		pages.internalPage.clickFilmAddLink().ensurePageLoaded()
				.setTitleField(film.getTitle()).setYearField(film.getYear())
				.clickAddButton();
	}

	@Override
	public void delete(Film film) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Film> search(String title) {
		WebElement elements;
		List<Film> films = new ArrayList<Film>();
		pages.internalPage.clickSearchLink(title);
		 elements = pages.internalPage.getSearchedFilms();
		 System.out.println(elements.toString());
		// driver.findElements(By.xpath("/html/body/div[@id='container']/div[@id='wrapper']/div[@id='content']/section/div[@id='results']/*/div[@class='title']"));
//		 for (WebElement el : elements) {
		 films.add(new Film().setTitle(elements.getText()));
//		 }
		return null;
	}
	
	@FindBy(css = "nav a[href = './?go=add']")
	private WebElement firstFilm;
	
	@Override
	public boolean isFilmAdded(Film film) {
		//firstFilm.click();
		List<Film> films = search(film.getTitle());
		// for (Film fil : films) {
		// String elval = fil.getTitle();
		// System.out.println("firstFilm - " + firstFilm);
		// if (elval.equals(film.getTitle())) {
		// return true;
		// }
		// }
		return false;
	}

}
