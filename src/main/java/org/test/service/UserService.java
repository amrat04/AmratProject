package org.test.service;

/**
 *  UserService : Interface to provide operations on #User Entity
 */
import org.test.dto.UserRegistrationDto;
import org.test.model.User;

public interface UserService {

    void save(UserRegistrationDto userRegistrationDto);

    User findByEmail(String email);

    void updateUser(User user);
}
