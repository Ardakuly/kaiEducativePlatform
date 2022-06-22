package spring.educativeprojects.kaieducativeplatform.repositories;

import org.springframework.data.repository.CrudRepository;
import spring.educativeprojects.kaieducativeplatform.entities.Module;
import org.springframework.stereotype.Repository;

import java.util.Optional;


public interface ModuleRepository extends CrudRepository<Module, Integer> {

     Optional<Module> findByName(String name);
}
