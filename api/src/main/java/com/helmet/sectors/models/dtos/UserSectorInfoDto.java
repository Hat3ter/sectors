package com.helmet.sectors.models.dtos;

import lombok.Data;

import java.util.List;
import java.util.UUID;

/**
 * User/sector info dto
 */
@Data
public class UserSectorInfoDto {

	/**
	 * User id
	 */
	private UUID userId;

	/**
	 * User name
	 */
	private String userName;

	/**
	 * List of sector dto
	 */
	private List<SectorDto> sectorDto;

}
