package lt.luminor.auth.conf;

import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class AppSecurity extends WebSecurityConfigurerAdapter {


    private OAuth2UserService<OidcUserRequest, OidcUser> oidcUserService;
    private CustomizeLogoutSuccessHandler customizeLogoutSuccessHandler;


    public AppSecurity(OAuth2UserService<OidcUserRequest, OidcUser> oidcUserService, CustomizeLogoutSuccessHandler customizeLogoutSuccessHandler) {
        this.oidcUserService = oidcUserService;
        this.customizeLogoutSuccessHandler = customizeLogoutSuccessHandler;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .oauth2Login()
                .userInfoEndpoint()
                .oidcUserService(oidcUserService);
        http.logout().logoutSuccessHandler(customizeLogoutSuccessHandler)
                .logoutRequestMatcher(new AntPathRequestMatcher("/app-logout"))
                .logoutSuccessUrl("/login")
                .deleteCookies("JSESSIONID")
                .invalidateHttpSession(true);
    }
}
