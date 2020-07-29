package org.test.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.test.model.User;

/**
 *  UserRepository : Data JPA repository for User entity
 */
public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String username);
}
