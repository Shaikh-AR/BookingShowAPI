package org.ty.CloneAPIBookMyShow.exception;

import lombok.Getter;

@Getter
public class MovieShowIdNotFoundException extends RuntimeException {

	private String message;

	public MovieShowIdNotFoundException(String message) {
		super();
		this.message = message;
	}
	
}
