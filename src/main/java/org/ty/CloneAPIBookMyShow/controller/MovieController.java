package org.ty.CloneAPIBookMyShow.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.ty.CloneAPIBookMyShow.dto.MovieDto;
import org.ty.CloneAPIBookMyShow.entity.Movie;
import org.ty.CloneAPIBookMyShow.service.MovieService;
import org.ty.CloneAPIBookMyShow.util.ResponseStructure;

@RestController
@RequestMapping("/movie")
public class MovieController {

	@Autowired
	private MovieService service;

	@PostMapping
	public ResponseEntity<ResponseStructure<MovieDto>> saveMovie(@RequestParam long houseId,
			@Valid @RequestBody MovieDto movieDto) {
		return service.saveMovie(houseId, movieDto);
	}
	
	@PutMapping
	public ResponseEntity<ResponseStructure<MovieDto>> updateMovie(@RequestParam long movieId,
			@Valid @RequestBody MovieDto movieDto) {
		return service.updateMovie(movieId, movieDto);
	}
	
	@GetMapping
	public ResponseEntity<ResponseStructure<MovieDto>> getMovieById(@RequestParam long movieId) {
		return service.getMovieById(movieId);
	}
	
	@DeleteMapping
	public ResponseEntity<ResponseStructure<MovieDto>> deleteMovieById(@RequestParam long movieId) {
		return service.deleteMovieById(movieId);
	}
	
	
}
