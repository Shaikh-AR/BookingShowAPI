package org.ty.CloneAPIBookMyShow.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.ty.CloneAPIBookMyShow.dao.BookingDao;
import org.ty.CloneAPIBookMyShow.dao.CustomerDao;
import org.ty.CloneAPIBookMyShow.dao.MovieShowDao;
import org.ty.CloneAPIBookMyShow.dao.SeatDao;
import org.ty.CloneAPIBookMyShow.dao.TicketDao;
import org.ty.CloneAPIBookMyShow.entity.Booking;
import org.ty.CloneAPIBookMyShow.entity.Customer;
import org.ty.CloneAPIBookMyShow.entity.MovieShow;
import org.ty.CloneAPIBookMyShow.entity.Seat;
import org.ty.CloneAPIBookMyShow.entity.Ticket;
import org.ty.CloneAPIBookMyShow.enums.BookingStatus;
import org.ty.CloneAPIBookMyShow.enums.SeatType;
import org.ty.CloneAPIBookMyShow.enums.ShowStatus;
import org.ty.CloneAPIBookMyShow.enums.TicketStatus;
import org.ty.CloneAPIBookMyShow.exception.CustomerIdNotFoundException;
import org.ty.CloneAPIBookMyShow.exception.MovieShowIdNotFoundException;
import org.ty.CloneAPIBookMyShow.exception.SeatIdNotFoundException;
import org.ty.CloneAPIBookMyShow.exception.ShowIsNotActiveException;
import org.ty.CloneAPIBookMyShow.exception.TicketAlreadyCancelledException;
import org.ty.CloneAPIBookMyShow.exception.TicketAlreadyExpiredException;
import org.ty.CloneAPIBookMyShow.exception.TicketCannotBeCancelException;
import org.ty.CloneAPIBookMyShow.exception.TicketIdNotFoundException;
import org.ty.CloneAPIBookMyShow.util.ResponseStructure;

@Service
public class TicketService {

	@Autowired
	private TicketDao ticketDao;

	@Autowired
	private CustomerDao customerDao;

	@Autowired
	private MovieShowDao showDao;

	@Autowired
	private SeatDao seatDao;

	@Autowired
	private BookingDao bookingDao;

	@Autowired
	private ModelMapper modelMapper;

	public ResponseEntity<ResponseStructure<Ticket>> saveTicket(long customerId, long showId, long seatId) {
		Customer dbCustomer = customerDao.getCustomerById(customerId);
		Ticket ticket = new Ticket();
		MovieShow dbMovieShow;
		if (dbCustomer != null) {
			ticket.setCustomer(dbCustomer);
			dbMovieShow = showDao.getShowById(showId);
			if (dbMovieShow != null) {
				if (dbMovieShow.getShowStatus().equals(ShowStatus.ACTIVE)) {
					ticket.setMovieShow(dbMovieShow);
				} else {
					throw new ShowIsNotActiveException("Sorry failed to book Ticket(Active Status)");
				}
			} else {
				throw new MovieShowIdNotFoundException("Sorry Failed to book Ticket(MovieShow)");
			}
		} else {
			throw new CustomerIdNotFoundException("Sorry Failed to book Ticket(Customer)");
		}

		List<Booking> bookings = new ArrayList<Booking>();
		List<Seat> seats = new ArrayList<Seat>();
		double totalPrice = 0;
		Seat dbSeat = seatDao.getSeatById(seatId);
		if (dbSeat != null) {
			Booking booking = new Booking();
			booking.setSeatId(dbSeat.getSeatId());
			booking.setSeatType(dbSeat.getSeatType());
			booking.setBookingStatus(BookingStatus.ACTIVE);
			booking.setBookingFromTime(dbMovieShow.getShowStartTime());
			booking.setBookingTillTime(dbMovieShow.getShowEndTime());

			SeatType seatType = booking.getSeatType();
			switch (seatType) {
			case CLASSIC:
				booking.setSeatPrice(dbMovieShow.getClassicSeatPrice());
				totalPrice += dbMovieShow.getClassicSeatPrice();
				break;
			case GOLD:
				booking.setSeatPrice(dbMovieShow.getGoldSeatPrice());
				totalPrice += dbMovieShow.getGoldSeatPrice();
				break;
			case PLATINUM:
				booking.setSeatPrice(dbMovieShow.getPremiumSeatPrice());
				totalPrice += dbMovieShow.getPremiumSeatPrice();
				break;
			}

			bookings.add(booking);
			seats.add(dbSeat);

			bookingDao.saveBooking(booking);

			ticket.setBookings(bookings);
			ticket.setTotalPrice(totalPrice);
			ticket.setTicketStatus(TicketStatus.ACTIVE);

			Ticket dbTicket = ticketDao.saveTicket(ticket);

			ResponseStructure<Ticket> structure = new ResponseStructure<Ticket>();
			structure.setMessage("Ticket Booked Successfully");
			structure.setStatus(HttpStatus.CREATED.value());
			structure.setData(dbTicket);
			return new ResponseEntity<ResponseStructure<Ticket>>(structure, HttpStatus.CREATED);

		} else {
			throw new SeatIdNotFoundException("Sorry Failed to book ticket");
		}
	}

	public ResponseEntity<ResponseStructure<Ticket>> cancelTicket(long ticketId) {
		Ticket dbTicket = ticketDao.getTicketById(ticketId);
		if (dbTicket != null) {
			if (dbTicket.getMovieShow().getShowStatus().equals(ShowStatus.ON_GOING)) {
				throw new TicketCannotBeCancelException("Sorry failed to cancel Ticket");
			} else if (dbTicket.getTicketStatus().equals(TicketStatus.CANCELLED)) {
				throw new TicketAlreadyCancelledException("Sorry Failed to Cancel The Ticket");
			} else if (dbTicket.getTicketStatus().equals(TicketStatus.EXPIRED)) {
				throw new TicketAlreadyExpiredException("Sorry Failed to cancel The Ticket");
			} else {
				List<Booking> bookings = dbTicket.getBookings();
				for (Booking booking : bookings) {
					booking.setBookingStatus(BookingStatus.CANCELLED);
					bookingDao.saveBooking(booking);
				}
				dbTicket.setTicketStatus(TicketStatus.CANCELLED);
				ticketDao.saveTicket(dbTicket);
				ResponseStructure<Ticket> structure = new ResponseStructure<Ticket>();
				structure.setMessage("Ticket Cancelld Successfully");
				structure.setStatus(HttpStatus.FOUND.value());
				structure.setData(dbTicket);
				return new ResponseEntity<ResponseStructure<Ticket>>(structure, HttpStatus.FOUND);
			}

		} else {
			throw new TicketIdNotFoundException("Sorry Failed to cancel Ticket");
		}
	}
}
