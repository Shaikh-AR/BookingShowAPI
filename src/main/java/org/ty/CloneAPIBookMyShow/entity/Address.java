package org.ty.CloneAPIBookMyShow.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Address {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long addressId;
	private int flatNo;
	private String area;
	private String landmark;
	private String city;
	private String state;
	private String country;
	private long pincode;

	@OneToOne(mappedBy = "address")
	@JsonIgnore
	private Theatre theatre;
}
