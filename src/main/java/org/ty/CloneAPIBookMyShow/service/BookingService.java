package org.ty.CloneAPIBookMyShow.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.ty.CloneAPIBookMyShow.dao.BookingDao;

@Service
public class BookingService {

	@Autowired
	private BookingDao bookingDao;
	
	@Autowired
	private ModelMapper modelMapper;
}
