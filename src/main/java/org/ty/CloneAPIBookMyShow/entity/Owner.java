package org.ty.CloneAPIBookMyShow.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Owner {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long ownerId;
	@NotBlank(message = "Owner Name Should Not be Null")
	@NotBlank(message = "Owner Name Should Not be Blank")
	private String ownerName;
	@Min(6000000000l)
	@Max(9999999999l)
	private long ownerPhoneNumber;
	@Email(regexp = "^(.+)@(\\S+)$",message = "Email is Not Valid please Wright in this format alphabet@gmail.com")
	private String ownerEmail;
	@Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&-+=()])(?=\\S+$).{8,20}$",message = "Password is not Valid Please Used Combination of small and Capital Character and number with Special Character")
	private String ownerPassword;

	@OneToMany(mappedBy = "owner")
	@JsonIgnore
	private List<ProductionHouse> houses;

	@OneToMany(mappedBy = "owner")
	@JsonIgnore
	private List<Theatre> theatres;
}
