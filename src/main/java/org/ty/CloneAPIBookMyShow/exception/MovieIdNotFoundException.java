package org.ty.CloneAPIBookMyShow.exception;

import lombok.Getter;

@Getter
public class MovieIdNotFoundException extends RuntimeException {

	private String message;

	public MovieIdNotFoundException(String message) {
		super();
		this.message = message;
	}
	
	
}
