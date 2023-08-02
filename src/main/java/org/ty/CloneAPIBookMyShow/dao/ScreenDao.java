package org.ty.CloneAPIBookMyShow.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.ty.CloneAPIBookMyShow.Repository.ScreenRepo;
import org.ty.CloneAPIBookMyShow.entity.Screen;
import org.ty.CloneAPIBookMyShow.entity.Theatre;

@Repository
public class ScreenDao {

	@Autowired
	private ScreenRepo repo;

	public Screen saveScreen(Screen screen) {
		return repo.save(screen);
	}

	public Screen updateScreen(long screenId, Screen screen) {
		Optional<Screen> optional = repo.findById(screenId);
		if (optional.isPresent()) {
			screen.setScreenId(screenId);
			screen.setSeats(optional.get().getSeats());
			screen.setNoOfClassicSeat(optional.get().getNoOfClassicSeat());
			screen.setNoOfGoldSeat(optional.get().getNoOfGoldSeat());
			screen.setNoOfPlatinumSeat(optional.get().getNoOfPlatinumSeat());
			screen.setTheatre(optional.get().getTheatre());
			return repo.save(screen);
		}
		return null;
	}

	public Screen getScreenById(long screenId) {
		Optional<Screen> optional = repo.findById(screenId);
		if (optional.isPresent()) {
			return optional.get();
		}
		return null;
	}

	public Screen deleteScreenById(long screenId) {
		Optional<Screen> optional = repo.findById(screenId);
		if (optional.isPresent()) {
			Screen screen = optional.get();
			screen.setScreenId(screenId);
			Theatre theatre = screen.getTheatre();
			theatre.setScreens(null);
			screen.setTheatre(null);
			repo.delete(screen);
			return screen;
		}
		return null;
	}
}
