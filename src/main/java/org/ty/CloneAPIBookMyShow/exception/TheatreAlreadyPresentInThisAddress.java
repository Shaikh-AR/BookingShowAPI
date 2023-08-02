package org.ty.CloneAPIBookMyShow.exception;

import lombok.Getter;

@Getter
public class TheatreAlreadyPresentInThisAddress extends RuntimeException {

	private String message;

	public TheatreAlreadyPresentInThisAddress(String message) {
		super();
		this.message = message;
	}

}
