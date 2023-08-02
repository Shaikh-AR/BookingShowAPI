package org.ty.CloneAPIBookMyShow.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.ty.CloneAPIBookMyShow.dao.AddressDao;
import org.ty.CloneAPIBookMyShow.dto.AddressDto;
import org.ty.CloneAPIBookMyShow.entity.Address;
import org.ty.CloneAPIBookMyShow.exception.AddressIdNotFoundException;
import org.ty.CloneAPIBookMyShow.util.ResponseStructure;

@Service
public class AddressService {

	@Autowired
	private AddressDao addressDao;

	@Autowired
	private ModelMapper modelMapper;

	public ResponseEntity<ResponseStructure<Address>> saveAddress(AddressDto addressDto) {
		Address address = this.modelMapper.map(addressDto, Address.class);
		Address dbAddress = addressDao.saveAddress(address);
		ResponseStructure<Address> structure = new ResponseStructure<Address>();
		structure.setMessage("Address Saved Successfully");
		structure.setStatus(HttpStatus.CREATED.value());
		structure.setData(dbAddress);
		return new ResponseEntity<ResponseStructure<Address>>(structure, HttpStatus.CREATED);
	}

	public ResponseEntity<ResponseStructure<Address>> updateAddress(long addressId, AddressDto addressDto) {
		Address address = this.modelMapper.map(addressDto, Address.class);
		Address dbAddress = addressDao.updateAddress(addressId, address);
		if (dbAddress != null) {
			ResponseStructure<Address> structure = new ResponseStructure<Address>();
			structure.setMessage("Address Update Successfully");
			structure.setStatus(HttpStatus.OK.value());
			structure.setData(dbAddress);
			return new ResponseEntity<ResponseStructure<Address>>(structure, HttpStatus.OK);
		} else {
			throw new AddressIdNotFoundException("Sorry failed to Update the Address");
		}
	}

	public ResponseEntity<ResponseStructure<Address>> deleteAddressById(long addressId) {
		Address dbAddress = addressDao.deleteAddress(addressId);
		if (dbAddress != null) {
			ResponseStructure<Address> structure = new ResponseStructure<Address>();
			structure.setMessage("Address Deleted Successfully");
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setData(dbAddress);
			return new ResponseEntity<ResponseStructure<Address>>(structure, HttpStatus.FOUND);
		} else {
			throw new AddressIdNotFoundException("Sorry failed to Delete the Address");
		}
	}

	public ResponseEntity<ResponseStructure<Address>> getAddressById(long addressId) {
		Address dbAddress = addressDao.getAddressById(addressId);
		if (dbAddress != null) {
			ResponseStructure<Address> structure = new ResponseStructure<Address>();
			structure.setMessage("Address Fetch Successfully");
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setData(dbAddress);
			return new ResponseEntity<ResponseStructure<Address>>(structure, HttpStatus.FOUND);
		} else {
			throw new AddressIdNotFoundException("Sorry failed to Fetch the Address");
		}
	}
}
