package org.ty.CloneAPIBookMyShow.entity;

import java.time.LocalDateTime;
import java.time.LocalTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.ty.CloneAPIBookMyShow.enums.Genre;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Movie {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long movieId;
	private String movieName;
//	genress
	private Genre genre1;
	private Genre genre2;
	private Genre genre3;
	private LocalDateTime movieDuration;
	private String movieDescription;
	private String language;
	
	@ManyToOne
	@JoinColumn
	@JsonIgnore
	private ProductionHouse productionHouse;
	
}
