package com.helmet.sectors.models.request;

import lombok.Data;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

@Data
public class UserRequest {

	/**
	 * Id can be null when new user.
	 */
	private UUID id;

	/**
	 * User name
	 */
	@NotBlank
	private String name;

	/**
	 * List of agreed sectors
	 */
	@NotNull
	private List<UUID> sectorIds;

	/**
	 * Is agree with terms
	 */
	@AssertTrue
	private boolean agreeWithTerms;
}
