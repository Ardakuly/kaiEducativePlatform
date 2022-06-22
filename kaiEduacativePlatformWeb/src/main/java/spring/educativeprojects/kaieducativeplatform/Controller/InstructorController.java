package spring.educativeprojects.kaieducativeplatform.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import spring.educativeprojects.kaieducativeplatform.dto.AuthorDTO;
import spring.educativeprojects.kaieducativeplatform.dto.CourseDTO;
import spring.educativeprojects.kaieducativeplatform.dto.InstructorDTO;
import spring.educativeprojects.kaieducativeplatform.entities.Instructor;
import spring.educativeprojects.kaieducativeplatform.services.InstructorService;

import java.util.HashSet;
import java.util.Set;

@Controller
@RequestMapping("/instructor/v1")
public class InstructorController {

    private final InstructorService serviceInstructors;

    public InstructorController(InstructorService serviceInstructors) {
        this.serviceInstructors = serviceInstructors;
    }

    @RequestMapping("/allInstructors")
    public ResponseEntity<Set<InstructorDTO>> findAllCourses() {

        return new ResponseEntity<Set<InstructorDTO>>(new HashSet<>(serviceInstructors.findAll()), HttpStatus.OK);
    }

    @RequestMapping("/{id}")
    public ResponseEntity<InstructorDTO> findById(@PathVariable String id) {

        return new ResponseEntity<InstructorDTO>(serviceInstructors.findById(new Integer(id)), HttpStatus.OK);
    }

    @PostMapping("/new")
    public ResponseEntity<InstructorDTO> createNewInstructor(@RequestBody InstructorDTO instructorDTO) {

        return new ResponseEntity<InstructorDTO>(serviceInstructors.save(instructorDTO), HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<InstructorDTO> updateInstructor(@RequestBody InstructorDTO instructorDTO, @PathVariable Integer id) {
        return new ResponseEntity<InstructorDTO>(serviceInstructors.updateInstructor(instructorDTO,id), HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> delete(@RequestBody InstructorDTO instructorDTO) {
        serviceInstructors.delete(instructorDTO);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable String id) {
        serviceInstructors.deleteById(new Integer(id));
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
}
