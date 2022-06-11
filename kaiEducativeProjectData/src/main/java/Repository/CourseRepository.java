package Repository;

import org.springframework.data.repository.CrudRepository;
import spring.educativeprojects.kaieducativeplatform.entities.Course;

public interface CourseRepository extends CrudRepository<Course, Integer> {
}
