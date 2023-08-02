package org.ty.CloneAPIBookMyShow.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.ty.CloneAPIBookMyShow.enums.TicketStatus;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Ticket {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long ticketId;
	private double totalPrice;
	// ticket status
	private TicketStatus ticketStatus;
	@ManyToOne
	@JsonIgnore
	private MovieShow movieShow;
	
	@OneToMany
	private List<Booking> bookings;
	
	@ManyToOne
	@JoinColumn
	private Customer customer;
}
