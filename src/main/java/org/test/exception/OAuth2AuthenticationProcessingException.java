package org.test.exception;

import org.springframework.security.core.AuthenticationException;

/**
 *  OAuth2AuthenticationProcessingException  : Created for handling customized AuthenticationException
 */
public class OAuth2AuthenticationProcessingException extends AuthenticationException {
    public OAuth2AuthenticationProcessingException(String msg, Throwable t) {
        super(msg, t);
    }

    public OAuth2AuthenticationProcessingException(String msg) {
        super(msg);
    }
}