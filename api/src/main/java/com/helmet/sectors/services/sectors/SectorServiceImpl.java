package com.helmet.sectors.services.sectors;

import com.helmet.sectors.models.dtos.SectorDto;
import com.helmet.sectors.models.entity.Sector;
import com.helmet.sectors.repositories.sectors.SectorRepository;
import com.helmet.sectors.utils.SectorConverter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service for {@link SectorRepository}
 */
@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class SectorServiceImpl implements SectorService {

	/**
	 * @see SectorRepository
	 */
	private final SectorRepository repository;

	@Override
	public List<SectorDto> getSectors() {

		return repository.findRootSectors().stream()
				.map(SectorConverter.INSTANCE::convert)
				.collect(Collectors.toList());
	}
}
