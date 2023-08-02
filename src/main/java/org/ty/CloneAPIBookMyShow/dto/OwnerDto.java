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
	
	public long getOwnerId() {
		return ownerId;
	}
	public void setOwnerId(long ownerId) {
		this.ownerId = ownerId;
	}
	public String getOwnerName() {
		return ownerName;
	}
	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}
	public long getOwnerPhoneNumber() {
		return ownerPhoneNumber;
	}
	public void setOwnerPhoneNumber(long ownerPhoneNumber) {
		this.ownerPhoneNumber = ownerPhoneNumber;
	}
	public String getOwnerEmail() {
		return ownerEmail;
	}
	public void setOwnerEmail(String ownerEmail) {
		this.ownerEmail = ownerEmail;
	}
}
