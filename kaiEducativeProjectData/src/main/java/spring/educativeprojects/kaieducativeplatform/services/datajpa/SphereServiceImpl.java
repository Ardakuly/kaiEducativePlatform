package spring.educativeprojects.kaieducativeplatform.services.datajpa;

import spring.educativeprojects.kaieducativeplatform.converters.CourseDTOToCourseConverter;
import spring.educativeprojects.kaieducativeplatform.converters.SphereDTOToSphereConverter;
import spring.educativeprojects.kaieducativeplatform.converters.SphereToSphereDTOConverter;
import spring.educativeprojects.kaieducativeplatform.dto.CourseDTO;
import spring.educativeprojects.kaieducativeplatform.entities.Course;
import spring.educativeprojects.kaieducativeplatform.entities.Sphere;
import spring.educativeprojects.kaieducativeplatform.repositories.CourseRepository;
import spring.educativeprojects.kaieducativeplatform.repositories.SphereRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.educativeprojects.kaieducativeplatform.dto.SphereDTO;
import spring.educativeprojects.kaieducativeplatform.services.SphereService;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class SphereServiceImpl implements SphereService {

    private final SphereRepository repositorySphere;

    private final CourseRepository repositoryCourse;

    private final SphereToSphereDTOConverter converterToSphereDTO;

    private final SphereDTOToSphereConverter converterToSphere;

    private final CourseDTOToCourseConverter converterToCourse;


    @Autowired
    public SphereServiceImpl(SphereRepository repositorySphere,
                             CourseRepository repositoryCourse, SphereToSphereDTOConverter converter,
                             SphereDTOToSphereConverter convertToSphere, CourseDTOToCourseConverter converterToCourse) {
        this.repositorySphere = repositorySphere;
        this.repositoryCourse = repositoryCourse;
        this.converterToSphereDTO = converter;
        this.converterToSphere = convertToSphere;
        this.converterToCourse = converterToCourse;
    }


    //------------------ Common Methods -----------------//

    @Override
    public Set<SphereDTO> findAll() {
        Set<SphereDTO> spheresDTO = new HashSet<>();
        repositorySphere.findAll().forEach(sphere -> spheresDTO.add(converterToSphereDTO.convert(sphere)));
        return spheresDTO;
    }

    @Override
    public SphereDTO findById(Integer id) {
        Optional<Sphere> optSphere = repositorySphere.findById(id);
        Sphere sphere = null;
        if (optSphere.isPresent()) sphere = optSphere.get();
        return converterToSphereDTO.convert(sphere);
    }

    @Override
    public SphereDTO findByName(String name) {

        Optional<Sphere> optSphere = repositorySphere.findByName(name);
        Sphere sphere = null;
        if (optSphere.isPresent()) sphere = optSphere.get();
        return converterToSphereDTO.convert(sphere);
    }

    @Override
    public SphereDTO updateSphere(SphereDTO sphereDTO, Integer id) {
        Sphere sphere = converterToSphere.convert(sphereDTO);
        sphere.setId(id);

        for (Course course : sphere.getCourses()) {
            if (repositoryCourse.findByName(course.getName()).isPresent()) {
                course.setId(repositoryCourse.findByName(course.getName()).get().getId());
            }

        }

        Sphere sphereReturned = repositorySphere.save(sphere);
        return converterToSphereDTO.convert(sphereReturned);
    }

    @Override
    public SphereDTO addCourses(SphereDTO sphereDTO, String name) { //need to be updated.
        Optional<Sphere> optSphere = repositorySphere.findByName(name);
        Sphere sphere = optSphere.get();

        if(sphere.getCourses().size() == 0) {
            sphereDTO.getCoursesDTO().
                    forEach(sphereDTOEach -> sphere.getCourses().add(repositoryCourse.findByName(sphereDTOEach.getName()).get()));
        }else {
            for (CourseDTO courseDTO : sphereDTO.getCoursesDTO()) {
                boolean isThere = false;
                for (Course course : sphere.getCourses()) {
                    if (courseDTO.getName().equals(course.getName())) {
                        isThere = true;
                    }
                }
                if(!isThere) sphere.getCourses().add(repositoryCourse.findByName(courseDTO.getName()).get());
            }
        }

        Sphere returnedSphere = repositorySphere.save(sphere);
        return converterToSphereDTO.convert(returnedSphere);
    }

    @Override
    public SphereDTO save(SphereDTO sphereDTO) {
        Sphere sphere = converterToSphere.convert(sphereDTO);

        repositorySphere.save(sphere);
        return sphereDTO;
    }

    @Override
    public void delete(SphereDTO sphereDTO) {
        Sphere sphere = converterToSphere.convert(sphereDTO);
        Optional<Sphere> optSphere = repositorySphere.findByName(sphereDTO.getName());
        if (optSphere.isPresent()) sphere.setId(optSphere.get().getId());
        repositorySphere.delete(sphere);
    }

    @Override
    public void deleteById(Integer id) {
        repositorySphere.deleteById(id);
    }




    //----------------------END------------------------------//

}
