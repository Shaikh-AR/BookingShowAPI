package org.ty.CloneAPIBookMyShow.dto;

import java.time.LocalDateTime;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.ty.CloneAPIBookMyShow.enums.Genre;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MovieDto {
	private long movieId;
	@NotNull(message = "Movie Name should Not be Null")
	@NotBlank(message = "Movie Name should Not be Blank")
	private String movieName;
//	genress
	private Genre genre1;
	private Genre genre2;
	private Genre genre3;
	
	private LocalDateTime movieDuration;
	@NotNull(message = "Movie Description should Not be Null")
	@NotBlank(message = "Movie Description should Not be Blank")
	private String movieDescription;
	@NotNull(message = "Language should Not be Null")
	@NotBlank(message = "Language should Not be Blank")
	private String language;
}
