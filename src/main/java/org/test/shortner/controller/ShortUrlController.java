package org.test.shortner.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
import org.test.dto.AuthResponse;
import org.test.model.User;
import org.test.security.TokenProvider;
import org.test.service.UserService;
import org.test.shortner.common.URLValidator;
import org.test.shortner.dto.AuthenticateUrlRequest;
import org.test.shortner.dto.ShortenRequest;
import org.test.shortner.service.ShortnerServiceConverterService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.net.URISyntaxException;

/**
 *  Controller class for URL Shortner API's
 */
@RestController
public class ShortUrlController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ShortUrlController.class);

    @Autowired
    private ShortnerServiceConverterService shortnerServiceConverterService;

    @Autowired
    private TokenProvider tokenProvider;

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/generatetoken", method=RequestMethod.POST, consumes = {"application/json"})
    public ResponseEntity<?> generateToken(@RequestBody AuthenticateUrlRequest authenticateUrlRequest, HttpServletRequest request) throws Exception {
        LOGGER.info("Received url to Generating Token for username: " + authenticateUrlRequest.getUsername());

        User user = userService.findByEmail(authenticateUrlRequest.getUsername());

        String token = tokenProvider.createToken(user.getId());
        user.setToken(token);
        userService.updateUser(user);
        return ResponseEntity.ok(new AuthResponse(token));
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = "/shorturl", method=RequestMethod.POST, consumes = {"application/json"})
    public String shortenUrl(@RequestBody @Valid final ShortenRequest shortenRequest, HttpServletRequest request) throws Exception {
        LOGGER.info("Received url to shorten: " + shortenRequest.getUrl());
        String longUrl = shortenRequest.getUrl();
        if (URLValidator.INSTANCE.validateURL(longUrl)) {
            String localURL = request.getRequestURL().toString();
            String shortenedUrl = shortnerServiceConverterService.shortenURL(localURL, shortenRequest.getUrl());
            LOGGER.info("Shortened url to: " + shortenedUrl);
            return shortenedUrl;
        }
        throw new Exception("Please enter a valid URL");
    }

    @RequestMapping(value = "/shorturl/{id}", method=RequestMethod.GET)
    public RedirectView redirectUrl(@PathVariable String id, HttpServletRequest request, HttpServletResponse response) throws IOException, URISyntaxException, Exception {
        LOGGER.info("Received shortened url to redirect: " + id);
        String redirectUrlString = shortnerServiceConverterService.getLongURLFromID(id);
        LOGGER.info("Original URL: " + redirectUrlString);
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl(redirectUrlString);
        return redirectView;
    }
}