package org.test.dto;

import java.util.Map;

/**
 * OAuth2UserInfo : Abstract class created for UserInfo entity that can be extended by multiple oAuth2 UserEntities.
 */
public abstract class OAuth2UserInfo {
    protected Map<String, Object> attributes;

    public OAuth2UserInfo(Map<String, Object> attributes) {
        this.attributes = attributes;
    }

    public Map<String, Object> getAttributes() {
        return attributes;
    }

    public abstract String getId();

    public abstract String getName();

    public abstract String getEmail();

    public abstract String getImageUrl();
}
