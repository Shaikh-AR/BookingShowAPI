package org.ty.CloneAPIBookMyShow.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.ty.CloneAPIBookMyShow.dao.CustomerDao;
import org.ty.CloneAPIBookMyShow.dto.CustomerDto;
import org.ty.CloneAPIBookMyShow.entity.Customer;
import org.ty.CloneAPIBookMyShow.exception.CustomerIdNotFoundException;
import org.ty.CloneAPIBookMyShow.util.ResponseStructure;

@Service
public class CustomerService {

	@Autowired
	private CustomerDao customerDao;

	@Autowired
	private ModelMapper modelMapper;

	public ResponseEntity<ResponseStructure<CustomerDto>> saveCustomer(Customer customer) {
		Customer dbCustomer = customerDao.saveCustomer(customer);
		CustomerDto customerDto = this.modelMapper.map(dbCustomer, CustomerDto.class);
		ResponseStructure<CustomerDto> structure = new ResponseStructure<CustomerDto>();
		structure.setMessage("Customer Saved Successfully");
		structure.setStatus(HttpStatus.CREATED.value());
		structure.setData(customerDto);
		return new ResponseEntity<ResponseStructure<CustomerDto>>(structure, HttpStatus.CREATED);
	}

	public ResponseEntity<ResponseStructure<CustomerDto>> updateCustomer(long customerId, Customer customer) {
		Customer dbCustomer = customerDao.updateCustomer(customerId, customer);
		CustomerDto customerDto = this.modelMapper.map(dbCustomer, CustomerDto.class);
		if (dbCustomer != null) {
			ResponseStructure<CustomerDto> structure = new ResponseStructure<CustomerDto>();
			structure.setMessage("Customer Updated Successfully");
			structure.setStatus(HttpStatus.OK.value());
			structure.setData(customerDto);
			return new ResponseEntity<ResponseStructure<CustomerDto>>(structure, HttpStatus.OK);
		} else {
			throw new CustomerIdNotFoundException("Sorry failed to Update the Customer");
		}
	}

	public ResponseEntity<ResponseStructure<CustomerDto>> getCustomerById(long customerId) {
		Customer dbCustomer = customerDao.getCustomerById(customerId);
		CustomerDto customerDto = this.modelMapper.map(dbCustomer, CustomerDto.class);
		if (dbCustomer != null) {
			ResponseStructure<CustomerDto> structure = new ResponseStructure<CustomerDto>();
			structure.setMessage("Customer Found Successfully");
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setData(customerDto);
			return new ResponseEntity<ResponseStructure<CustomerDto>>(structure, HttpStatus.FOUND);
		} else {
			throw new CustomerIdNotFoundException("Sorry failed to fetch the Customer");
		}
	}

	public ResponseEntity<ResponseStructure<CustomerDto>> deleteCustomerById(long customerId) {
		Customer dbCustomer = customerDao.deleteCustomerById(customerId);
		Customer customerDto = this.modelMapper.map(dbCustomer, Customer.class);
		if (dbCustomer != null) {
			ResponseStructure<CustomerDto> structure = new ResponseStructure<CustomerDto>();
			structure.setMessage("Customer Delete Successfully");
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setData(customerDto);
			return new ResponseEntity<ResponseStructure<CustomerDto>>(structure, HttpStatus.FOUND);
		} else {
			throw new CustomerIdNotFoundException("Sorry failed to Delete the Customer");
		}
	}

}
