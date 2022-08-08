package spring.educativeprojects.kaieducativeplatform.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import spring.educativeprojects.kaieducativeplatform.entities.UserAuthorities;
import spring.educativeprojects.kaieducativeplatform.entities.UserRoles;
import spring.educativeprojects.kaieducativeplatform.services.datajpa.UserDetailServiceImpl;

@Configuration
@EnableWebSecurity
public class kaiEducativePlatformSecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final UserDetailServiceImpl userDetailService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public kaiEducativePlatformSecurityConfiguration(UserDetailServiceImpl userDetailService,
                                                     BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userDetailService = userDetailService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(daoAuthenticationProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
         http.csrf().disable()
                 .authorizeRequests()
                 .antMatchers("/users/v1/signUp", "/users/v1/confirm**")
                 .permitAll()
                 .antMatchers("/**/modification/**")
                 .hasAuthority(UserAuthorities.READ_WRITE.name())
                 .antMatchers(HttpMethod.GET, "/sphere/v1/**/course/**", "/sphere/v1/course/**/module/**", "/sphere/v1/course/module/**/lesson/**")
                 //.hasRole(UserRoles.PAID_USER.name())
                 .permitAll()
                 .anyRequest()
                 .permitAll()
                 .and().httpBasic();
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProviderVar = new DaoAuthenticationProvider();
        daoAuthenticationProviderVar.setPasswordEncoder(bCryptPasswordEncoder);
        daoAuthenticationProviderVar.setUserDetailsService(userDetailService);
        return daoAuthenticationProviderVar;
    }

}
