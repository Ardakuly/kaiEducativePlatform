package spring.educativeprojects.kaieducativeplatform.services.datajpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.educativeprojects.kaieducativeplatform.exceptions.ResourceNotFoundException;
import spring.educativeprojects.kaieducativeplatform.repositories.ConfirmationTokenRepository;
import spring.educativeprojects.kaieducativeplatform.entities.ConfirmationToken;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class ConfirmationTokenServiceImpl {

    private final ConfirmationTokenRepository confirmationTokenRepository;

    @Autowired
    public ConfirmationTokenServiceImpl(ConfirmationTokenRepository confirmationTokenRepository) {
        this.confirmationTokenRepository = confirmationTokenRepository;
    }

    public void saveConfirmationToken(ConfirmationToken token) {
        confirmationTokenRepository.save(token);
    }

    public ConfirmationToken findByToken(String uuid) {
        Optional<ConfirmationToken> confirmationToken =  confirmationTokenRepository.findByToken(uuid);

        confirmationToken.orElseThrow(() -> new ResourceNotFoundException("Токен не был зарегистрирован в базе данных"));

        return confirmationToken.get();
    }

    public int setConfirmedAt(String token) {
        return confirmationTokenRepository.setConfirmedAt(token, LocalDateTime.now());
    }

}
