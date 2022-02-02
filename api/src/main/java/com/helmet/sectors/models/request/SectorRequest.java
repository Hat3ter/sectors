package com.helmet.sectors.models.request;

import lombok.Data;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

/**
 * Request for save sector
 */
@Data
public class SectorRequest {

	/**
	 * User id
	 */
	@NotNull
	private UUID userId;

	/**
	 * List of agreed sectors
	 */
	@NotNull
	private List<UUID> sectorId;

	/**
	 * Is agree with terms
	 */
	@AssertTrue
	private boolean agreeWithTerms;
}
