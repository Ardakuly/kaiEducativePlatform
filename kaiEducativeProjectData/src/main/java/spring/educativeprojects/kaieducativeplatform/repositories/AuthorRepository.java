package spring.educativeprojects.kaieducativeplatform.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import spring.educativeprojects.kaieducativeplatform.entities.Author;

import java.util.Optional;


public interface AuthorRepository extends CrudRepository<Author, Integer> {

     Optional<Author> findByName(String name);
}
