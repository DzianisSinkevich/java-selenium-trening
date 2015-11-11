package denis.trening;

import org.testng.annotations.Test;

import denis.trening.model.Film;
import denis.trening.pages.TestBase;

public class SearchNegative extends TestBase {
	private boolean acceptNextAlert = true;
	private StringBuffer verificationErrors = new StringBuffer();

	@Test
	public void testUntitled() {
		app.getUserHelper().loginAs(ADMIN);
		Film film = new Film().setTitle("NotExistFilm");
		assert(app.getFilmHelper().notFoundResults(film));
		app.getUserHelper().logout();
	}
}
