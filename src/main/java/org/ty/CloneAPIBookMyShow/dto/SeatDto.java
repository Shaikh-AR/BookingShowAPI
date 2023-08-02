package org.ty.CloneAPIBookMyShow.dto;

import org.ty.CloneAPIBookMyShow.enums.SeatType;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SeatDto {
	private long seatId;
	// seat type
	private SeatType seatType;
}
