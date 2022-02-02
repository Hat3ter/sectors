package com.helmet.sectors.models.entity;

import lombok.Data;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Sector entity
 */
@Table(name = "sectors")
@Entity
@Data
public class Sector implements Serializable {

	/**
	 * Id
	 */
	@Id
	@Column(name = "id", columnDefinition = "uuid")
	@GeneratedValue
	private UUID id;

	/**
	 * sector name
	 */
	@Column(name = "sector_name")
	private String sector;

	/**
	 * Agree with terms
	 */
	@Column(name = "agree_terms")
	private boolean isAgreeWithTerms;

	/**
	 * Sector child list
	 */
	@OneToMany(mappedBy = "parentId", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<Sector> sectors = new ArrayList<>(0);

	/**
	 * Parent id(if hasn't parent - null)
	 */
	@Column(name = "parent_id", columnDefinition = "uuid")
	private UUID parentId;
}
