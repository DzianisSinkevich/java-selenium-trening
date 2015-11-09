package denis.trening.pages;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;

import denis.trening.applogic.ApplicationManager;
import denis.trening.applogic2.ApplicationManager2;
import denis.trening.model.User;

public class TestBase {

	public static User ADMIN = new User().setLogin("admin").setPassword("admin");
	
	protected ApplicationManager app;

	@BeforeClass
	public void init() {
		app = new ApplicationManager2();
	}

	@AfterSuite
	public void stop() {
		app.stop();
	}

}
