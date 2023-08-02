package org.ty.CloneAPIBookMyShow.dto;

import java.time.LocalDateTime;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.ty.CloneAPIBookMyShow.enums.ShowStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MovieShowDto {
	private long showId;
	private LocalDateTime showStartTime;
	private LocalDateTime showEndTime;
//  Show Status
	private ShowStatus showStatus;
	@NotBlank(message = "Show Location Can't be Blank")
	@NotNull(message = "Show Location Can't be Null")
	private String showLocation;

	private long movieId;
	private String movieName;
//  genre
	private String genre;
	private LocalDateTime movieDuration;
	private String movieDescription;
	private String movieLanguage;

	private long screenId;
	private String screenName;
	private double classicSeatPrice;
	private double goldSeatPrice;
	private double premiumSeatPrice;
}
