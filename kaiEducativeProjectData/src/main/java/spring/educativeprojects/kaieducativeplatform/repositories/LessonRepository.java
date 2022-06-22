package spring.educativeprojects.kaieducativeplatform.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import spring.educativeprojects.kaieducativeplatform.entities.Lesson;

import java.util.Optional;


public interface LessonRepository extends CrudRepository<Lesson, Integer> {

    Optional<Lesson> findByName(String name);

}
