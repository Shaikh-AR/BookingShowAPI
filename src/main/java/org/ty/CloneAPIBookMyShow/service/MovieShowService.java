package org.ty.CloneAPIBookMyShow.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.ty.CloneAPIBookMyShow.dao.MovieDao;
import org.ty.CloneAPIBookMyShow.dao.MovieShowDao;
import org.ty.CloneAPIBookMyShow.dao.ScreenDao;
import org.ty.CloneAPIBookMyShow.dao.TheatreDao;
import org.ty.CloneAPIBookMyShow.dto.MovieShowDto;
import org.ty.CloneAPIBookMyShow.entity.Movie;
import org.ty.CloneAPIBookMyShow.entity.MovieShow;
import org.ty.CloneAPIBookMyShow.entity.Screen;
import org.ty.CloneAPIBookMyShow.entity.Theatre;
import org.ty.CloneAPIBookMyShow.enums.ScreenAvailability;
import org.ty.CloneAPIBookMyShow.exception.MovieIdNotFoundException;
import org.ty.CloneAPIBookMyShow.exception.MovieShowIdNotFoundException;
import org.ty.CloneAPIBookMyShow.exception.ScreenAlreadyAllotedException;
import org.ty.CloneAPIBookMyShow.exception.ScreenIdNotFoundException;
import org.ty.CloneAPIBookMyShow.exception.TheatreIdNotFoundException;
import org.ty.CloneAPIBookMyShow.util.ResponseStructure;

@Service
public class MovieShowService {

	@Autowired
	private MovieShowDao showDao;

	@Autowired
	private TheatreDao theatreDao;

	@Autowired
	private ScreenDao screenDao;
	@Autowired
	private MovieDao movieDao;

	@Autowired
	private ModelMapper modelMapper;

	public ResponseEntity<ResponseStructure<MovieShow>> addMovieShow(long theatreId, MovieShowDto showDto) {
		Theatre dbTheatre = theatreDao.getTheatreById(theatreId);
		if (dbTheatre != null) {
//			remaining function
			MovieShow movieShow = this.modelMapper.map(showDto, MovieShow.class);
			long screenId = movieShow.getScreenId();
			Screen dbScreen = screenDao.getScreenById(screenId);
			if (dbScreen != null) {
				if (dbScreen.getScreenAvailability().equals(ScreenAvailability.NOT_ALLOTED)) {
//					Add Show
					long movieId = movieShow.getMovieId();
					Movie dbMovie = movieDao.getMovieById(movieId);
					if (dbMovie != null) {
//						Add movie to that Show
						movieShow.setMovieDescription(dbMovie.getMovieDescription());
						movieShow.setMovieDuration(dbMovie.getMovieDuration());
						movieShow.setMovieLanguage(dbMovie.getLanguage());
						movieShow.setMovieName(dbMovie.getMovieName());
						movieShow.setGenre(dbMovie.getGenre1());
						movieShow.setScreenName(dbScreen.getScreenName());
						movieShow.setTheatre(dbTheatre);
						MovieShow show = showDao.addShow(movieShow);
						if (dbTheatre.getMovieShows().isEmpty()) {
//							this is the fisrt show
							List<MovieShow> list = new ArrayList<MovieShow>();
							list.add(movieShow);
							dbTheatre.setMovieShows(list);
							theatreDao.updateTheatre(theatreId, dbTheatre);
						} else {
//							show already present
							List<MovieShow> list = dbTheatre.getMovieShows();
							list.add(movieShow);
							dbTheatre.setMovieShows(list);
							theatreDao.updateTheatre(theatreId, dbTheatre);
						}
						ResponseStructure<MovieShow> structure = new ResponseStructure<MovieShow>();
						structure.setMessage("Added Show Successfully");
						structure.setStatus(HttpStatus.CREATED.value());
						structure.setData(this.modelMapper.map(movieShow, MovieShowDto.class));
						return new ResponseEntity<ResponseStructure<MovieShow>>(structure, HttpStatus.CREATED);
					} else {
						throw new MovieIdNotFoundException("Sorry Failed to add Show because movie id Not present");
					}
				} else {
					throw new ScreenAlreadyAllotedException("Sorry Failed to Add Show");
				}
			} else {
				throw new ScreenIdNotFoundException("Sorry failed to add Show because Screen Id not Found");
			}
		} else {
			throw new TheatreIdNotFoundException("Sorry failed to add Movie Show because Theatre Id Not Found");
		}
	}

	public ResponseEntity<ResponseStructure<MovieShow>> updateMovieShow(long showId, MovieShowDto showDto) {
		MovieShow movieShow = this.modelMapper.map(showDto, MovieShow.class);

		long screenId = movieShow.getScreenId();
		Screen dbScreen = screenDao.getScreenById(screenId);
		if (dbScreen != null) {

			long movieId = movieShow.getMovieId();
			Movie dbMovie = movieDao.getMovieById(movieId);
			if (dbMovie != null) {
				MovieShow dbMovieShowId = showDao.getShowById(showId);
				if (dbMovieShowId != null) {
					movieShow.setMovieDescription(dbMovie.getMovieDescription());
					movieShow.setMovieDuration(dbMovie.getMovieDuration());
					movieShow.setMovieLanguage(dbMovie.getLanguage());
					movieShow.setMovieName(dbMovie.getMovieName());
					movieShow.setGenre(dbMovie.getGenre1());
					movieShow.setScreenName(dbScreen.getScreenName());
					MovieShow dbMovieShow = showDao.updateShow(showId, movieShow);
					ResponseStructure<MovieShow> structure = new ResponseStructure<MovieShow>();
					structure.setMessage("Updated Show Successfully");
					structure.setStatus(HttpStatus.OK.value());
					structure.setData(this.modelMapper.map(movieShow, MovieShowDto.class));
					return new ResponseEntity<ResponseStructure<MovieShow>>(structure, HttpStatus.OK);
				} else {
					throw new MovieShowIdNotFoundException("Sorry failed to update Movie Show");
				}
			} else {
				throw new MovieIdNotFoundException("Sorry Failed to Update Movie Show");
			}
		} else {
			throw new ScreenIdNotFoundException("Sorry Failed to Update Movie Show");
		}

	}

	public ResponseEntity<ResponseStructure<MovieShow>> getMovieShowById(long showId) {
		MovieShow dbMovieShow = showDao.getShowById(showId);
		Movie dbMovie = movieDao.getMovieById(dbMovieShow.getMovieId());
		if (dbMovie != null) {
			dbMovieShow.setGenre(dbMovie.getGenre1());
		}
		if (dbMovieShow != null) {
			ResponseStructure<MovieShow> structure = new ResponseStructure<MovieShow>();
			structure.setMessage("Fetch Show Successfully");
			structure.setStatus(HttpStatus.OK.value());
			structure.setData(this.modelMapper.map(dbMovieShow, MovieShowDto.class));
			return new ResponseEntity<ResponseStructure<MovieShow>>(structure, HttpStatus.OK);
		} else {
			throw new MovieShowIdNotFoundException("Sorry failed to Fetch Movie Show");
		}
	}

	public ResponseEntity<ResponseStructure<MovieShowDto>> deleteMovieShowById(long showId) {
		MovieShow dbMovieShow = showDao.deleteShowById(showId);
		if (dbMovieShow != null) {
			ResponseStructure<MovieShowDto> structure = new ResponseStructure<MovieShowDto>();
			structure.setMessage("Delete Show Successfully");
			structure.setStatus(HttpStatus.OK.value());
			structure.setData(this.modelMapper.map(dbMovieShow, MovieShowDto.class));
			return new ResponseEntity<ResponseStructure<MovieShowDto>>(structure, HttpStatus.OK);
		} else {
			throw new MovieShowIdNotFoundException("Sorry failed to Delete Movie Show");
		}
	}
}
