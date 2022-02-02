package com.helmet.sectors.repositories.sectors;

import com.helmet.sectors.models.entity.Sector;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

/**
 * Repository for {@link Sector}
 */
@Repository
public interface SectorRepository extends JpaRepository<Sector, UUID> {

	/**
	 * Find all root sectors
	 *
	 * @return sector list
	 */
	@Query(value = "select s from Sector s where s.parentId is null order by s.sector")
	List<Sector> findRootSectors();
}
