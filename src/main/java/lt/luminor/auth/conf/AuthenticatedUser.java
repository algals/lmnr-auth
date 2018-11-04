package lt.luminor.auth.conf;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.stereotype.Component;

import java.security.Principal;


public class AuthenticatedUser {

    private DefaultOidcUser defaultOidcUser;

    private AuthenticatedUser(Authentication authentication){
        defaultOidcUser = ((DefaultOidcUser) authentication.getPrincipal());
    }

    public String getName() {
        return defaultOidcUser.getName();
    }

    public String getEmail() {
        return defaultOidcUser.getEmail();
    }

    public String getOid(){
        return (String) defaultOidcUser.getClaims().get("oid");
    }

    public static AuthenticatedUser get(Authentication authentication){
        return new AuthenticatedUser(authentication);
    }
}
