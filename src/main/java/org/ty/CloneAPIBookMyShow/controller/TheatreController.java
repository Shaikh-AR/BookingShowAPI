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
import org.ty.CloneAPIBookMyShow.dto.TheatreDto;
import org.ty.CloneAPIBookMyShow.entity.Theatre;
import org.ty.CloneAPIBookMyShow.service.TheatreService;
import org.ty.CloneAPIBookMyShow.util.ResponseStructure;

@RestController
@RequestMapping("/theatre")
public class TheatreController {

	@Autowired
	private TheatreService service;
	
	@PostMapping
	public ResponseEntity<ResponseStructure<Theatre>> saveTheatre(@RequestParam long ownerId,@RequestParam long addressId,@Valid @RequestBody TheatreDto theatreDto){
		return service.saveTheatre(ownerId,addressId,theatreDto);
	}
	
	@PutMapping
	public ResponseEntity<ResponseStructure<Theatre>> updateTheatre(@RequestParam long theatreId,@Valid @RequestBody TheatreDto theatreDto){
		return service.updateTheatre(theatreId,theatreDto);
	}
	
	@GetMapping
	public ResponseEntity<ResponseStructure<Theatre>> getTheatreById(@RequestParam long theatreId){
		return service.getTheatreById(theatreId);
	}
	
	@DeleteMapping
	public ResponseEntity<ResponseStructure<Theatre>> deleteTheatreById(@RequestParam long theatreId){
		return service.deleteTheatreById(theatreId);
	}
	
}

