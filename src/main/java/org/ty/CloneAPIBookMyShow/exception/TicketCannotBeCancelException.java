package org.ty.CloneAPIBookMyShow.exception;

import lombok.Getter;

@Getter
public class TicketCannotBeCancelException extends RuntimeException {

	private String message;

	public TicketCannotBeCancelException(String message) {
		super();
		this.message = message;
	}
	
	
}
