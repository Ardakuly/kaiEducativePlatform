package spring.educativeprojects.kaieducativeplatform.service;

import org.springframework.stereotype.Service;
import spring.educativeprojects.kaieducativeplatform.entities.Author;
import spring.educativeprojects.kaieducativeplatform.entities.Course;
import spring.educativeprojects.kaieducativeplatform.entities.Instructor;

import java.util.Set;

public interface CourseService extends CrudService {

    public Set<Course> getByAuthor(Author author);

    public Set<Course> getByInstructor(Instructor instructor);

}
