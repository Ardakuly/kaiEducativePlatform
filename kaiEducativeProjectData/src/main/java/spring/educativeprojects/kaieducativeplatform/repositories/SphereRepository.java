package spring.educativeprojects.kaieducativeplatform.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import spring.educativeprojects.kaieducativeplatform.entities.Sphere;

import java.util.Optional;


public interface SphereRepository extends CrudRepository<Sphere, Integer> {

     Optional<Sphere> findByName(String name);

}
