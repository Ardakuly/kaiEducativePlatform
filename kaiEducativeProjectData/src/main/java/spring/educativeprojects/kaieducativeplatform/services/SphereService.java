package spring.educativeprojects.kaieducativeplatform.services;

import spring.educativeprojects.kaieducativeplatform.dto.SphereDTO;
import spring.educativeprojects.kaieducativeplatform.entities.Sphere;


public interface SphereService extends CrudService<SphereDTO, Integer> {

    public SphereDTO findByName(String name);

    public SphereDTO updateSphere(SphereDTO sphereDTO, Integer id);

    SphereDTO addCourses(SphereDTO sphereDTO, String name);

}
