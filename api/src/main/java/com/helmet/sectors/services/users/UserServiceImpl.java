package com.helmet.sectors.services.users;

import com.helmet.sectors.models.dtos.UserDto;
import com.helmet.sectors.models.entity.AgreedSector;
import com.helmet.sectors.models.entity.User;
import com.helmet.sectors.models.request.UserRequest;
import com.helmet.sectors.repositories.users.UserRepository;
import com.helmet.sectors.utils.UserConverter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Implementation {@link UserService}
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

	/**
	 * @see UserRepository
	 */
	private final UserRepository userRepository;

	@Override
	public UUID saveUser(UserRequest request) {

		User user = new User();
		user.setId(request.getId() == null ? UUID.randomUUID() : request.getId());
		user.setName(request.getName());
		user.setAgreeWithTerms(request.isAgreeWithTerms());
		user.setAgreedSectors(createAgreedSectors(request.getSectorIds(), user));
		return userRepository.save(user).getId();
	}


	@Override
	public UserDto getUserById(UUID id) {

		return userRepository.findById(id)
				.map(UserConverter.INSTANCE::convert)
				.orElseThrow(() -> {
					String errorMessage = String.format("User with id={%s} not found", id);
					log.warn(errorMessage);
					return new EntityNotFoundException(errorMessage);
				});
	}


	/**
	 * Create list of agreed sectors
	 *
	 * @param agreedIds ids
	 * @param user      {@link User}
	 * @return list
	 */
	private static List<AgreedSector> createAgreedSectors(List<UUID> agreedIds, User user) {

		return agreedIds.stream()
				.map(uuid -> {
					AgreedSector agreedSector = new AgreedSector();
					agreedSector.setUserId(user.getId());
					agreedSector.setSectorId(uuid);
					return agreedSector;
				})
				.collect(Collectors.toList());
	}

}
