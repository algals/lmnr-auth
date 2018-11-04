package lt.luminor.auth.conf;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.stereotype.Component;

import java.security.Principal;


public final class AuthenticatedUser {

    private static DefaultOidcUser defaultOidcUser;
    private static AuthenticatedUser authenticatedUser;

    public String getName() {
        return defaultOidcUser.getName();
    }

    public String getOid(){
        return (String) defaultOidcUser.getClaims().get("oid");
    }

    public String getUniqueName() {return (String) defaultOidcUser.getClaims().get("unique_name");}

    public static AuthenticatedUser get(Authentication authentication){
        if(authenticatedUser==null){
            authenticatedUser = new AuthenticatedUser();
        }
        defaultOidcUser = ((DefaultOidcUser) authentication.getPrincipal());
        return authenticatedUser;
    }
}
