package org.test.factory;

import org.test.Util.AuthProvider;
import org.test.dto.GoogleOAuth2UserInfo;
import org.test.dto.OAuth2UserInfo;
import org.test.exception.OAuth2AuthenticationProcessingException;

import java.util.Map;

/**
 *  OAuth2UserInfoFactory  : Factory class to created a specific UserInfo class(i.e google, e.tc.) based on the registrationId
 */
public class OAuth2UserInfoFactory {
    public static OAuth2UserInfo getOAuth2UserInfo(String registrationId, Map<String, Object> attributes) {
        if (registrationId.equalsIgnoreCase(AuthProvider.google.toString())) {
            return new GoogleOAuth2UserInfo(attributes);
        } else {
            throw new OAuth2AuthenticationProcessingException("Sorry! Login with " + registrationId + " is not supported yet.");
        }
    }
}
