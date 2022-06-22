package spring.educativeprojects.kaieducativeplatform.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import spring.educativeprojects.kaieducativeplatform.dto.InstructorDTO;
import spring.educativeprojects.kaieducativeplatform.dto.LessonDTO;
import spring.educativeprojects.kaieducativeplatform.dto.ModuleDTO;
import spring.educativeprojects.kaieducativeplatform.dto.SphereDTO;
import spring.educativeprojects.kaieducativeplatform.entities.Lesson;
import spring.educativeprojects.kaieducativeplatform.services.LessonService;

import java.util.HashSet;
import java.util.Set;

@Controller
@RequestMapping("/lesson/v1")
public class LessonController {

    private final LessonService serviceLessons;

    @Autowired
    public LessonController(LessonService serviceLessons) {
        this.serviceLessons = serviceLessons;
    }

    @RequestMapping("/allLessons")
    public ResponseEntity<Set<LessonDTO>> findAllCourses() {

        return new ResponseEntity<Set<LessonDTO>>(new HashSet<>(serviceLessons.findAll()), HttpStatus.OK);
    }

    @RequestMapping("/{id}")
    public ResponseEntity<LessonDTO> findById(@PathVariable String id) {

        return new ResponseEntity<LessonDTO>(serviceLessons.findById(new Integer(id)), HttpStatus.OK);
    }

    @PostMapping("/new")
    public ResponseEntity<LessonDTO> createNewLesson(@RequestBody LessonDTO lessonDTO) {

        return new ResponseEntity<LessonDTO>(serviceLessons.save(lessonDTO), HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<LessonDTO> updateLesson(@RequestBody LessonDTO lessonDTO, @PathVariable Integer id) {
        return new ResponseEntity<LessonDTO>(serviceLessons.updateLesson(lessonDTO,id), HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> delete(@RequestBody LessonDTO lessonDTO) {
        serviceLessons.delete(lessonDTO);
        return new ResponseEntity<Void>(HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Integer id) {
        serviceLessons.deleteById(id);
        return new ResponseEntity<Void>(HttpStatus.ACCEPTED);
    }


}
