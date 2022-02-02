package com.helmet.sectors.models.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Response for errors
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse {

	/**
	 * Message
	 */
	private String message;
}
