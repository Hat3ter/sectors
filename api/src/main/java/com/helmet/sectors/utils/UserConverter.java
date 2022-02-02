package com.helmet.sectors.utils;


import com.helmet.sectors.models.dtos.SectorDto;
import com.helmet.sectors.models.dtos.UserDto;
import com.helmet.sectors.models.entity.Sector;
import com.helmet.sectors.models.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

/**
 * Converter for {@link User} and {@link UserDto}
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserConverter {

	/**
	 * Instance
	 */
	UserConverter INSTANCE = Mappers.getMapper(UserConverter.class);

	/**
	 * Convert
	 *
	 * @param sector {@link User}
	 * @return {@link UserDto}
	 */
	UserDto convert(User sector);

}
