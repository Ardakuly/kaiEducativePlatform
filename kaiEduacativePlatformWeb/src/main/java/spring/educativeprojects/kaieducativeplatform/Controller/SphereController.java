package spring.educativeprojects.kaieducativeplatform.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import spring.educativeprojects.kaieducativeplatform.dto.SphereDTO;
import spring.educativeprojects.kaieducativeplatform.exceptions.BadRequestException;
import spring.educativeprojects.kaieducativeplatform.exceptions.NameFormatException;
import spring.educativeprojects.kaieducativeplatform.exceptions.NumberFormatException;
import spring.educativeprojects.kaieducativeplatform.exceptions.ResourceNotFoundException;
import spring.educativeprojects.kaieducativeplatform.services.SphereService;
import spring.educativeprojects.kaieducativeplatform.validators.ValidatorSphere;

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

        if (ValidatorSphere.emptySpheresValidator(spheres)) {
            throw new ResourceNotFoundException("Не одна сфера не было зафиксированно в базе данных");
        }

        return new ResponseEntity<Set<SphereDTO>>(spheres , HttpStatus.OK);

    }

    @RequestMapping("/id/{id}")
    public ResponseEntity<SphereDTO> findById(@PathVariable String id) {

        if (ValidatorSphere.idSphereEmptyValidator(id)) throw new BadRequestException("Введенная значение пустой");
        else if (ValidatorSphere.idSphereValidator(id)) throw new NumberFormatException("Введенный ID являться недействительной");

        SphereDTO sphereDTO = serviceSpheres.findById(new Integer(id));

        if (sphereDTO == null) throw new ResourceNotFoundException("Введенный ID не был найден в базе данных.");

        return new ResponseEntity<SphereDTO>(sphereDTO, HttpStatus.OK);
    }

    @RequestMapping("/name/{name}")
    public ResponseEntity<SphereDTO> findByName(@PathVariable String name) {

        if (ValidatorSphere.nameSphereEmptyValidator(name)) {
            throw new BadRequestException("Введенная значение пустой");
        }else if (ValidatorSphere.nameSphereValidator(name)) {
            throw new NameFormatException("Значение внесенный в поле 'Название' является недействительной.");
        }
        SphereDTO sphereDTO = serviceSpheres.findByName(name);

        if(ValidatorSphere.nullSphereValidor(sphereDTO)) {
            throw new ResourceNotFoundException("Введенное название не был найден в базе данных.");
        }

        return new ResponseEntity<SphereDTO>(sphereDTO, HttpStatus.OK);
    }


    @PostMapping("/modification/new")
    public ResponseEntity<SphereDTO> createNewSphere(@RequestBody SphereDTO sphereDTO) {

        if (ValidatorSphere.nameSphereEmptyValidator(sphereDTO)) {
            throw new BadRequestException("Введенная значение пустой");
        }else if (ValidatorSphere.nameSphereValidator(sphereDTO)) {
            throw new NameFormatException("Значение внесенный в поле 'Название' является недействительной.");
        }

        return new ResponseEntity<SphereDTO>(serviceSpheres.save(sphereDTO), HttpStatus.CREATED);
    }

    @PutMapping("/modification/update/{id}")
    public ResponseEntity<SphereDTO> updateSphere(@RequestBody SphereDTO sphereDTO, @PathVariable String id) {

        if (ValidatorSphere.idSphereEmptyValidator(id)) {
            throw new BadRequestException("Введенная значение пустой");
        }else if (ValidatorSphere.idSphereValidator(id)) {
            throw new NumberFormatException("Введенный ID являться недействительной");
        }else if (ValidatorSphere.nameSphereEmptyValidator(sphereDTO)) {
            throw new BadRequestException("Введенная значение пустой");
        } else if (ValidatorSphere.nameSphereValidator(sphereDTO)) {
            throw new NameFormatException("Значение внесенный в поле 'Название' является недействительной.");
        } else if (serviceSpheres.findById(new Integer(id)) == null) {
            throw new ResourceNotFoundException("Введенный ID не был найден в базе данных.");
        }

        return new ResponseEntity<SphereDTO>(serviceSpheres.updateSphere(sphereDTO,new Integer(id)), HttpStatus.OK);
    }//course exception must be handled in service layer

    @DeleteMapping("/modification/delete/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable String id) {

        if (ValidatorSphere.idSphereEmptyValidator(id)) {
            throw new BadRequestException("Введенная значение пустой");
        }else if (ValidatorSphere.idSphereValidator(id)) {
            throw new NumberFormatException("Веденный формат ID неействительный.");
        }else if (serviceSpheres.findById(new Integer(id)) == null) {
            throw new ResourceNotFoundException("Введенный ID не был найден в базе данных.");
        }



        serviceSpheres.deleteById(new Integer(id));
        return new ResponseEntity<Void>(HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/modification/delete")
    public ResponseEntity<Void> delete(@RequestBody SphereDTO sphereDTO) {

        if (ValidatorSphere.nameSphereEmptyValidator(sphereDTO)) {
            throw new BadRequestException("Введенная значение пустой");
        }else if (ValidatorSphere.nameSphereValidator(sphereDTO)) {
            throw new NameFormatException("Значение внесенный в поле 'Название' является недействительной.");
        }else if (serviceSpheres.findByName(sphereDTO.getName()) == null) {
            throw new ResourceNotFoundException("Введенный имя не был найден в базе данных.");
        }

        serviceSpheres.delete(sphereDTO);
        return new ResponseEntity<Void>(HttpStatus.ACCEPTED);
    }

}
