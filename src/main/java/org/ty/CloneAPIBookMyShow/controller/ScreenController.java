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
import org.ty.CloneAPIBookMyShow.dto.ScreenDto;
import org.ty.CloneAPIBookMyShow.service.ScreenService;
import org.ty.CloneAPIBookMyShow.util.ResponseStructure;

@RestController
@RequestMapping("/screen")
public class ScreenController {

	@Autowired
	private ScreenService service;

	@PostMapping
	public ResponseEntity<ResponseStructure<ScreenDto>> addScreen(@RequestParam long theatreId,
			@Valid @RequestBody ScreenDto screenDto) {
		return service.addScreen(theatreId, screenDto);
	}

	@PutMapping
	public ResponseEntity<ResponseStructure<ScreenDto>> updateScreen(@RequestParam long screenId,
			@Valid @RequestBody ScreenDto screenDto) {
		return service.updateScreen(screenId, screenDto);
	}

	@GetMapping
	public ResponseEntity<ResponseStructure<ScreenDto>> getScreenById(@RequestParam long screenId) {
		return service.getScreenById(screenId);
	}

	@DeleteMapping
	public ResponseEntity<ResponseStructure<ScreenDto>> deleteScreenById(@RequestParam long screenId) {
		return service.deleteScreenById(screenId);
	}

}
