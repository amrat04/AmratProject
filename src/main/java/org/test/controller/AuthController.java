package org.test.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.test.Util.AuthProvider;
import org.test.dto.ApiResponse;
import org.test.dto.AuthResponse;
import org.test.dto.LoginRequest;
import org.test.dto.SignUpRequest;
import org.test.exception.BadRequestException;
import org.test.model.User;
import org.test.repository.UserRepository;
import org.test.security.TokenProvider;

import javax.validation.Valid;
import java.net.URI;

/**
 *  Rest Controller : Used based on /auth services for login and registration.
 */
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private TokenProvider tokenProvider;

    /**
     *
     * @param loginRequest
     * @return
     */
    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = tokenProvider.createToken(authentication);
        return ResponseEntity.ok(new AuthResponse(token));
    }

    /**
     *
     * @param signUpRequest
     * @return
     */
    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
        User user = userRepository.findByEmail(signUpRequest.getEmail());
        if(!user.equals(null)  ) {
            throw new BadRequestException("Email address already in use.");
        }

        // Creating user's account
        User user1 = new User();
        user1.setFirst_name(signUpRequest.getName());
        user1.setEmail(signUpRequest.getEmail());
        user1.setPassword(signUpRequest.getPassword());
        user1.setProvider(AuthProvider.local);

        user1.setPassword(passwordEncoder.encode(user1.getPassword()));

        User result = userRepository.save(user1);

        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/user/me")
                .buildAndExpand(result.getId()).toUri();

        return ResponseEntity.created(location)
                .body(new ApiResponse(true, "User registered successfully@"));
    }

}