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
import org.ty.CloneAPIBookMyShow.dto.AddressDto;
import org.ty.CloneAPIBookMyShow.entity.Address;
import org.ty.CloneAPIBookMyShow.service.AddressService;
import org.ty.CloneAPIBookMyShow.util.ResponseStructure;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.val;

@RestController
@RequestMapping("/address")
public class AddressController {
	
	@Autowired
	private AddressService service;
	
	@ApiOperation(value = "Save Address",notes = "Api is Used to save the Address")
	@ApiResponses(value = {@ApiResponse(code = 201,message = "Successfully Created")})
	@PostMapping
	public ResponseEntity<ResponseStructure<Address>> saveAddress(@Valid @RequestBody AddressDto addressDto){
		return service.saveAddress(addressDto);
	}
	
	@ApiOperation(value = "Update Address",notes = "Api is Used to Update the Address")
	@ApiResponses(value = {@ApiResponse(code = 200,message = "Successfully Update"),
						   @ApiResponse(code = 404,message = "Id not found for address")})
	@PutMapping
	public ResponseEntity<ResponseStructure<Address>> updateAddress(@RequestParam long addressId,@Valid @RequestBody AddressDto addressDto){
		return service.updateAddress(addressId,addressDto);
	}
	
	@DeleteMapping
	public ResponseEntity<ResponseStructure<Address>> deleteAddressById(@RequestParam long addressId){
		return service.deleteAddressById(addressId);
	}
	
	@GetMapping
	public ResponseEntity<ResponseStructure<Address>> getAddressById(@RequestParam long addressId){
		return service.getAddressById(addressId);
	}
}
