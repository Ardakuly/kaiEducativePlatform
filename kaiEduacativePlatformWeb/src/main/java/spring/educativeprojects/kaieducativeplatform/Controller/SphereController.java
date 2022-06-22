package spring.educativeprojects.kaieducativeplatform.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import spring.educativeprojects.kaieducativeplatform.dto.SphereDTO;
import spring.educativeprojects.kaieducativeplatform.services.SphereService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

@Controller
@RequestMapping("/sphere/v1")
public class SphereController {


    private final SphereService serviceSpheres;

    @Autowired
    public SphereController(SphereService serviceSpheres) {
        this.serviceSpheres = serviceSpheres;
    }

    @RequestMapping("/allSpheres")
    public ResponseEntity<Set<SphereDTO>> findAllSphere() {

        Set<SphereDTO> spheres = serviceSpheres.findAll();

        return new ResponseEntity<Set<SphereDTO>>(spheres , HttpStatus.OK);

    }

    @RequestMapping("/{id}")
    public ResponseEntity<SphereDTO> findById(@PathVariable String id) {

        return new ResponseEntity<SphereDTO>(serviceSpheres.findById(new Integer(id)), HttpStatus.OK);
    }


    @PostMapping("/new")
    public ResponseEntity<SphereDTO> createNewSphere(@RequestBody SphereDTO sphereDTO) {
        return new ResponseEntity<SphereDTO>(serviceSpheres.save(sphereDTO), HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<SphereDTO> updateSphere(@RequestBody SphereDTO sphereDTO, @PathVariable Integer id) {
        return new ResponseEntity<SphereDTO>(serviceSpheres.updateSphere(sphereDTO,id), HttpStatus.OK);
    }

    @PatchMapping("/update/{name}")
    public ResponseEntity<SphereDTO> addCourses(@RequestBody SphereDTO sphereDTO, @PathVariable String name) {
        return new ResponseEntity<SphereDTO>(serviceSpheres.addCourses(sphereDTO, name), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Integer id) {
        serviceSpheres.deleteById(id);
        return new ResponseEntity<Void>(HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> delete(@RequestBody SphereDTO sphereDTO) {
        serviceSpheres.delete(sphereDTO);
        return new ResponseEntity<Void>(HttpStatus.ACCEPTED);
    }

}
