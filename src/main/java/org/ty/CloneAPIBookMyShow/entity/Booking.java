package org.ty.CloneAPIBookMyShow.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.ty.CloneAPIBookMyShow.enums.BookingStatus;
import org.ty.CloneAPIBookMyShow.enums.SeatType;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Booking {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long bookingId;
	private LocalDateTime bookingFromTime;
	private LocalDateTime bookingTillTime;
	private long seatId;
// seat type
	private SeatType seatType;
	
//  booking Status
	private BookingStatus bookingStatus;
	private double seatPrice;
}
