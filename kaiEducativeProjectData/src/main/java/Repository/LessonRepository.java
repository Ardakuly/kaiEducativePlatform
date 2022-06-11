package Repository;

import org.springframework.data.repository.CrudRepository;
import spring.educativeprojects.kaieducativeplatform.entities.Lesson;

public interface LessonRepository extends CrudRepository<Lesson, Integer> {
}
