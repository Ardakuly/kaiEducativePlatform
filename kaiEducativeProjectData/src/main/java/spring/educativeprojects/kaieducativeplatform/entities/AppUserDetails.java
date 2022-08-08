package spring.educativeprojects.kaieducativeplatform.entities;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

public class AppUserDetails implements UserDetails {

    private String firstName;
    private String secondName;
    private String password;
    private String email;
    private Boolean locked;
    private Boolean enabled;

    private UserRoles role;

    private UserAuthorities authorities;

    public AppUserDetails(User user) {
        this.firstName = user.getFirstName();
        this.secondName = user.getSecondName();
        this.password = user.getPassword();
        this.email = user.getEmail();
        this.locked = user.getLocked();
        this.enabled = user.getEnabled();
        this.role = user.getRole();
        this.authorities = user.getPermission();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        GrantedAuthority userRoleTemp = new SimpleGrantedAuthority("ROLE_" +role.name());
        GrantedAuthority userAuthTemp = new SimpleGrantedAuthority(authorities.name());

        Set<GrantedAuthority> authorities = new HashSet<>();
        authorities.add(userRoleTemp);
        authorities.add(userAuthTemp);
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return firstName + " " + secondName;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !locked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    @Override
    public String toString() {
        return email;
    }


}
