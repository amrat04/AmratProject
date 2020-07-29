package org.test.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.test.model.Role;

/**
 *  RoleRepository : Data JPA repository for Role entity
 */
public interface RoleRepository extends JpaRepository<Role, Long> {
}
