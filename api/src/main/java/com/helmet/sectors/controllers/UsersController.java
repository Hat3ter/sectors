package com.helmet.sectors.controllers;

import com.helmet.sectors.models.request.UserRequest;
import com.helmet.sectors.models.response.ResponseData;
import com.helmet.sectors.services.UserSectorService;
import com.helmet.sectors.services.sectors.SectorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

/**
 * Controller for {@link com.helmet.sectors.models.entity.User}
 */
@Validated
@CrossOrigin
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UsersController {

	/**
	 * @see SectorService
	 */
	private final UserSectorService userSectorService;

	@ResponseStatus(HttpStatus.ACCEPTED)
	@PostMapping
	public ResponseData<?> saveUserInfo(@RequestBody @Valid UserRequest request) {

		log.info("#POST save user {}", request);
		UUID uuid = userSectorService.saveUser(request);
		log.info("#POST saved user {}", uuid);
		return new ResponseData<>(uuid);
	}

}
