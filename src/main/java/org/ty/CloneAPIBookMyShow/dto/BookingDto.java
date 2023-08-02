package org.ty.CloneAPIBookMyShow.dto;

import java.time.LocalDateTime;

import org.ty.CloneAPIBookMyShow.enums.BookingStatus;
import org.ty.CloneAPIBookMyShow.enums.SeatType;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookingDto {
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
