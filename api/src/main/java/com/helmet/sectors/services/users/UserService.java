package com.helmet.sectors.services.users;

import com.helmet.sectors.models.dtos.UserDto;
import com.helmet.sectors.models.request.UserRequest;

import java.util.UUID;

/**
 * Service for {@link com.helmet.sectors.models.entity.User}
 */
public interface UserService {

	/**
	 * Save user information
	 *
	 * @param request {@link UserRequest}
	 * @return {@link UUID} user id
	 */
	UUID saveUser(UserRequest request);

	/**
	 * Get user information by user id
	 *
	 * @param id user id
	 * @return {@link UserDto}
	 */
	UserDto getUserById(UUID id);
}
