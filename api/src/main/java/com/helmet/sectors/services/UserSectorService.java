package com.helmet.sectors.services;

import com.helmet.sectors.models.dtos.SectorDto;
import com.helmet.sectors.models.dtos.UserSectorInfoDto;
import com.helmet.sectors.models.request.UserRequest;

import java.util.List;
import java.util.UUID;

/**
 * Gateway for user and sector information
 */
public interface UserSectorService {

	/**
	 * Get sector information
	 *
	 * @return list of {@link SectorDto}
	 */
	List<SectorDto> getSectors();

	/**
	 * Save user
	 *
	 * @param request {@link UserRequest}
	 * @return {@link UUID} user id
	 */
	UUID saveUser(UserRequest request);

	/**
	 * Get user sectors with selected
	 *
	 * @param userId user id
	 * @return {@link UserSectorInfoDto}
	 */
	UserSectorInfoDto getUserSectors(UUID userId);
}
