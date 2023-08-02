package org.ty.CloneAPIBookMyShow.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TheatreDto {
	private long theatreId;
	@NotBlank(message = "Theatre Name Should Not be Blank")
	@NotNull(message = "Theatre Name Should Not be Null")
	private String theatreName;
}
