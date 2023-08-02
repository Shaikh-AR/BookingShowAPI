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
import org.ty.CloneAPIBookMyShow.dto.CustomerDto;
import org.ty.CloneAPIBookMyShow.entity.Customer;
import org.ty.CloneAPIBookMyShow.service.CustomerService;
import org.ty.CloneAPIBookMyShow.util.ResponseStructure;

@RestController
@RequestMapping("/customer")
public class CustomerController {
	
	@Autowired
	private CustomerService service;
	
	@PostMapping
	public ResponseEntity<ResponseStructure<CustomerDto>> saveCustomer(@Valid @RequestBody Customer customer){
		return service.saveCustomer(customer);
	}
	
	@PutMapping
	public ResponseEntity<ResponseStructure<CustomerDto>> updateCustomer(@RequestParam long customerId,@Valid @RequestBody Customer customer){
		return service.updateCustomer(customerId,customer);
	}
	
	@GetMapping
	public ResponseEntity<ResponseStructure<CustomerDto>> getCustomer(@RequestParam long customerId){
		return service.getCustomerById(customerId);
	}
	
	@DeleteMapping
	public ResponseEntity<ResponseStructure<CustomerDto>> deleteCustomer(@RequestParam long customerId){
		return service.deleteCustomerById(customerId);
	}
	
}
