package org.ty.CloneAPIBookMyShow.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OwnerDto {
	private long ownerId;
	private String ownerName;
	private long ownerPhoneNumber;
	private String ownerEmail;
}
