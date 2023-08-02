package org.ty.CloneAPIBookMyShow.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressDto {
	private long addressId;
	private int flatNo;
	@NotNull(message = "Area can't be null")
	@NotBlank(message = "Area can't be Blank")
	private String area;
	@NotNull(message = "Landmark can't be null")
	@NotBlank(message = "Landmark can't be Blank")
	private String landmark;
	@NotNull(message = "City can't be null")
	@NotBlank(message = "City can't be Blank")
	private String city;
	@NotNull(message = "State can't be null")
	@NotBlank(message = "State can't be Blank")
	private String state;
	@NotNull(message = "Country can't be null")
	@NotBlank(message = "Country can't be Blank")
	private String country;
	
	private long pincode;
}
