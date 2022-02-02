package com.helmet.sectors.services.sectors;

import com.helmet.sectors.models.dtos.SectorDto;

import java.util.List;

/**
 * Service for {@link com.helmet.sectors.models.entity.Sector}
 */
public interface SectorService {

	/**
	 * Get sectors
	 *
	 * @return list of sectors
	 */
	List<SectorDto> getSectors();
}
