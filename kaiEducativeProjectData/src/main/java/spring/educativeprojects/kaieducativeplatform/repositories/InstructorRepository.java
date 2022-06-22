package spring.educativeprojects.kaieducativeplatform.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import spring.educativeprojects.kaieducativeplatform.entities.Instructor;

import java.util.Optional;


public interface InstructorRepository extends CrudRepository<Instructor, Integer> {
     Optional<Instructor> findByName(String name);

}
