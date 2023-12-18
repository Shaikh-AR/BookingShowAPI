package org.ty.CloneAPIBookMyShow.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.ty.CloneAPIBookMyShow.Repository.TheatreRepo;
import org.ty.CloneAPIBookMyShow.entity.MovieShow;
import org.ty.CloneAPIBookMyShow.entity.Theatre;

@Repository
public class TheatreDao {
	
	@Autowired
	private TheatreRepo repo;
	
	public Theatre saveTheatre(Theatre theatre) {
		return repo.save(theatre);
	}

	public Theatre updateTheatre(long theatreId, Theatre theatre) {
		Optional<Theatre> optional=repo.findById(theatreId);
		if(optional.isPresent()) {
			Theatre oldTheatre=optional.get();
			theatre.setTheatreId(theatreId);
			theatre.setAddress(oldTheatre.getAddress());
			theatre.setMovieShows(oldTheatre.getMovieShows());
			theatre.setOwner(oldTheatre.getOwner());
			theatre.setScreens(oldTheatre.getScreens());
			return repo.save(theatre);
		}
		return null;
	}

	public Theatre getTheatreById(long theatreId) {
		Optional<Theatre> optional=repo.findById(theatreId);
		if(optional.isPresent()) {
			return optional.get();
		}
		return null;
	}
	
	public Theatre deleteTheatreById(long theatreId) {
		Optional<Theatre> optional=repo.findById(theatreId);
		if(optional.isPresent()) {
			Theatre theatre=optional.get();
			theatre.setOwner(null);
			theatre.setAddress(null);
			List<MovieShow> list = optional.get().getMovieShows();
			
			for(MovieShow show:list) {
				show.setTheatre(null);
			}
			/*
			* theatre.setScreens(null);
			* repo.delete(theatre);
			* if it is not work please Used above Repo concept
			*/
			repo.deleteById(theatreId);
			return optional.get();
		}
		return null;
	}

	
}
