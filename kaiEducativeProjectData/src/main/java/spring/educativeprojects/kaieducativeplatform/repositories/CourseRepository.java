package spring.educativeprojects.kaieducativeplatform.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import spring.educativeprojects.kaieducativeplatform.entities.Author;
import spring.educativeprojects.kaieducativeplatform.entities.Course;
import spring.educativeprojects.kaieducativeplatform.entities.Instructor;

import java.util.Optional;
import java.util.Set;

public interface CourseRepository extends CrudRepository<Course, Integer> {

     Optional<Course> findByName(String name);

     Set<Course> findByAuthor(Author author);

     Set<Course> findByInstructor(Instructor instructor);
}
