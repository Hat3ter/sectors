package com.helmet.sectors.services;

import com.helmet.sectors.AbstractTestCaseTest;
import com.helmet.sectors.models.dtos.SectorDto;
import com.helmet.sectors.models.entity.Sector;
import com.helmet.sectors.repositories.sectors.SectorRepository;
import com.helmet.sectors.services.sectors.SectorService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Test for {@link SectorService}
 */
class SectorServiceTest extends AbstractTestCaseTest {

	/**
	 * @see SectorRepository
	 */
	@Autowired
	private SectorRepository repository;

	@Autowired
	private SectorService sectorService;

	/**
	 *
	 */
	@Test
	public void testGetSectors() {

		assertTrue(repository.findAll().isEmpty());
		assertTrue(repository.findRootSectors().isEmpty());

		Sector root1 = createRootSector("root1");
		Sector root2 = createRootSector("root2");
		Sector childRoot1 = createSector("childRoot1", root1.getId());
		Sector childRoot2 = createSector("childRoot2", root2.getId());

		assertEquals(4, repository.findAll().size());
		assertEquals(2, repository.findRootSectors().size());

		List<SectorDto> rootSectors = sectorService.getSectors();
		assertEquals(root1.getSector(), rootSectors.get(0).getSector());
		assertEquals(childRoot1.getSector(), rootSectors.get(0).getSectors().get(0).getSector());

		assertEquals(root2.getSector(), rootSectors.get(1).getSector());
		assertEquals(childRoot2.getSector(), rootSectors.get(1).getSectors().get(0).getSector());
	}

	/**
	 * Create root sector
	 *
	 * @param name name
	 * @return {@link Sector}
	 */
	private Sector createRootSector(String name) {

		return createSector(name, null);
	}

	/**
	 * Create sector
	 *
	 * @param name   name
	 * @param parent parent id
	 * @return {@link Sector}
	 */
	private Sector createSector(String name, UUID parent) {

		Sector sector = new Sector();
		sector.setSector(name);
		sector.setAgreeWithTerms(true);
		sector.setParentId(parent);
		return repository.save(sector);
	}
}