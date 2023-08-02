package org.ty.CloneAPIBookMyShow.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.ty.CloneAPIBookMyShow.dao.MovieDao;
import org.ty.CloneAPIBookMyShow.dao.ProductionHouseDao;
import org.ty.CloneAPIBookMyShow.dto.MovieDto;
import org.ty.CloneAPIBookMyShow.entity.Movie;
import org.ty.CloneAPIBookMyShow.entity.ProductionHouse;
import org.ty.CloneAPIBookMyShow.exception.MovieIdNotFoundException;
import org.ty.CloneAPIBookMyShow.exception.ProductionHouseIdNotFoundException;
import org.ty.CloneAPIBookMyShow.util.ResponseStructure;

@Service
public class MovieService {

	@Autowired
	private MovieDao movieDao;

	@Autowired
	private ProductionHouseDao houseDao;

	@Autowired
	private ModelMapper modelMapper;

	public ResponseEntity<ResponseStructure<MovieDto>> saveMovie(long houseId, MovieDto movieDto) {
		ProductionHouse house = houseDao.getProductionHouseById(houseId);
		if (house != null) {
//			add movie
			Movie movie = this.modelMapper.map(movieDto, Movie.class);
			movie.setProductionHouse(house);
			Movie dbMovie = movieDao.saveMovie(movie);
//			update Production House
			if (house.getMovies().isEmpty()) {
				List<Movie> list = new ArrayList<Movie>();
				list.add(movie);
				house.setMovies(list);
				houseDao.updateProductionHouse(houseId, house);
			} else {
				List<Movie> list = house.getMovies();
				list.add(movie);
				house.setMovies(list);
			}
			
			ResponseStructure<MovieDto> structure = new ResponseStructure<MovieDto>();
			structure.setMessage("Movie saved Successfully");
			structure.setStatus(HttpStatus.CREATED.value());
			structure.setData(this.modelMapper.map(dbMovie, MovieDto.class));
			return new ResponseEntity<ResponseStructure<MovieDto>>(structure, HttpStatus.CREATED);
		} else {
			throw new ProductionHouseIdNotFoundException("Sorry failed to add Movie");
		}
	}

	public ResponseEntity<ResponseStructure<MovieDto>> updateMovie(long movieId, MovieDto movieDto) {
		Movie movie = this.modelMapper.map(movieDto, Movie.class);
		Movie dbMovie = movieDao.updateMovie(movieId, movie);
		if (dbMovie != null) {
			ResponseStructure<MovieDto> structure = new ResponseStructure<MovieDto>();
			structure.setMessage("Movie Update Successfully");
			structure.setStatus(HttpStatus.OK.value());
			structure.setData(this.modelMapper.map(dbMovie, MovieDto.class));
			return new ResponseEntity<ResponseStructure<MovieDto>>(structure, HttpStatus.OK);
		} else {
			throw new MovieIdNotFoundException("Sorry failed to update Movie");
		}
	}

	public ResponseEntity<ResponseStructure<MovieDto>> getMovieById(long movieId) {
		Movie dbMovie = movieDao.getMovieById(movieId);
		if (dbMovie != null) {
			ResponseStructure<MovieDto> structure = new ResponseStructure<MovieDto>();
			structure.setMessage("Movie Fetch Successfully");
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setData(this.modelMapper.map(dbMovie, MovieDto.class));
			return new ResponseEntity<ResponseStructure<MovieDto>>(structure, HttpStatus.FOUND);
		} else {
			throw new MovieIdNotFoundException("Sorry failed to Fetch Movie");
		}
	}

	public ResponseEntity<ResponseStructure<MovieDto>> deleteMovieById(long movieId) {
		Movie dbMovie = movieDao.deleteMovieById(movieId);
		if (dbMovie != null) {
			ResponseStructure<MovieDto> structure = new ResponseStructure<MovieDto>();
			structure.setMessage("Movie Delete Successfully");
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setData(this.modelMapper.map(dbMovie, MovieDto.class));
			return new ResponseEntity<ResponseStructure<MovieDto>>(structure, HttpStatus.FOUND);
		} else {
			throw new MovieIdNotFoundException("Sorry failed to Delete Movie");
		}
	}
}
