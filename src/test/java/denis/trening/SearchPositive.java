package denis.trening;

import org.testng.annotations.Test;

import denis.trening.model.Film;
import denis.trening.pages.TestBase;

public class SearchPositive extends TestBase {
	private boolean acceptNextAlert = true;
	private StringBuffer verificationErrors = new StringBuffer();

	@Test
	public void testUntitled() {
		app.getUserHelper().loginAs(ADMIN);
		Film film = new Film().setTitle("");
		app.getFilmHelper().getFirstFilmTitle(film);
		assert(app.getFilmHelper().isOnlySerchedInResults(film));
		app.getUserHelper().logout();
	}
}
