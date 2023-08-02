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
import org.ty.CloneAPIBookMyShow.dto.OwnerDto;
import org.ty.CloneAPIBookMyShow.entity.Owner;
import org.ty.CloneAPIBookMyShow.service.OwnerService;
import org.ty.CloneAPIBookMyShow.util.ResponseStructure;

@RestController
@RequestMapping("/owner")
public class OwnerController {
	@Autowired
	private OwnerService service;
	
	@PostMapping
	public ResponseEntity<ResponseStructure<OwnerDto>> saveOwner(@Valid @RequestBody Owner owner){
		return service.saveOwner(owner);
	}
	
	@GetMapping
	public ResponseEntity<ResponseStructure<OwnerDto>> findOwnerById(@Valid @RequestParam long ownerId){
		return service.findOwnerById(ownerId);
	}
	
	@DeleteMapping
	public ResponseEntity<ResponseStructure<OwnerDto>> deleteOwnerById(@Valid @RequestParam long ownerId){
		return service.deleteOwnerById(ownerId);
	} 
	
	@PutMapping
	public ResponseEntity<ResponseStructure<OwnerDto>> updateOwnerById(@Valid @RequestParam long ownerId,@Valid @RequestBody Owner owner){
		return service.updateOwnerById(ownerId,owner);
	} 
}
