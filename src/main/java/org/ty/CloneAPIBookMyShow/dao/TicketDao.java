package org.ty.CloneAPIBookMyShow.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.ty.CloneAPIBookMyShow.Repository.TicketRepo;
import org.ty.CloneAPIBookMyShow.entity.Ticket;

@Repository
public class TicketDao {

	@Autowired
	private TicketRepo repo;

	public Ticket saveTicket(Ticket ticket) {
		return repo.save(ticket);
	}

	public Ticket getTicketById(long ticketId) {
		if(repo.findById(ticketId).isPresent()) {
			return repo.findById(ticketId).get();
		}
		return null;
	}
}
