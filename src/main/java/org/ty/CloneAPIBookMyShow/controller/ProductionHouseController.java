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
import org.ty.CloneAPIBookMyShow.dto.ProductionHouseDto;
import org.ty.CloneAPIBookMyShow.entity.ProductionHouse;
import org.ty.CloneAPIBookMyShow.service.ProductionHouseService;
import org.ty.CloneAPIBookMyShow.util.ResponseStructure;

@RestController
@RequestMapping("/house")
public class ProductionHouseController {
	@Autowired
	private ProductionHouseService service;

	@PostMapping
	public ResponseEntity<ResponseStructure<ProductionHouse>> saveProductionHouse(@RequestParam long ownerId,@Valid
			@RequestBody ProductionHouseDto houseDto) {
		return service.saveProductionHouse(ownerId, houseDto);
	}

	@PutMapping
	public ResponseEntity<ResponseStructure<ProductionHouse>> updateProductionHouse(@RequestParam long houseId,
			@Valid @RequestBody ProductionHouseDto houseDto) {
		return service.updateProductionHouse(houseId, houseDto);
	}

	@GetMapping
	public ResponseEntity<ResponseStructure<ProductionHouse>> getProductionHouseById(@RequestParam long houseId) {
		return service.getProductionHouseById(houseId);
	}
	
	@DeleteMapping
	public ResponseEntity<ResponseStructure<ProductionHouse>> deleteProductionHouseById(@Valid @RequestParam long houseId) {
		return service.deleteProductionHouseById(houseId);
	}
	
}
