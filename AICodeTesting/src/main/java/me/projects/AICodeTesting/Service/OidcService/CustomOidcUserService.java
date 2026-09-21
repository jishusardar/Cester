package me.projects.AICodeTesting.Service.OidcService;

import me.projects.AICodeTesting.Service.TestingService;
import org.jspecify.annotations.Nullable;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Service;
@Service
public class CustomOidcUserService implements OAuth2UserService<OidcUserRequest, OidcUser> {
    private final OidcUserService oidcUserService=new OidcUserService();
    private TestingService testingService;
    public CustomOidcUserService(TestingService testingService) {
        this.testingService = testingService;
    }
    @Override
    public @Nullable OidcUser loadUser(OidcUserRequest userRequest) throws OAuth2AuthenticationException {
        OidcUser oidcUser=oidcUserService.loadUser(userRequest);
        testingService.RegisterAndUpdateUser(oidcUser);
        return oidcUser;
    }
}
