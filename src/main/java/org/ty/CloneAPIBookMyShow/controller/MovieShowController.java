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
import org.ty.CloneAPIBookMyShow.dto.MovieShowDto;
import org.ty.CloneAPIBookMyShow.entity.MovieShow;
import org.ty.CloneAPIBookMyShow.service.MovieShowService;
import org.ty.CloneAPIBookMyShow.util.ResponseStructure;

@RestController
@RequestMapping("/movieshow")
public class MovieShowController {
	@Autowired
	private MovieShowService service;

	@PostMapping
	public ResponseEntity<ResponseStructure<MovieShow>> saveMovieShow(@RequestParam long theatreId,
			@Valid @RequestBody MovieShowDto showDto) {
		return service.addMovieShow(theatreId, showDto);
	}

	@PutMapping
	public ResponseEntity<ResponseStructure<MovieShow>> updateMovieShow(@RequestParam long showId,
			@Valid @RequestBody MovieShowDto showDto) {
		return service.updateMovieShow(showId, showDto);
	}
	
	@GetMapping
	public ResponseEntity<ResponseStructure<MovieShow>> getMovieShowById(@RequestParam long showId) {
		return service.getMovieShowById(showId);
	}
	
	@DeleteMapping
	public ResponseEntity<ResponseStructure<MovieShowDto>> deleteMovieShowById(@RequestParam long showId) {
		return service.deleteMovieShowById(showId);
	}
	
	
}
