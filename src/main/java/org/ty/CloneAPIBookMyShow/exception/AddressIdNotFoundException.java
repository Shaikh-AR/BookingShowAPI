package org.ty.CloneAPIBookMyShow.exception;

import lombok.Getter;

@Getter
public class AddressIdNotFoundException extends RuntimeException {
	private String message;

	public AddressIdNotFoundException(String message) {
		this.message = message;
	}
	
	
}
