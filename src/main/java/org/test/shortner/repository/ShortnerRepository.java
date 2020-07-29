package org.test.shortner.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.test.shortner.model.Shortner;

/**
 * ShortnerRepository : Repository class for #Shortner entity
 */
public interface ShortnerRepository extends JpaRepository<Shortner, Long> {
    Shortner getByUniqueId(Long dictionaryKey);
}
