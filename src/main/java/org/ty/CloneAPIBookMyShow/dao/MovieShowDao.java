package org.ty.CloneAPIBookMyShow.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.ty.CloneAPIBookMyShow.Repository.ShowRepo;
import org.ty.CloneAPIBookMyShow.entity.MovieShow;

@Repository
public class MovieShowDao {

	@Autowired
	private ShowRepo repo;

	public MovieShow addShow(MovieShow movieShow) {
		return repo.save(movieShow);
	}

	public MovieShow updateShow(long showId, MovieShow movieShow) {
		Optional<MovieShow> optional = repo.findById(showId);
		if (optional.isPresent()) {
			movieShow.setShowId(showId);
			movieShow.setTheatre(optional.get().getTheatre());
			return repo.save(movieShow);
		}
		return null;
	}

	public MovieShow getShowById(long showId) {
		Optional<MovieShow> optional = repo.findById(showId);
		if (optional.isPresent()) {
			return optional.get();
		}
		return null;
	}

	public MovieShow deleteShowById(long showId) {
		Optional<MovieShow> optional = repo.findById(showId);
		if (optional.isPresent()) {
			MovieShow movieShow=optional.get();
			movieShow.setTheatre(null);
			repo.delete(movieShow);
			return optional.get();
		}
		return null;
	}

}
