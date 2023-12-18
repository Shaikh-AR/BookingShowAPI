package org.ty.CloneAPIBookMyShow.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.ty.CloneAPIBookMyShow.enums.ScreenAvailability;
import org.ty.CloneAPIBookMyShow.enums.ScreenStatus;
import org.ty.CloneAPIBookMyShow.enums.ScreenType;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Screen {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long screenId;
	private String screenName;
	
//	screen type
	private ScreenType screenType;
//  screen availability
	private ScreenAvailability screenAvailability;
//	screen status
	private ScreenStatus screenStatus;
	
	@OneToMany(mappedBy = "screen",cascade = CascadeType.ALL)
	private List<Seat> seats;
	
	private int noOfClassicSeat;
	private int noOfPlatinumSeat;
	private int noOfGoldSeat;
	
	@ManyToOne
	@JoinColumn
	@JsonIgnore
	private Theatre theatre;
}
