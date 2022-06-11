package Repository;

import org.springframework.data.repository.CrudRepository;
import spring.educativeprojects.kaieducativeplatform.entities.Author;

public interface AuthorRepository extends CrudRepository<Author, Integer> {
}
