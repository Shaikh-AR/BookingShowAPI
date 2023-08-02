package org.ty.CloneAPIBookMyShow.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.ty.CloneAPIBookMyShow.Repository.BookingRepo;
import org.ty.CloneAPIBookMyShow.entity.Booking;

@Repository
public class BookingDao {

	@Autowired
	private BookingRepo repo;
	
	public void saveBooking(Booking booking) {
		repo.save(booking);
	}
}
