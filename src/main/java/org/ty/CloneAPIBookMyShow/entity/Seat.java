package org.ty.CloneAPIBookMyShow.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.ty.CloneAPIBookMyShow.enums.SeatType;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Seat {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long seatId;
	// seat type
	private SeatType seatType;
	
	@ManyToOne
	@JoinColumn
	@JsonIgnore
	private Screen screen;
}
