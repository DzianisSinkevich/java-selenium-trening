package denis.trening.applogic;

import java.util.List;

import denis.trening.model.Film;

public interface FilmHelper {

	void create(Film film);
	void delete(Film film);
	List<Film> search(String title);
	boolean isFilmAdded(Film film);
	boolean isFilmDeleted(Film film);

}
