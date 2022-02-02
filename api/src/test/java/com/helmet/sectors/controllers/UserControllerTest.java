package com.helmet.sectors.controllers;

import com.helmet.sectors.models.entity.User;
import com.helmet.sectors.models.request.UserRequest;
import com.helmet.sectors.models.response.ResponseData;
import com.helmet.sectors.repositories.users.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.annotation.DirtiesContext;

import java.util.Collections;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Test {@link UsersController}
 */
@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class UserControllerTest extends AbstractMvcTest {

	/**
	 * @see UserRepository
	 */
	@Autowired
	private UserRepository repository;

	/**
	 * Test get sectors
	 *
	 * @throws Exception e
	 */
	@Test
	public void testGetSectors() throws Exception {

		UserRequest request = new UserRequest();
		request.setName("Name");
		request.setAgreeWithTerms(true);
		request.setSectorIds(Collections.emptyList());
		MockHttpServletResponse response = mockMvc.perform(
				post(getUrl())
						.contentType(MediaType.APPLICATION_JSON_VALUE)
						.content(objectMapper.writeValueAsString(request)))
				.andDo(print())
				.andExpect(status().is2xxSuccessful())
				.andReturn().getResponse();

		String contentAsString = response.getContentAsString();
		ResponseData responseData = objectMapper.readValue(contentAsString, ResponseData.class);
		Object userId = responseData.getData();
		User user = repository.findAll().get(0);
		Assertions.assertEquals(user.getId().toString(), userId.toString());

	}

	@Override
	String getUrl() {
		return "/api/v1/users";
	}
}