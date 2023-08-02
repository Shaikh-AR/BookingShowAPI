package org.ty.CloneAPIBookMyShow.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class ProductionHouse {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long productionId;
	@NotNull(message = "Production Name Should Not be Null")
	@NotBlank(message = "Production Name Should Not be Blank")
	private String productionName;
	@DateTimeFormat
	private LocalDateTime establishment;

	@ManyToOne
	@JoinColumn
	private Owner owner;

	@OneToMany(fetch = FetchType.EAGER,mappedBy = "productionHouse")
	private List<Movie> movies;

	

}
