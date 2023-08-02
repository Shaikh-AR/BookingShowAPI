package org.ty.CloneAPIBookMyShow.dto;

import java.time.LocalDateTime;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductionHouseDto {
	private long productionId;
	@NotBlank(message = "Production Name Should Not be Blank")
	@NotNull(message = "Production Name Should Not be Null")
	private String productionName;
	
	@DateTimeFormat
	private LocalDateTime establishment;
}
