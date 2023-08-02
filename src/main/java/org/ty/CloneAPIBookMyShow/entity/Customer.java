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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Customer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long customerId;
	@NotNull(message = "Customer Name can't be null")
	@NotBlank(message = "Customer Name can't be Blank")
	private String customerName;
	@Min(6000000001l)
	@Max(9999999999l)
	private long customerPhone;
	@Email
	@NotNull(message = "Email can't be null")
	@NotBlank(message = "Email can't be Blank")
	private String customerEmail;
	@Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&-+=()])(?=\\S+$).{8,20}$",message = "Password is not Valid Please Used Combination of small and Capital Character and number with Special Character")
	private String customerPassword;
	
	@OneToMany(mappedBy = "customer")
	@JsonIgnore
	private List<Ticket> tickets;
}
