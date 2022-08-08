package spring.educativeprojects.kaieducativeplatform.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import spring.educativeprojects.kaieducativeplatform.currentLogUser.IAuthenticationPrincipal;
import spring.educativeprojects.kaieducativeplatform.dto.CourseDTO;
import spring.educativeprojects.kaieducativeplatform.exceptions.BadRequestException;
import spring.educativeprojects.kaieducativeplatform.exceptions.NameFormatException;
import spring.educativeprojects.kaieducativeplatform.exceptions.NumberFormatException;
import spring.educativeprojects.kaieducativeplatform.exceptions.ResourceNotFoundException;
import spring.educativeprojects.kaieducativeplatform.services.CourseService;
import spring.educativeprojects.kaieducativeplatform.services.datajpa.UserDetailServiceImpl;
import spring.educativeprojects.kaieducativeplatform.validators.ValidatorCourse;
import spring.educativeprojects.kaieducativeplatform.validators.ValidatorSphere;

import java.util.Set;

@Controller
@RequestMapping("/sphere/v1")
public class CourseController {

    final private CourseService serviceCourses;

    @Autowired
    public CourseController(CourseService serviceCourses) {
        this.serviceCourses = serviceCourses;
    }

    @RequestMapping("/course/allCourses")
    public ResponseEntity<Set<CourseDTO>> findAllCourses() {

        Set<CourseDTO> coursesDTO = serviceCourses.findAll();
        if (ValidatorCourse.emptyCoursesValidator(coursesDTO)) {
            throw new ResourceNotFoundException("Не один курс не был зафиксирован в базе данных");
        }

        return new ResponseEntity<Set<CourseDTO>>(coursesDTO, HttpStatus.OK);
    }

    @RequestMapping("/{name}/course/allCourses")
    public ResponseEntity<Set<CourseDTO>> findAllCoursesBySphere(@PathVariable String name) {

        if (ValidatorSphere.nameSphereEmptyValidator(name)) {
            throw new BadRequestException("Введенная значение пустой");
        }else if (ValidatorSphere.nameSphereValidator(name)) {
            throw new NameFormatException("Значение внесенный в поле 'Название' является недействительной.");
        }

        Set<CourseDTO> coursesDTO = serviceCourses.findAllCoursesBySphere(name);
        if (ValidatorCourse.emptyCoursesValidator(coursesDTO)) {
            throw new ResourceNotFoundException("Не один курс не был зафиксирован в базе данных");
        }
        
        return new ResponseEntity<Set<CourseDTO>>(coursesDTO, HttpStatus.OK);
    }

    @RequestMapping("/course/id/{id}")
    public ResponseEntity<CourseDTO> findById(@PathVariable String id) {

        if (ValidatorCourse.idCourseEmptyValidator(id)) {
            throw new BadRequestException("Введенная значение пустой");
        }else if (ValidatorCourse.idCourseValidator(id)) {
            throw new NumberFormatException("Введенный ID являться недействительной");
        }

        CourseDTO courseDTO = serviceCourses.findById(new Integer(id));
        if (ValidatorCourse.nullCourseValidator(courseDTO)) {
            throw new ResourceNotFoundException("Введенный ID не был найден в базе данных.");
        }

        return new ResponseEntity<CourseDTO>(courseDTO, HttpStatus.OK);
    }

    @RequestMapping("/course/name/{name}")
    public ResponseEntity<CourseDTO> findByName(@PathVariable String name) {

        if (ValidatorCourse.nameCourseEmptyValidator(name)) {
            throw new BadRequestException("Введенная значение пустой");
        }else if (ValidatorCourse.nameCourseValidator(name)) {
            throw new NameFormatException("Значение внесенный в поле 'Название' является недействительной.");
        }
        CourseDTO courseDTO = serviceCourses.findByName(name);

        if(ValidatorCourse.nullCourseValidator(courseDTO)) {
            throw new ResourceNotFoundException("Введенное название не был найден в базе данных.");
        }
        return new ResponseEntity<CourseDTO>(courseDTO,HttpStatus.OK);
    }


    @PostMapping("/course/modification/new")
    public ResponseEntity<CourseDTO> createNewCourse(@RequestBody CourseDTO courseDTO) {

        if (ValidatorCourse.nameCourseEmptyValidator(courseDTO.getName())) {
            throw new BadRequestException("Введенная значение пустой");
        }else if (ValidatorCourse.nameCourseValidator(courseDTO.getName())) {
            throw new NameFormatException("Значение внесенный в поле 'Название' является недействительной.");
        }

        CourseDTO courseDTO1 = serviceCourses.save(courseDTO);

        if (courseDTO1 == null) {
            throw new BadRequestException(String.format("Курс с названием %s уже существует", courseDTO.getName()));
        }

        return new ResponseEntity<CourseDTO>(courseDTO1, HttpStatus.CREATED);
    }

    @PutMapping("/course/modification/update/id/{id}")
    public ResponseEntity<CourseDTO> updateCourse(@RequestBody CourseDTO courseDTO, @PathVariable String id) {

        if (ValidatorCourse.idCourseEmptyValidator(id)) {
            throw new BadRequestException("Введенная значение пустой");
        }else if (ValidatorCourse.idCourseValidator(id)) {
            throw new NumberFormatException("Введенный ID являться недействительной");
        } else if (ValidatorCourse.nullCourseValidator(serviceCourses.findById(new Integer(id)))) {
            throw new ResourceNotFoundException("Введенный ID не был найден в базе данных.");
        }

        return new ResponseEntity<CourseDTO>(serviceCourses.updateCourse(courseDTO,new Integer(id)), HttpStatus.CREATED);
    }


    @DeleteMapping("/course/modification/delete")
    public ResponseEntity<Void> deleteCourse(@RequestBody CourseDTO courseDTO) {

        if (ValidatorCourse.nameCourseEmptyValidator(courseDTO.getName())) {
            throw new BadRequestException("Введенная значение пустой");
        }else if (ValidatorCourse.nameCourseValidator(courseDTO.getName())) {
            throw new NumberFormatException("Веденный формат названия недействительный.");
        }else if (ValidatorCourse.nullCourseValidator(serviceCourses.findByName(courseDTO.getName()))) {
            throw new ResourceNotFoundException("Введенное название не был найден в базе данных.");
        }


        serviceCourses.delete(courseDTO);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @DeleteMapping("/course/modification/delete/{id}")
    public ResponseEntity<Void> deleteCourseById(@PathVariable String id) {

        if (ValidatorCourse.idCourseEmptyValidator(id)) {
            throw new BadRequestException("Введенная значение пустой");
        } else if (ValidatorCourse.idCourseValidator(id)) {
            throw new NumberFormatException("Веденный формат ID неействительный.");
        } else if (ValidatorCourse.nullCourseValidator(serviceCourses.findById(new Integer(id)))) {
            throw new ResourceNotFoundException("Введенный ID не был найден в базе данных.");
        }

        serviceCourses.deleteById(new Integer(id));
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
}
