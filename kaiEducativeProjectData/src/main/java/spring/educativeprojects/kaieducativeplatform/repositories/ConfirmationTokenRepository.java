package spring.educativeprojects.kaieducativeplatform.repositories;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import spring.educativeprojects.kaieducativeplatform.entities.ConfirmationToken;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Optional;

public interface ConfirmationTokenRepository extends CrudRepository<ConfirmationToken, Integer> {

    Optional<ConfirmationToken> findByToken(String token);

    @Transactional
    @Modifying
    @Query("update ConfirmationToken ct set ct.confirmedAt = ?2 where ct.token = ?1")
    int setConfirmedAt(String token, LocalDateTime confirmedAt);
}
