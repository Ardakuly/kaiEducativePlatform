package spring.educativeprojects.kaieducativeplatform.services.datajpa;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import spring.educativeprojects.kaieducativeplatform.entities.*;
import spring.educativeprojects.kaieducativeplatform.exceptions.BadRequestException;
import spring.educativeprojects.kaieducativeplatform.repositories.UserRepository;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    private final UserRepository repositoryUser;
    private final ConfirmationTokenServiceImpl confirmationTokenService;

    @Autowired
    public UserDetailServiceImpl(UserRepository repositoryUser, ConfirmationTokenServiceImpl confirmationTokenService) {
        this.repositoryUser = repositoryUser;
        this.confirmationTokenService = confirmationTokenService;

    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> user = repositoryUser.findByEmail(email);
        user.orElseThrow(() -> new UsernameNotFoundException(String.format("Пользователь под названием %s не был найден.", email)));
        return new AppUserDetails(user.get());
    }

    public String signUp(User user) {
        if (repositoryUser.findByEmail(user.getEmail()).isPresent()) {
            throw new BadRequestException("Почтовый адрес уже используеться другим пользователем.");
        }

        user.setRole(UserRoles.USER);
        user.setPermission(UserAuthorities.READ_WRITE);
        repositoryUser.save(user);

        String token = UUID.randomUUID().toString();

        ConfirmationToken confirmationToken = new ConfirmationToken(
            token, LocalDateTime.now(), LocalDateTime.now().plusMinutes(15), user
        );

        confirmationTokenService.saveConfirmationToken(confirmationToken);

        // TODO:Email

        return token;
    }

    public void enableUser(String email) {
        repositoryUser.enableUser(email);
    }
}
