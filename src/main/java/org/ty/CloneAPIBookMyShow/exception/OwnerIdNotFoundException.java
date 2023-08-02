package org.ty.CloneAPIBookMyShow.exception;

import lombok.Getter;

@Getter
public class OwnerIdNotFoundException extends RuntimeException{
	private String message;
	
	public OwnerIdNotFoundException(String message) {
		this.message=message;
	}
}
