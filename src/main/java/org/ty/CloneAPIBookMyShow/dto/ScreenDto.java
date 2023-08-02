package org.ty.CloneAPIBookMyShow.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.ty.CloneAPIBookMyShow.enums.ScreenAvailability;
import org.ty.CloneAPIBookMyShow.enums.ScreenStatus;
import org.ty.CloneAPIBookMyShow.enums.ScreenType;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ScreenDto {
	private long screenId;
	@NotNull(message = "Screen Name Can't be Null")
	@NotBlank(message = "Screen Name Can't be Blank")
	private String screenName;
//	screen type

	private ScreenType screenType;
//  screen availability

	private ScreenAvailability screenAvailability;
//	screen status

	private ScreenStatus screenStatus;
	private int noOfClassicSeat;
	private int noOfPlatinumSeat;
	private int noOfGoldSeat;
}
