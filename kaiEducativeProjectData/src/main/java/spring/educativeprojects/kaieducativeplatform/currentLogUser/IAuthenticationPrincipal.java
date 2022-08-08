package spring.educativeprojects.kaieducativeplatform.currentLogUser;

import org.springframework.security.core.Authentication;

public interface IAuthenticationPrincipal {

    Authentication getAuthentication();

}
