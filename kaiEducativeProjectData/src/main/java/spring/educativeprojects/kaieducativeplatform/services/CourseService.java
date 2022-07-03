package spring.educativeprojects.kaieducativeplatform.services;

import spring.educativeprojects.kaieducativeplatform.dto.AuthorDTO;
import spring.educativeprojects.kaieducativeplatform.dto.CourseDTO;
import spring.educativeprojects.kaieducativeplatform.dto.InstructorDTO;
import spring.educativeprojects.kaieducativeplatform.dto.SphereDTO;
import spring.educativeprojects.kaieducativeplatform.entities.Author;
import spring.educativeprojects.kaieducativeplatform.entities.Course;
import spring.educativeprojects.kaieducativeplatform.entities.Instructor;

import java.util.Set;

public interface CourseService extends CrudService<CourseDTO, Integer> {

     CourseDTO findByName(String name);
     Set<CourseDTO> findByAuthor(AuthorDTO authorDTO);


     Set<CourseDTO> findByInstructor(InstructorDTO instructorDTO);


     CourseDTO updateCourse(CourseDTO courseDTO, Integer id);

     CourseDTO addModules(CourseDTO courseDTO, String name);

     Set<CourseDTO> findAllSpheresByCourse(String name);

      void deleteCourseFromSphere(String name);

}
