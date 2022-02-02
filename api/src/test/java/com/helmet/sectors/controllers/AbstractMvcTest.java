package com.helmet.sectors.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

/**
 * Abstract mvc test
 */
public abstract class AbstractMvcTest {

	/**
	 * @see ObjectMapper
	 */
	@Autowired
	protected ObjectMapper objectMapper;

	/**
	 * @see MockMvc
	 */
	@Autowired
	protected MockMvc mockMvc;

	/**
	 * Get url
	 *
	 * @return url
	 */
	abstract String getUrl();
}
