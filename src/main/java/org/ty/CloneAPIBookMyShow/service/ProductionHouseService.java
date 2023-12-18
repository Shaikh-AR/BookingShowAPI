package org.ty.CloneAPIBookMyShow.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.ty.CloneAPIBookMyShow.dao.OwnerDao;
import org.ty.CloneAPIBookMyShow.dao.ProductionHouseDao;
import org.ty.CloneAPIBookMyShow.dto.ProductionHouseDto;
import org.ty.CloneAPIBookMyShow.entity.Owner;
import org.ty.CloneAPIBookMyShow.entity.ProductionHouse;
import org.ty.CloneAPIBookMyShow.exception.OwnerIdNotFoundException;
import org.ty.CloneAPIBookMyShow.exception.ProductionHouseIdNotFoundException;
import org.ty.CloneAPIBookMyShow.util.ResponseStructure;

@Service
public class ProductionHouseService {

	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private ProductionHouseDao houseDao;
	@Autowired
	private OwnerDao ownerDao;

	public ResponseEntity<ResponseStructure<ProductionHouse>> saveProductionHouse(long ownerId,
			ProductionHouseDto houseDto) {
		Owner dbOwner = ownerDao.findOwnerById(ownerId);
		if (dbOwner != null) {
// 			that owner exist and then we can establish the Production House
			ProductionHouse house = this.modelMapper.map(houseDto, ProductionHouse.class);
			house.setOwner(dbOwner);
			if (dbOwner.getHouses().isEmpty()) {
				List<ProductionHouse> list = new ArrayList<ProductionHouse>();
				list.add(house);
				dbOwner.setHouses(list);
			} else {
				List<ProductionHouse> list = dbOwner.getHouses();
				list.add(house);
				dbOwner.setHouses(list);
			}
			ProductionHouse dbProductionHouse = houseDao.saveProductionHouse(house);
			ResponseStructure<ProductionHouse> structure = new ResponseStructure<ProductionHouse>();
			structure.setMessage("ProductionHouse Added Successfully");
			structure.setStatus(HttpStatus.CREATED.value());
			structure.setData(dbProductionHouse);
			return new ResponseEntity<ResponseStructure<ProductionHouse>>(structure, HttpStatus.CREATED);

		} else {
// 			Raise the Exception ownerId is not Present
			throw new OwnerIdNotFoundException("Failed to add Production House");
		}
	}

	public ResponseEntity<ResponseStructure<ProductionHouse>> updateProductionHouse(long houseId,
			ProductionHouseDto houseDto) {
		ProductionHouse house = this.modelMapper.map(houseDto, ProductionHouse.class);
		ProductionHouse dbHouse = houseDao.updateProductionHouse(houseId, house);
		if (dbHouse != null) {
			ResponseStructure<ProductionHouse> structure = new ResponseStructure<ProductionHouse>();
			structure.setMessage("Production House Update Successfully");
			structure.setStatus(HttpStatus.OK.value());
			structure.setData(dbHouse);
			return new ResponseEntity<ResponseStructure<ProductionHouse>>(structure, HttpStatus.OK);
		} else {
			throw new ProductionHouseIdNotFoundException("Sorry failed to update Production House");
		}
	}

	public ResponseEntity<ResponseStructure<ProductionHouse>> getProductionHouseById(long houseId) {
		ProductionHouse dbHouse=houseDao.getProductionHouseById(houseId);
		if(dbHouse!=null) {
			ResponseStructure<ProductionHouse> structure = new ResponseStructure<ProductionHouse>();
			structure.setMessage("Production House Fetch Successfully");
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setData(dbHouse);
			return new ResponseEntity<ResponseStructure<ProductionHouse>>(structure, HttpStatus.FOUND);
		} else {
			throw new ProductionHouseIdNotFoundException("Sorry failed to Fetch Production House");
		}
		
	}

	public ResponseEntity<ResponseStructure<ProductionHouse>> deleteProductionHouseById(long houseId) {
		ProductionHouse dbHouse=houseDao.deleteProductionHouseById(houseId);
		if(dbHouse!=null) {
			ResponseStructure<ProductionHouse> structure = new ResponseStructure<ProductionHouse>();
			structure.setMessage("Production House delete Successfully");
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setData(dbHouse);
			return new ResponseEntity<ResponseStructure<ProductionHouse>>(structure, HttpStatus.FOUND);
		} else {
			throw new ProductionHouseIdNotFoundException("Sorry failed to delete Production House");
		}
	}
}
