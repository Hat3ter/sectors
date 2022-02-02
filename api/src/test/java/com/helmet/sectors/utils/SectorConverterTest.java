package com.helmet.sectors.utils;

import com.helmet.sectors.models.dtos.SectorDto;
import com.helmet.sectors.models.entity.Sector;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.UUID;

/**
 * Test {@link SectorConverter}
 */
class SectorConverterTest extends Assertions {


	/**
	 * Test convert sector to dto
	 */
	@Test
	public void testConvert() {

		SectorDto dto = SectorConverter.INSTANCE.convert(null);
		assertNull(dto);

		Sector sector = new Sector();
		sector.setSector("sector");
		sector.setParentId(UUID.randomUUID());
		sector.setSectors(Collections.emptyList());
		sector.setId(UUID.randomUUID());
		sector.setAgreeWithTerms(true);

		dto = SectorConverter.INSTANCE.convert(sector);

		assertEquals(sector.getId(), dto.getId());
		assertEquals(sector.getSector(), dto.getSector());
		assertEquals(sector.getSectors().size(), dto.getSectors().size());
		assertEquals(sector.isAgreeWithTerms(), dto.isAgreeWithTerms());
		assertEquals(sector.getParentId(), dto.getParentId());
	}
}