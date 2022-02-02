package com.helmet.sectors.models.dtos;

import com.helmet.sectors.models.entity.AgreedSector;
import lombok.Data;

import java.util.List;
import java.util.UUID;

/**
 * User dto
 */
@Data
public class UserDto {

	/**
	 * Id
	 */
	private UUID id;

	/**
	 * name
	 */
	private String name;

	/**
	 * List of agreed sectors
	 */
	private List<AgreedSector> agreedSectors;

	/**
	 * Flag agree with terms
	 */
	private boolean agreeWithTerms;
}
