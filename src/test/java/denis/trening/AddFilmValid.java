package denis.trening;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.testng.annotations.Test;

import denis.trening.model.Film;
import denis.trening.model.User;
import denis.trening.pages.TestBase;

public class AddFilmValid extends TestBase {
	private boolean acceptNextAlert = true;
	private StringBuffer verificationErrors = new StringBuffer();

	@Test
	public void testUntitled() {
		app.getUserHelper().loginAs(ADMIN);
		Film film = new Film()
				.setTitle("NewFilm")
				.setYear("1999");
		app.getFilmHelper().create(film);
		assert(app.getFilmHelper().isFilmAdded(film));
		app.getUserHelper().logout();
	}
}
