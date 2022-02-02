package com.helmet.sectors.utils;


import com.helmet.sectors.models.dtos.SectorDto;
import com.helmet.sectors.models.entity.Sector;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

/**
 * Converter for {@link Sector} and {@link SectorDto}
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SectorConverter {

	/**
	 * Instance
	 */
	SectorConverter INSTANCE = Mappers.getMapper(SectorConverter.class);

	/**
	 * Convert
	 *
	 * @param sector {@link Sector}
	 * @return {@link SectorDto}
	 */
	SectorDto convert(Sector sector);
}
