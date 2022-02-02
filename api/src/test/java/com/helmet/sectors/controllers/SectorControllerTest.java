package com.helmet.sectors.controllers;

import com.helmet.sectors.models.entity.Sector;
import com.helmet.sectors.models.request.UserRequest;
import com.helmet.sectors.repositories.sectors.SectorRepository;
import com.helmet.sectors.services.UserSectorService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.Collections;
import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class SectorControllerTest extends AbstractMvcTest {

	/**
	 * @see SectorRepository
	 */
	@Autowired
	private SectorRepository repository;

	@Autowired
	private UserSectorService userSectorService;


	/**
	 * Test get sectors
	 *
	 * @throws Exception e
	 */
	@Test
	public void testGetSectors() throws Exception {

		mockMvc.perform(
				get(getUrl())).andDo(print())
				.andExpect(status().is2xxSuccessful())
				.andExpect(jsonPath("$.data", Matchers.empty()));

		Sector root = createSector("root", null);
		Sector child = createSector("child", root.getId());

		mockMvc.perform(
				get(getUrl())).andDo(print())
				.andExpect(status().is2xxSuccessful())
				.andExpect(jsonPath("$.data[0].sector").value(root.getSector()))
				.andExpect(jsonPath("$.data[0].sectors[0].sector").value(child.getSector()))
				.andExpect(jsonPath("$.data[1]").doesNotExist());
	}


	/**
	 * Test get sectors
	 *
	 * @throws Exception e
	 */
	@Test
	public void testGetSectorsById() throws Exception {

		Sector root = createSector("root", null);
		createSector("child", root.getId());

		UserRequest request = new UserRequest();
		request.setAgreeWithTerms(true);
		request.setSectorIds(Collections.emptyList());
		request.setName("Name");

		UUID userId = userSectorService.saveUser(request);

		mockMvc.perform(
				get(getUrl() + "/" + userId.toString())).andDo(print())
				.andExpect(status().is2xxSuccessful())
				.andExpect(jsonPath("$.data.userId").value(userId.toString()))
				.andExpect(jsonPath("$.data.userName").value("Name"))
				.andExpect(jsonPath("$.data.sectorDto[0].sector").value("root"))
				.andExpect(jsonPath("$.data.sectorDto[0].agreeWithTerms").value("true"))
				.andExpect(jsonPath("$.data.sectorDto[0].selected").value(false))
				.andExpect(jsonPath("$.data.sectorDto[0].sectors[0].sector").value("child"))
				.andExpect(jsonPath("$.data.sectorDto[0].sectors[0].selected").value(false))
				.andExpect(jsonPath("$.data.sectorDto[0].sectors[0].agreeWithTerms").value(true));
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

	@Override
	String getUrl() {
		return "/api/v1/sectors";
	}
}