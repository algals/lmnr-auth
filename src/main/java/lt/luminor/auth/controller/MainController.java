package lt.luminor.auth.controller;

import lt.luminor.auth.service.HttpClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;


@Controller
public class MainController {


    @Autowired
    HttpClientService httpClientService;

    @PreAuthorize("hasRole('ROLE_USER')")
    @RequestMapping("/")
    public String helloWorld(Authentication authentication) throws IOException {

        httpClientService.getContacts((((DefaultOidcUser) ((OAuth2AuthenticationToken) authentication).getPrincipal()).getIdToken()).getTokenValue());

        return "user";
    }


}
