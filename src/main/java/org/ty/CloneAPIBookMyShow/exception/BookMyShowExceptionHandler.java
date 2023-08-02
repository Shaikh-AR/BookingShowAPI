package org.ty.CloneAPIBookMyShow.exception;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.transform.ErrorListener;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.ty.CloneAPIBookMyShow.util.ResponseStructure;

@RestControllerAdvice
public class BookMyShowExceptionHandler extends ResponseEntityExceptionHandler {

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		List<ObjectError> list = ex.getAllErrors();
		Map<String,String> map = new HashMap<String,String>();
		for (ObjectError objectError : list) {
			String message = objectError.getDefaultMessage();
			String fieldName = ((FieldError) objectError).getField();
			map.put(fieldName, message);
		}
		return new ResponseEntity<Object>(map,HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> ownerIdNotFound(OwnerIdNotFoundException ex) {
		ResponseStructure<String> structure = new ResponseStructure<String>();
		structure.setMessage("Id not Found for this owner");
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		structure.setData(ex.getMessage());
		return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> houseIdNotFound(ProductionHouseIdNotFoundException ex) {
		ResponseStructure<String> structure = new ResponseStructure<String>();
		structure.setMessage("Id not Found for House");
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		structure.setData(ex.getMessage());
		return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> addressIdNotFound(AddressIdNotFoundException ex) {
		ResponseStructure<String> structure = new ResponseStructure<String>();
		structure.setMessage("Id not Found for address");
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		structure.setData(ex.getMessage());
		return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> theatreAlreadyPresent(TheatreAlreadyPresentInThisAddress ex) {
		ResponseStructure<String> structure = new ResponseStructure<String>();
		structure.setMessage("Theatre Already Present In This Address");
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		structure.setData(ex.getMessage());
		return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> theatreIdNotFound(TheatreIdNotFoundException ex) {
		ResponseStructure<String> structure = new ResponseStructure<String>();
		structure.setMessage("Theatre Id NOt Found");
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		structure.setData(ex.getMessage());
		return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> customerIdNotFound(CustomerIdNotFoundException ex) {
		ResponseStructure<String> structure = new ResponseStructure<String>();
		structure.setMessage("Customer Id NOt Found");
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		structure.setData(ex.getMessage());
		return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> screenIdNotFound(ScreenIdNotFoundException ex) {
		ResponseStructure<String> structure = new ResponseStructure<String>();
		structure.setMessage("Screen Id NOt Found");
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		structure.setData(ex.getMessage());
		return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> movieIdNotFound(MovieIdNotFoundException ex) {
		ResponseStructure<String> structure = new ResponseStructure<String>();
		structure.setMessage("Movie Id NOt Found");
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		structure.setData(ex.getMessage());
		return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> bookingIdNotFound(BookingIdNotFoundException ex) {
		ResponseStructure<String> structure = new ResponseStructure<String>();
		structure.setMessage("Booking Id Not Found");
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		structure.setData(ex.getMessage());
		return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> movieShowIdNotFound(MovieShowIdNotFoundException ex) {
		ResponseStructure<String> structure = new ResponseStructure<String>();
		structure.setMessage("MovieShow Id Not Found");
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		structure.setData(ex.getMessage());
		return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> ticketIdNotFound(TicketIdNotFoundException ex) {
		ResponseStructure<String> structure = new ResponseStructure<String>();
		structure.setMessage("Ticket Id Not Found");
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		structure.setData(ex.getMessage());
		return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> screenAlreadyAlloted(ScreenAlreadyAllotedException ex) {
		ResponseStructure<String> structure = new ResponseStructure<String>();
		structure.setMessage("Screen Already Alotted");
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		structure.setData(ex.getMessage());
		return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> showIsNotActive(ShowIsNotActiveException ex) {
		ResponseStructure<String> structure = new ResponseStructure<String>();
		structure.setMessage("Show is not Active");
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		structure.setData(ex.getMessage());
		return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> seatIdNotFound(SeatIdNotFoundException ex) {
		ResponseStructure<String> structure = new ResponseStructure<String>();
		structure.setMessage("Seat Id not Found");
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		structure.setData(ex.getMessage());
		return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> ticketCannotBeCancel(TicketCannotBeCancelException ex) {
		ResponseStructure<String> structure = new ResponseStructure<String>();
		structure.setMessage("Sorry Show is Ongoing you cant cancel the Ticket");
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		structure.setData(ex.getMessage());
		return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> ticketAlreadyCancel(TicketAlreadyCancelledException ex) {
		ResponseStructure<String> structure = new ResponseStructure<String>();
		structure.setMessage("Ticket Allready Cancelled you cant Cancelled the Ticket");
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		structure.setData(ex.getMessage());
		return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> ticketAlreadyExpired(TicketAlreadyExpiredException ex) {
		ResponseStructure<String> structure = new ResponseStructure<String>();
		structure.setMessage("Ticket Expired you cant Cancelled the Ticket");
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		structure.setData(ex.getMessage());
		return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.NOT_FOUND);
	}

}
