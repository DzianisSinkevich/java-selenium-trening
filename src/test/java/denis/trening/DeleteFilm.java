package denis.trening;

import org.testng.annotations.Test;

import denis.trening.model.Film;
import denis.trening.pages.TestBase;

public class DeleteFilm extends TestBase {
	private boolean acceptNextAlert = true;
	private StringBuffer verificationErrors = new StringBuffer();

	@Test
	public void testUntitled() {
		app.getUserHelper().loginAs(ADMIN);
		Film film = new Film();
		app.getFilmHelper().delete(film);
		assert(app.getFilmHelper().isFilmDeleted(film));
		app.getUserHelper().logout();
	}
}
