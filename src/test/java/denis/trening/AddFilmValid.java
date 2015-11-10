package denis.trening;

import org.testng.annotations.Test;

import denis.trening.model.Film;
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
