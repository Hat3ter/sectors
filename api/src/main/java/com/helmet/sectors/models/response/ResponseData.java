package com.helmet.sectors.models.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Response from api
 *
 * @param <T> data
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseData<T> {

	/**
	 * Data
	 */
	private T data;
}
