package org.ty.CloneAPIBookMyShow.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.ty.CloneAPIBookMyShow.entity.Ticket;

public interface TicketRepo extends JpaRepository<Ticket, Long> {

}
