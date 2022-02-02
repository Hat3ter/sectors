package com.helmet.sectors.models.entity;

import lombok.Data;
import lombok.Generated;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

@Table(name = "users")
@Entity
@Data
public class User {

	@Id
	@Column(name = "id", columnDefinition = "uuid")
	@Generated
	@GeneratedValue
	private UUID id;

	@NotNull
	@Column(name = "name")
	private String name;

	@OneToMany(mappedBy = "userId", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<AgreedSector> agreedSectors;

	/**
	 * Is agree with terms
	 */
	@NotNull
	@Column(name = "agree_with_terms")
	private boolean agreeWithTerms;
}
