package com.helmet.sectors.services;

import com.helmet.sectors.models.dtos.SectorDto;
import com.helmet.sectors.models.dtos.UserDto;
import com.helmet.sectors.models.dtos.UserSectorInfoDto;
import com.helmet.sectors.models.entity.AgreedSector;
import com.helmet.sectors.models.request.UserRequest;
import com.helmet.sectors.services.sectors.SectorService;
import com.helmet.sectors.services.users.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

/**
 * Implementation {@link UserSectorService}
 */
@Transactional
@Service
@RequiredArgsConstructor
public class UserSectorServiceImpl implements UserSectorService {

	/**
	 * @see SectorService
	 */
	private final SectorService sectorService;

	/**
	 * @see UserService
	 */
	private final UserService userService;

	@Override
	public List<SectorDto> getSectors() {

		return sectorService.getSectors();
	}

	@Override
	public UUID saveUser(UserRequest request) {

		return userService.saveUser(request);
	}

	@Override
	public UserSectorInfoDto getUserSectors(UUID userId) {

		List<SectorDto> sectors = sectorService.getSectors();
		UserDto user = userService.getUserById(userId);
		final List<AgreedSector> agreedSectors = user.getAgreedSectors();

		return createUserSectorInfoDto(sectors, user, agreedSectors);
	}

	/**
	 * Create {@link UserSectorInfoDto}
	 *
	 * @param sectors       list of {@link SectorDto}
	 * @param user          {@link com.helmet.sectors.models.entity.User}
	 * @param agreedSectors list of {@link AgreedSector}
	 * @return {@link UserSectorInfoDto}
	 */
	private UserSectorInfoDto createUserSectorInfoDto(List<SectorDto> sectors, UserDto user,
													  List<AgreedSector> agreedSectors) {

		UserSectorInfoDto result = new UserSectorInfoDto();
		result.setUserId(user.getId());
		result.setUserName(user.getName());
		changeSelectedFlagIfNeeded(sectors, agreedSectors);
		result.setSectorDto(sectors);
		return result;
	}

	/**
	 * Fill selected flag
	 *
	 * @param dtos          list of {@link SectorDto}
	 * @param agreedSectors list of {@link AgreedSector}
	 */
	private void changeSelectedFlagIfNeeded(List<SectorDto> dtos, List<AgreedSector> agreedSectors) {

		dtos.forEach(sectorDto -> {
			UUID id = sectorDto.getId();
			if (agreedSectors.stream().anyMatch(agreedSector -> agreedSector.getSectorId().equals(id))) {
				sectorDto.setSelected(true);
			}
			changeSelectedFlagIfNeeded(sectorDto.getSectors(), agreedSectors);
		});
	}
}
