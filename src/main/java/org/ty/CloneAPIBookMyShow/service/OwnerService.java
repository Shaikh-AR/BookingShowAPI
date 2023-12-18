package org.ty.CloneAPIBookMyShow.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.ty.CloneAPIBookMyShow.dao.OwnerDao;
import org.ty.CloneAPIBookMyShow.dto.OwnerDto;
import org.ty.CloneAPIBookMyShow.entity.Owner;
import org.ty.CloneAPIBookMyShow.exception.OwnerIdNotFoundException;
import org.ty.CloneAPIBookMyShow.util.ResponseStructure;

@Service
public class OwnerService {
	@Autowired
	private OwnerDao ownerDao;

	public ResponseEntity<ResponseStructure<OwnerDto>> saveOwner(Owner owner) {
		Owner dbOwner = ownerDao.saveOwner(owner);
		OwnerDto dto = new OwnerDto();
		dto.setOwnerId(owner.getOwnerId());
		dto.setOwnerName(owner.getOwnerName());
		dto.setOwnerEmail(owner.getOwnerEmail());
		dto.setOwnerPhoneNumber(owner.getOwnerPhoneNumber());

		ResponseStructure<OwnerDto> structure = new ResponseStructure<OwnerDto>();
		structure.setMessage("Owner saved Successfully");
		structure.setStatus(HttpStatus.CREATED.value());
		structure.setData(dto);
		return new ResponseEntity<ResponseStructure<OwnerDto>>(structure, HttpStatus.CREATED);
	}

	public ResponseEntity<ResponseStructure<OwnerDto>> findOwnerById(long ownerId) {
		Owner dbOwner = ownerDao.findOwnerById(ownerId);
		if (dbOwner != null) {
			OwnerDto dto = new OwnerDto();
			dto.setOwnerId(dbOwner.getOwnerId());
			dto.setOwnerName(dbOwner.getOwnerName());
			dto.setOwnerEmail(dbOwner.getOwnerEmail());
			dto.setOwnerPhoneNumber(dbOwner.getOwnerPhoneNumber());

			ResponseStructure<OwnerDto> structure = new ResponseStructure<OwnerDto>();
			structure.setMessage("OwnerData Fetch  Successfully");
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setData(dto);
			return new ResponseEntity<ResponseStructure<OwnerDto>>(structure, HttpStatus.FOUND);

		} else {
			// raise ownerIdNotFoundException
			throw new OwnerIdNotFoundException("Sorry Owner Data Not Found");
		}
	}

	public ResponseEntity<ResponseStructure<OwnerDto>> deleteOwnerById(long ownerId) {
		Owner dbOwner = ownerDao.deleteOwnerById(ownerId);
		if (dbOwner != null) {
			OwnerDto dto = new OwnerDto();
			dto.setOwnerId(dbOwner.getOwnerId());
			dto.setOwnerName(dbOwner.getOwnerName());
			dto.setOwnerEmail(dbOwner.getOwnerEmail());
			dto.setOwnerPhoneNumber(dbOwner.getOwnerPhoneNumber());

			ResponseStructure<OwnerDto> structure = new ResponseStructure<OwnerDto>();
			structure.setMessage("OwnerData delete  Successfully");
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setData(dto);
			return new ResponseEntity<ResponseStructure<OwnerDto>>(structure, HttpStatus.FOUND);

		} else {
			// raise ownerIdNotFound Exception
			throw new OwnerIdNotFoundException("Sorry Owner Not Deleted");
		}
	}

	public ResponseEntity<ResponseStructure<OwnerDto>> updateOwnerById(long ownerId, Owner owner) {
		Owner dbOwner = ownerDao.updateOwnerById(ownerId, owner);
		if (dbOwner != null) {
			OwnerDto dto = new OwnerDto();
			dto.setOwnerId(dbOwner.getOwnerId());
			dto.setOwnerName(dbOwner.getOwnerName());
			dto.setOwnerEmail(dbOwner.getOwnerEmail());
			dto.setOwnerPhoneNumber(dbOwner.getOwnerPhoneNumber());

			ResponseStructure<OwnerDto> structure = new ResponseStructure<OwnerDto>();
			structure.setMessage("OwnerData Update Successfully");
			structure.setStatus(HttpStatus.OK.value());
			structure.setData(dto);
			return new ResponseEntity<ResponseStructure<OwnerDto>>(structure, HttpStatus.OK);

		} else {
			// raise ownerIdNotFound Exception
			throw new OwnerIdNotFoundException("Sorry Owner Not Updated");
		}
	}

}
