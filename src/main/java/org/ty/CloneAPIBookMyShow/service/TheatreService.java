package org.ty.CloneAPIBookMyShow.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.ty.CloneAPIBookMyShow.dao.AddressDao;
import org.ty.CloneAPIBookMyShow.dao.OwnerDao;
import org.ty.CloneAPIBookMyShow.dao.TheatreDao;
import org.ty.CloneAPIBookMyShow.dto.TheatreDto;
import org.ty.CloneAPIBookMyShow.entity.Address;
import org.ty.CloneAPIBookMyShow.entity.Owner;
import org.ty.CloneAPIBookMyShow.entity.ProductionHouse;
import org.ty.CloneAPIBookMyShow.entity.Theatre;
import org.ty.CloneAPIBookMyShow.exception.AddressIdNotFoundException;
import org.ty.CloneAPIBookMyShow.exception.OwnerIdNotFoundException;
import org.ty.CloneAPIBookMyShow.exception.TheatreAlreadyPresentInThisAddress;
import org.ty.CloneAPIBookMyShow.exception.TheatreIdNotFoundException;
import org.ty.CloneAPIBookMyShow.util.ResponseStructure;

@Service
public class TheatreService {

	@Autowired
	private TheatreDao theatreDao;

	@Autowired
	private OwnerDao ownerDao;

	@Autowired
	private AddressDao addressDao;

	@Autowired
	private ModelMapper modelMapper;

	public ResponseEntity<ResponseStructure<Theatre>> saveTheatre(long ownerId, long addressId, TheatreDto theatreDto) {
		Owner owner = ownerDao.findOwnerById(ownerId);
		if (owner != null) {
//			Proceed your Function Remaining
			Address address = addressDao.getAddressById(addressId);
			if (address != null) {
//				process the things
				Theatre theatreAddress = address.getTheatre();
				if (theatreAddress != null) {
					throw new TheatreAlreadyPresentInThisAddress("Sorry Address is mapped to Other Theatre");
				}
				Theatre theatre = this.modelMapper.map(theatreDto, Theatre.class);
				theatre.setAddress(address);
//				Update Address
				address.setTheatre(theatre);
				theatre.setOwner(owner);
//				Update the owner
				if (owner.getTheatres().isEmpty()) {
					List<Theatre> list = new ArrayList<Theatre>();
					list.add(theatre);
					owner.setTheatres(list);
				} else {
					List<Theatre> list = owner.getTheatres();
					list.add(theatre);
					owner.setTheatres(list);
				}

//				add theatre
				Theatre dbTheatre = theatreDao.saveTheatre(theatre);
				ResponseStructure<Theatre> structure = new ResponseStructure<Theatre>();
				structure.setMessage("Theatre Added Successfully");
				structure.setStatus(HttpStatus.CREATED.value());
				structure.setData(dbTheatre);
				return new ResponseEntity<ResponseStructure<Theatre>>(structure, HttpStatus.CREATED);

			} else {
				throw new AddressIdNotFoundException("sorry failed to find a Address");
			}
		} else {
//			Raise a Exception ownerIdnot Found Exception
			throw new OwnerIdNotFoundException("sorry failed to add a Theatre");
		}
	}

	public ResponseEntity<ResponseStructure<Theatre>> updateTheatre(long theatreId, TheatreDto theatreDto) {
		Theatre theatre = this.modelMapper.map(theatreDto, Theatre.class);
		Theatre dbTheatre = theatreDao.updateTheatre(theatreId, theatre);
		if (dbTheatre != null) {
			ResponseStructure<Theatre> structure = new ResponseStructure<Theatre>();
			structure.setMessage("Theatre Update Successfully");
			structure.setStatus(HttpStatus.OK.value());
			structure.setData(dbTheatre);
			return new ResponseEntity<ResponseStructure<Theatre>>(structure, HttpStatus.OK);
		} else {
//			Raise Exception
			throw new TheatreIdNotFoundException("Sorry Theatre Id not found");
		}
	}

	public ResponseEntity<ResponseStructure<Theatre>> getTheatreById(long theatreId) {
		Theatre dbTheatre = theatreDao.getTheatreById(theatreId);
		if (dbTheatre != null) {
			ResponseStructure<Theatre> structure = new ResponseStructure<Theatre>();
			structure.setMessage("Theatre Fetch Successfully");
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setData(dbTheatre);
			return new ResponseEntity<ResponseStructure<Theatre>>(structure, HttpStatus.FOUND);
		} else {
//			Raise Exception
			throw new TheatreIdNotFoundException("Sorry Theatre Id not found");
		}
	}

	public ResponseEntity<ResponseStructure<Theatre>> deleteTheatreById(long theatreId) {
		Theatre dbTheatre = theatreDao.deleteTheatreById(theatreId);
		if (dbTheatre != null) {
			ResponseStructure<Theatre> structure = new ResponseStructure<Theatre>();
			structure.setMessage("Theatre Delete Successfully");
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setData(dbTheatre);
			return new ResponseEntity<ResponseStructure<Theatre>>(structure, HttpStatus.FOUND);
		} else {
//			Raise Exception
			throw new TheatreIdNotFoundException("Sorry Theatre Id not found");
		}
	}

}
