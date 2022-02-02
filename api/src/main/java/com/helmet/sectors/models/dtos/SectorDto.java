package com.helmet.sectors.models.dtos;

import lombok.Data;

import java.util.List;
import java.util.UUID;

/**
 * Dto for sector
 */
@Data
public class SectorDto {

	/**
	 * id
	 */
	private UUID id;

	/**
	 * sector name
	 */
	private String sector;

	/**
	 * Agree with terms
	 */
	private boolean isAgreeWithTerms;

	/**
	 * Sector child list
	 */
	private List<SectorDto> sectors;

	/**
	 * Parent id(if hasn't parent - null)
	 */
	private UUID parentId;

	/**
	 * Selected flag
	 */
	private boolean selected;
}
