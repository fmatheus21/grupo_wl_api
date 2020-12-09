package com.firecode.app.controller.token;

import com.firecode.app.controller.security.UserSecurity;
import java.util.HashMap;
import java.util.Map;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

public class CustomTokenEnhancer implements TokenEnhancer {

    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {

        UserSecurity user = (UserSecurity) authentication.getPrincipal();

        Map<String, Object> info = new HashMap<>();
        info.put("name", user.getUser().getIdPersonPhysical().getName());
        info.put("username", user.getUser().getUsername());

        ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(info);

        return accessToken;
    }

}
