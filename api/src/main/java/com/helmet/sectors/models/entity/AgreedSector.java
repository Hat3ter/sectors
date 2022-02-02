package com.helmet.sectors.models.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

/**
 * Agreed sectors
 */
@Table(name = "agreed_sectors")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AgreedSector implements Serializable {

	/**
	 * ID
	 */
	@Id
	@Generated
	@GeneratedValue
	@Column(name = "id", columnDefinition = "uuid")
	private UUID id;

	/**
	 * Sector id
	 */
	@Column(name = "sector_id", columnDefinition = "uuid")
	private UUID sectorId;

	/**
	 * User id
	 */
	@Column(name = "user_id", columnDefinition = "uuid")
	private UUID userId;
}
