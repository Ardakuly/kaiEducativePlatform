package spring.educativeprojects.kaieducativeplatform.services.datajpa;

import spring.educativeprojects.kaieducativeplatform.converters.AuthorDTOToAuthorConverter;
import spring.educativeprojects.kaieducativeplatform.converters.CourseDTOToCourseConverter;
import spring.educativeprojects.kaieducativeplatform.converters.CourseToCourseDTOConverter;
import spring.educativeprojects.kaieducativeplatform.converters.InstructorDTOToInstructorConverter;
import spring.educativeprojects.kaieducativeplatform.dto.*;
import spring.educativeprojects.kaieducativeplatform.entities.Author;
import spring.educativeprojects.kaieducativeplatform.entities.Instructor;
import spring.educativeprojects.kaieducativeplatform.entities.Module;
import spring.educativeprojects.kaieducativeplatform.entities.Sphere;
import spring.educativeprojects.kaieducativeplatform.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.educativeprojects.kaieducativeplatform.entities.Course;
import spring.educativeprojects.kaieducativeplatform.services.CourseService;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class CourseServiceImpl implements CourseService {

    private final SphereRepository repositorySphere;

    private final CourseRepository repositoryCourse;

    public final AuthorRepository repositoryAuthor;

    public final InstructorRepository repositoryInstructor;

    public final ModuleRepository repositoryModule;

    private final CourseToCourseDTOConverter converterToCourseDTO;

    private final CourseDTOToCourseConverter converterToCourse;

    private final AuthorDTOToAuthorConverter converterAuthor;

    private final InstructorDTOToInstructorConverter converterInstructor;

    @Autowired
    public CourseServiceImpl(SphereRepository repositorySphere, CourseRepository repositoryCourse,
                             AuthorRepository repositoryAuthor, InstructorRepository repositoryInstructor,
                             ModuleRepository repositoryModule, CourseToCourseDTOConverter converterToDTO,
                             CourseDTOToCourseConverter converterToCourse, AuthorDTOToAuthorConverter converterAuthor,
                             InstructorDTOToInstructorConverter converterInstructor) {
        this.repositorySphere = repositorySphere;
        this.repositoryCourse = repositoryCourse;
        this.repositoryAuthor = repositoryAuthor;
        this.repositoryInstructor = repositoryInstructor;
        this.repositoryModule = repositoryModule;
        this.converterToCourseDTO = converterToDTO;
        this.converterToCourse = converterToCourse;
        this.converterAuthor = converterAuthor;
        this.converterInstructor = converterInstructor;
    }

    //------------------ Common Methods -----------------//

    @Override
    public Set<CourseDTO> findAll() {
        Set<CourseDTO> coursesDTO = new HashSet();
        repositoryCourse.findAll().forEach(course -> coursesDTO.add(converterToCourseDTO.convert(course)));
        return coursesDTO;
    }

    @Override
    public Set<CourseDTO> findAllSpheresByCourse(String name) {
        Optional<Sphere> sphereOpt = repositorySphere.findByName(name);

        Set<CourseDTO> coursesDTO = new HashSet<>();

        if (sphereOpt.isPresent()) {
            for (Course course : sphereOpt.get().getCourses()) {
                coursesDTO.add(converterToCourseDTO.convert(course));
            }
        }

        return coursesDTO;
    }

    @Override
    public CourseDTO findById(Integer id) {

        Optional<Course> optCourse = repositoryCourse.findById(id);

        Course course = null;
        if(optCourse.isPresent()) {
            course = optCourse.get();
            return converterToCourseDTO.convert(course);
        }

        return null;
    }

    @Override
    public CourseDTO findByName(String name) {

        Optional<Course> optCourse = repositoryCourse.findByName(name);

        Course course = null;
        if(optCourse.isPresent()) {
            course = optCourse.get();
            return converterToCourseDTO.convert(course);
        }

        return null;
    }

    private Course authorInstructorMapped(Course course) {
        Author tempAuthor = course.getAuthor();
        if (tempAuthor != null) {
            Optional<Author> author = repositoryAuthor.findByName(tempAuthor.getName());
            if (author.isPresent()) course.setAuthor(author.get());
        }

        Instructor tempInstructor = course.getInstructor();
        if (tempInstructor != null) {
            Optional<Instructor> instructor = repositoryInstructor.findByName(tempInstructor.getName());
            if (instructor.isPresent()) course.setInstructor(instructor.get());
        }

        return course;
    }

    @Override
    public CourseDTO save(CourseDTO courseDTO) {
        Course convertedCourse = converterToCourse.convert(courseDTO);

        Course courseMapped = authorInstructorMapped(convertedCourse);

        Course course = repositoryCourse.save(courseMapped);
        return converterToCourseDTO.convert(course);
    }

    @Override
    public CourseDTO updateCourse(CourseDTO courseDTO, Integer id) {
        Course course = converterToCourse.convert(courseDTO);
        course.setId(id);

        for (Module module : course.getModules()) {
            if (repositoryModule.findByName(module.getName()).isPresent()) {
               module.setId(repositoryModule.findByName(module.getName()).get().getId());
            }
        }

        Course courseMapped = authorInstructorMapped(course);

        Course sphereReturned = repositoryCourse.save(courseMapped);

        return converterToCourseDTO.convert(sphereReturned);
    }

    @Override
    public CourseDTO addModules(CourseDTO courseDTO, String name) { //need to be updated.
        Optional<Course> optCourse = repositoryCourse.findByName(name);
        Course course = optCourse.get();

        if(course.getModules().size() == 0) {
            courseDTO.getModulesDTO().
                    forEach(moduleDTOEach -> course.getModules().add(repositoryModule.findByName(moduleDTOEach.getName()).get()));
        }else {
            for (ModuleDTO moduleDTO : courseDTO.getModulesDTO()) {
                boolean isThere = false;
                for (Module module : course.getModules()) {
                    if (moduleDTO.getName().equals(module.getName())) {
                        isThere = true;
                    }
                }
                if(!isThere) course.getModules().add(repositoryModule.findByName(moduleDTO.getName()).get());
            }
        }

        Course returnedCourse = repositoryCourse.save(course);
        return converterToCourseDTO.convert(returnedCourse);
    }


    @Override
    public void delete(CourseDTO courseDTO) {
        Course course = repositoryCourse.findByName(courseDTO.getName()).get();
        repositoryCourse.delete(course);
    }

    @Override
    public void deleteById(Integer id) {
        deleteCourseFromSphere(repositoryCourse.findById(id).get().getName());
        repositoryCourse.deleteById(id);
    }

    @Override
    public Set<CourseDTO> findByAuthor(AuthorDTO authorDTO) {
        Set<CourseDTO> coursesDTO = new HashSet<>();
        repositoryCourse.findByAuthor(converterAuthor.convert(authorDTO)).
                forEach(course -> coursesDTO.add(converterToCourseDTO.convert(course)));
        return coursesDTO;
    }

    @Override
    public Set<CourseDTO> findByInstructor(InstructorDTO instructorDTO) {
        Set<CourseDTO> coursesDTO = new HashSet<>();
        repositoryCourse.findByInstructor(converterInstructor.convert(instructorDTO)).
                forEach(course -> coursesDTO.add(converterToCourseDTO.convert(course)));
        return coursesDTO;
    }

    @Override
    public void deleteCourseFromSphere(String name) {
        Course course = repositoryCourse.findByName(name).get();

        Set<Sphere> spheres = new HashSet<>();
        repositorySphere.findAll().forEach(sphere -> spheres.add(sphere));

        for (Sphere sphere : spheres) {
            for (Course course1 : sphere.getCourses()) {
                if (course1.getName().equals(course.getName())) {
                    sphere.getCourses().remove(course1);
                    repositorySphere.save(sphere);
                }
            }
        }

    }

    //----------------------END------------------------------//
}
