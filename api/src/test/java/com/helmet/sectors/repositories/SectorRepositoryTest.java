package com.helmet.sectors.repositories;

import com.helmet.sectors.AbstractTestCaseTest;
import com.helmet.sectors.models.entity.Sector;
import com.helmet.sectors.repositories.sectors.SectorRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test {@link SectorRepository}
 */
class SectorRepositoryTest extends AbstractTestCaseTest {

	/**
	 * @see SectorRepository
	 */
	@Autowired
	private SectorRepository repository;


	/**
	 * Test findRooSectors
	 */
	@Test
	public void testFindRootSectors() {

		assertTrue(repository.findAll().isEmpty());
		assertTrue(repository.findRootSectors().isEmpty());

		Sector rootSector = new Sector();
		rootSector.setSector("root");
		rootSector.setAgreeWithTerms(true);
		rootSector = repository.save(rootSector);

		Sector childSector = new Sector();
		childSector.setSector("child");
		childSector.setAgreeWithTerms(true);
		childSector.setParentId(rootSector.getId());
		repository.save(childSector);


		assertFalse(repository.findAll().isEmpty());
		assertEquals(2, repository.findAll().size());
		assertEquals(1, repository.findRootSectors().size());

		// check order by sector name
		Sector aSector = new Sector();
		aSector.setSector("aroot");
		aSector.setAgreeWithTerms(true);
		repository.save(aSector);

		Sector xSector = new Sector();
		xSector.setSector("xroot");
		xSector.setAgreeWithTerms(true);
		repository.save(xSector);

		Sector bSector = new Sector();
		bSector.setSector("broot");
		bSector.setAgreeWithTerms(true);
		repository.save(bSector);

		assertEquals(5, repository.findAll().size());
		List<Sector> rootSectors = repository.findRootSectors();
		assertEquals(4, rootSectors.size());

		assertEquals(aSector.getSector(), rootSectors.get(0).getSector());
		assertEquals(bSector.getSector(), rootSectors.get(1).getSector());
		assertEquals(rootSector.getSector(), rootSectors.get(2).getSector());
		assertEquals(xSector.getSector(), rootSectors.get(3).getSector());
	}
}