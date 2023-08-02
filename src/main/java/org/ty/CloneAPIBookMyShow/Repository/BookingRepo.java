package org.ty.CloneAPIBookMyShow.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.ty.CloneAPIBookMyShow.entity.Booking;

public interface BookingRepo extends JpaRepository<Booking, Long> {

}
