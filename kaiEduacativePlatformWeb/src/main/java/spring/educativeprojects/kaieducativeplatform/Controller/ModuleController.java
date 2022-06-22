package spring.educativeprojects.kaieducativeplatform.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import spring.educativeprojects.kaieducativeplatform.dto.CourseDTO;
import spring.educativeprojects.kaieducativeplatform.dto.InstructorDTO;
import spring.educativeprojects.kaieducativeplatform.dto.ModuleDTO;
import spring.educativeprojects.kaieducativeplatform.dto.SphereDTO;
import spring.educativeprojects.kaieducativeplatform.entities.Module;
import spring.educativeprojects.kaieducativeplatform.services.ModuleService;

import java.util.HashSet;
import java.util.Set;

@Controller
@RequestMapping("/module/v1")
public class ModuleController {

     private final ModuleService serviceModules;

     @Autowired
    public ModuleController(ModuleService serviceModules) {
        this.serviceModules = serviceModules;
    }

    @RequestMapping("/allModules")
    public ResponseEntity<Set<ModuleDTO>> findAllCourses() {

        return new ResponseEntity<Set<ModuleDTO>>(new HashSet<>(serviceModules.findAll()), HttpStatus.OK);
    }

    @RequestMapping("/{id}")
    public ResponseEntity<ModuleDTO> findById(@PathVariable String id) {

        return new ResponseEntity<ModuleDTO>(serviceModules.findById(new Integer(id)), HttpStatus.OK);
    }

    @PostMapping("/new")
    public ResponseEntity<ModuleDTO> createNewSphere(@RequestBody ModuleDTO moduleDTO) {

        return new ResponseEntity<ModuleDTO>(serviceModules.save(moduleDTO), HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ModuleDTO> updateModule(@RequestBody ModuleDTO moduleDTO, @PathVariable Integer id) {
        return new ResponseEntity<ModuleDTO>(serviceModules.updateModule(moduleDTO,id), HttpStatus.OK);
    }

    @PatchMapping("/update/{name}")
    public ResponseEntity<ModuleDTO> addLesson(@RequestBody ModuleDTO moduleDTO, @PathVariable String name) {
         return new ResponseEntity<ModuleDTO>(serviceModules.addLessons(moduleDTO,name), HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> delete(@RequestBody ModuleDTO moduleDTO) {
         serviceModules.delete(moduleDTO);
         return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable String id) {
        serviceModules.deleteById(new Integer(id));
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
}
