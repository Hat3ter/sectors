package com.helmet.sectors.models.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

/**
 * Request for update sector
 */
@Data
public class SectorUpdateRequest {

	/**
	 * Id
	 */
	@NotNull
	private UUID id;

	/**
	 * Sector name
	 */
	@NotBlank
	private String sector;
}
