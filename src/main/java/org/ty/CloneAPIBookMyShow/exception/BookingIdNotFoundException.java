package org.ty.CloneAPIBookMyShow.exception;

import lombok.Getter;

@Getter
public class BookingIdNotFoundException extends RuntimeException{

	private String message;

	public BookingIdNotFoundException(String message) {
		super();
		this.message = message;
	}
	
	
}
