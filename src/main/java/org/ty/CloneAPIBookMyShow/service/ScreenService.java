package org.ty.CloneAPIBookMyShow.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.ty.CloneAPIBookMyShow.dao.ScreenDao;
import org.ty.CloneAPIBookMyShow.dao.TheatreDao;
import org.ty.CloneAPIBookMyShow.dto.ScreenDto;
import org.ty.CloneAPIBookMyShow.entity.Screen;
import org.ty.CloneAPIBookMyShow.entity.Seat;
import org.ty.CloneAPIBookMyShow.entity.Theatre;
import org.ty.CloneAPIBookMyShow.enums.ScreenAvailability;
import org.ty.CloneAPIBookMyShow.enums.ScreenStatus;
import org.ty.CloneAPIBookMyShow.enums.SeatType;
import org.ty.CloneAPIBookMyShow.exception.ScreenIdNotFoundException;
import org.ty.CloneAPIBookMyShow.exception.TheatreIdNotFoundException;
import org.ty.CloneAPIBookMyShow.util.ResponseStructure;

@Service
public class ScreenService {

	@Autowired
	private ScreenDao screenDao;

	@Autowired
	private TheatreDao theatreDao;

	@Autowired
	private ModelMapper modelMapper;

	public ResponseEntity<ResponseStructure<ScreenDto>> addScreen(long theatreId, ScreenDto screenDto) {
		Theatre theatre = theatreDao.getTheatreById(theatreId);
		if (theatre != null) {
//			Add Screen and Seat
			Screen screen = this.modelMapper.map(screenDto, Screen.class);
			/*
			 * Screen variable your are having no of classic,gold,platinum seat Screen is
			 * having seat object? not present and i want to add it Screen is having
			 * theatre? no but we are having theatre object then i will set it (theatre)
			 */

			List<Seat> seats = new ArrayList<Seat>();
			for (int a = screen.getNoOfClassicSeat(); a > 0; a--) {
				Seat seat = new Seat();
				seat.setSeatType(SeatType.CLASSIC);
				seat.setScreen(screen);
				seats.add(seat);
			}

			for (int b = screen.getNoOfPlatinumSeat(); b > 0; b--) {
				Seat seat = new Seat();
				seat.setSeatType(SeatType.PLATINUM);
				seat.setScreen(screen);
				seats.add(seat);
			}

			for (int c = screen.getNoOfGoldSeat(); c > 0; c--) {
				Seat seat = new Seat();
				seat.setSeatType(SeatType.GOLD);
				seat.setScreen(screen);
				seats.add(seat);
			}

			screen.setTheatre(theatre);
			screen.setSeats(seats);

			screen.setScreenAvailability(ScreenAvailability.ALLOTED);
			screen.setScreenStatus(ScreenStatus.AVAILABLE);

			Screen dbScreen = screenDao.saveScreen(screen);
//			Update the Theatre
			if (theatre.getScreens().isEmpty()) {
				List<Screen> screens = new ArrayList<Screen>();
				screens.add(dbScreen);
				theatre.setScreens(screens);
			} else {
				List<Screen> screens = theatre.getScreens();
				screens.add(dbScreen);
				theatre.setScreens(screens);
			}
			ScreenDto dto = this.modelMapper.map(dbScreen, ScreenDto.class);
			ResponseStructure<ScreenDto> structure = new ResponseStructure<ScreenDto>();
			structure.setMessage("Screen saved Successfully");
			structure.setStatus(HttpStatus.CREATED.value());
			structure.setData(dto);
			return new ResponseEntity<ResponseStructure<ScreenDto>>(structure, HttpStatus.CREATED);

		} else {
			throw new TheatreIdNotFoundException("Failed to add Screen");
		}
	}

	public ResponseEntity<ResponseStructure<ScreenDto>> updateScreen(long screenId, ScreenDto screenDto) {
		Screen screen = this.modelMapper.map(screenDto, Screen.class);
		Screen dbScreen = screenDao.updateScreen(screenId, screen);
		if (dbScreen != null) {
			ResponseStructure<ScreenDto> structure = new ResponseStructure<ScreenDto>();
			structure.setMessage("Screen Update Successfully");
			structure.setStatus(HttpStatus.OK.value());
			structure.setData(this.modelMapper.map(dbScreen, ScreenDto.class));
			return new ResponseEntity<ResponseStructure<ScreenDto>>(structure, HttpStatus.OK);
		} else {
			throw new ScreenIdNotFoundException("Sorry Failed to Update The Screen");
		}

	}

	public ResponseEntity<ResponseStructure<ScreenDto>> getScreenById(long screenId) {
		Screen dbScreen = screenDao.getScreenById(screenId);
		if (dbScreen != null) {
			ResponseStructure<ScreenDto> structure = new ResponseStructure<ScreenDto>();
			structure.setMessage("Screen Fetch Successfully");
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setData(this.modelMapper.map(dbScreen, ScreenDto.class));
			return new ResponseEntity<ResponseStructure<ScreenDto>>(structure, HttpStatus.FOUND);
		} else {
			throw new ScreenIdNotFoundException("Sorry Failed to Get The Screen");
		}
	}

	public ResponseEntity<ResponseStructure<ScreenDto>> deleteScreenById(long screenId) {
		Screen dbScreen = screenDao.deleteScreenById(screenId);
		if (dbScreen != null) {
			ResponseStructure<ScreenDto> structure = new ResponseStructure<ScreenDto>();
			structure.setMessage("Screen Delete Successfully");
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setData(this.modelMapper.map(dbScreen, ScreenDto.class));
			return new ResponseEntity<ResponseStructure<ScreenDto>>(structure, HttpStatus.FOUND);
		} else {
			throw new ScreenIdNotFoundException("Sorry Failed to Delete The Screen");
		}
		
	}
}
