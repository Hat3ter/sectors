package com.helmet.sectors.controllers;

import com.helmet.sectors.models.dtos.SectorDto;
import com.helmet.sectors.models.dtos.UserSectorInfoDto;
import com.helmet.sectors.models.response.ResponseData;
import com.helmet.sectors.services.UserSectorService;
import com.helmet.sectors.services.sectors.SectorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 * Controller for {@link com.helmet.sectors.models.entity.Sector}
 */
@Validated
@CrossOrigin
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/sectors")
public class SectorController {

	/**
	 * @see SectorService
	 */
	private final UserSectorService userSectorService;

	/**
	 * Get sectors
	 *
	 * @return list of sectors
	 */
	@GetMapping
	public ResponseData<List<SectorDto>> getSectors() {

		log.info("#GET get sectors");
		List<SectorDto> sectors = userSectorService.getSectors();
		log.info("#GET get sectors size {}", sectors.size());
		return new ResponseData<>(sectors);
	}

	@GetMapping("/{userId}")
	public ResponseData<UserSectorInfoDto> getUserSectors(@PathVariable UUID userId) {

		log.info("#GET get user sectors id={}", userId);
		UserSectorInfoDto userSectors = userSectorService.getUserSectors(userId);
		log.info("#GET get user sectors id={}", userSectors.getUserId());
		return new ResponseData<>(userSectors);
	}


}
