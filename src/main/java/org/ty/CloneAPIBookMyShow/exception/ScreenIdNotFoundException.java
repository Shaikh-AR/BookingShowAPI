package org.ty.CloneAPIBookMyShow.exception;

import lombok.Getter;

@Getter
public class ScreenIdNotFoundException extends RuntimeException{

	private String message;

	public ScreenIdNotFoundException(String message) {
		super();
		this.message = message;
	}

}
