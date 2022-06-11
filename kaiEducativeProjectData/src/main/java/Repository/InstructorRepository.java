package Repository;

import org.springframework.data.repository.CrudRepository;
import spring.educativeprojects.kaieducativeplatform.entities.Instructor;

public interface InstructorRepository extends CrudRepository<Instructor, Integer> {
    
}
