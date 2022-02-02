package com.helmet.sectors.repositories.users;

import com.helmet.sectors.models.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

/**
 * User repository
 */
public interface UserRepository extends JpaRepository<User, UUID> {
}
