package org.test.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import org.test.Util.RoleType;
import org.test.dto.UserRegistrationDto;
import org.test.model.Role;
import org.test.model.User;
import org.test.repository.RoleRepository;
import org.test.repository.UserRepository;

import java.util.HashSet;
import java.util.Set;

/**
 *  UserServiceImpl : Service class implementing UserDetailsService
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void save(UserRegistrationDto userRegistrationDto) {
        User user = new User();
        user.setFirst_name(userRegistrationDto.getFirstName());
        user.setLast_name(userRegistrationDto.getLastName());
        user.setPassword(userRegistrationDto.getPassword());
        user.setEmail(userRegistrationDto.getEmail());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Set<Role> roles = new HashSet<Role>();

        //  Setting ADMIN as bydefault ROLE TYPE for every new user created.
        roles.add(new Role(RoleType.ADMIN.name()));
        user.setRoles(roles);

        user.setRoles(new HashSet<>(roleRepository.findAll()));

        userRepository.save(user);

    }

    @Override
    public User findByEmail(String username) {
        return userRepository.findByEmail(username);
    }

    public void updateUser(User user){
        userRepository.save(user);
    }

}
