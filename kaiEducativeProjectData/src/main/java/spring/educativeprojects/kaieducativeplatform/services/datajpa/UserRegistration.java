package spring.educativeprojects.kaieducativeplatform.services.datajpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import spring.educativeprojects.kaieducativeplatform.entities.ConfirmationToken;
import spring.educativeprojects.kaieducativeplatform.entities.User;
import spring.educativeprojects.kaieducativeplatform.exceptions.TokenException;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

@Service
public class UserRegistration {

    private final UserDetailServiceImpl uerDetailServiceImpl;
    private final BCryptPasswordEncoder passwordEncoder;
    private final ConfirmationTokenServiceImpl confirmationTokenService;

    @Autowired
    public UserRegistration(UserDetailServiceImpl uerDetailServiceImpl,
                            BCryptPasswordEncoder passwordEncoder,
                            ConfirmationTokenServiceImpl confirmationTokenService) {
        this.uerDetailServiceImpl = uerDetailServiceImpl;
        this.passwordEncoder = passwordEncoder;
        this.confirmationTokenService = confirmationTokenService;
    }

    public String register(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return uerDetailServiceImpl.signUp(user);
    }

    @Transactional
    public String confirm(String token) {

        ConfirmationToken confirmationToken = confirmationTokenService.findByToken(token);

        if (confirmationToken.getConfirmedAt() != null) {
            throw new TokenException("Токен уже был подвержден");
        }

        LocalDateTime expiresAt = confirmationToken.getExpiresAt();

        if (expiresAt.isBefore(LocalDateTime.now())) {
            throw new TokenException("Токен был исчерпан");
        }

        confirmationTokenService.setConfirmedAt(token);

        uerDetailServiceImpl.enableUser(confirmationToken.getUser().getEmail());

        return "Пользователь был подвержден.";

    }
}
