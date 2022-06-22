package spring.educativeprojects.kaieducativeplatform.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import spring.educativeprojects.kaieducativeplatform.dto.CourseDTO;
import spring.educativeprojects.kaieducativeplatform.entities.Course;
import spring.educativeprojects.kaieducativeplatform.services.CourseService;

import java.util.HashSet;
import java.util.Set;

@Controller
@RequestMapping("/course/v1")
public class CourseController {

    final private CourseService seriviceCourses;

    @Autowired
    public CourseController(CourseService seriviceCourses) {
        this.seriviceCourses = seriviceCourses;
    }

    @RequestMapping("/allCourses")
    public ResponseEntity<Set<CourseDTO>> findAllCourses() {

        return new ResponseEntity<Set<CourseDTO>>(new HashSet<>(seriviceCourses.findAll()), HttpStatus.OK);
    }
    @RequestMapping("/{id}")
    public ResponseEntity<CourseDTO> findById(@PathVariable String id) {

        return new ResponseEntity<CourseDTO>(seriviceCourses.findById(new Integer(id)), HttpStatus.OK);
    }

    @PostMapping("/new")
    public ResponseEntity<CourseDTO> createNewCourse(@RequestBody CourseDTO courseDTO) {

        return new ResponseEntity<CourseDTO>(seriviceCourses.save(courseDTO), HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<CourseDTO> updateCourse(@RequestBody CourseDTO courseDTO, @PathVariable Integer id) {
        return new ResponseEntity<CourseDTO>(seriviceCourses.updateCourse(courseDTO,id), HttpStatus.CREATED);
    }

    @PatchMapping("/update/{name}")
    public ResponseEntity<CourseDTO> updateModule(@RequestBody CourseDTO courseDTO, @PathVariable String name) {
        return new ResponseEntity<CourseDTO>(seriviceCourses.addModules(courseDTO, name), HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteCourse(@RequestBody CourseDTO courseDTO) {
        seriviceCourses.delete(courseDTO);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteCourse(@PathVariable Integer id) {
        seriviceCourses.deleteById(id);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
}
