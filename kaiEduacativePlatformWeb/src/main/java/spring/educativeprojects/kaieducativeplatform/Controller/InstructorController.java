package spring.educativeprojects.kaieducativeplatform.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import spring.educativeprojects.kaieducativeplatform.dto.InstructorDTO;
import spring.educativeprojects.kaieducativeplatform.exceptions.BadRequestException;
import spring.educativeprojects.kaieducativeplatform.exceptions.NameFormatException;
import spring.educativeprojects.kaieducativeplatform.exceptions.NumberFormatException;
import spring.educativeprojects.kaieducativeplatform.exceptions.ResourceNotFoundException;
import spring.educativeprojects.kaieducativeplatform.services.InstructorService;
import spring.educativeprojects.kaieducativeplatform.validators.ValidatorInstructor;
import spring.educativeprojects.kaieducativeplatform.validators.ValidatorModule;


import java.util.Set;

@Controller
@RequestMapping("/instructor/v1")
public class InstructorController {

    private final InstructorService serviceInstructors;

    public InstructorController(InstructorService serviceInstructors) {
        this.serviceInstructors = serviceInstructors;
    }

    @RequestMapping("/allInstructors")
    public ResponseEntity<Set<InstructorDTO>> findAllInstructors() {


        Set<InstructorDTO> instructorsDTO = serviceInstructors.findAll();

        if (ValidatorInstructor.allInstructorIsEmpty(instructorsDTO)) {
            throw new ResourceNotFoundException("Не один инструтор не был зафиксирован в базе данных");
        }

        return new ResponseEntity<Set<InstructorDTO>>(instructorsDTO, HttpStatus.OK);
    }

    @RequestMapping("/course/{name}")
    public ResponseEntity<InstructorDTO> getInstructorByCourse(@PathVariable String name) {

        if (ValidatorInstructor.nameIsEmptyInstructorValidator(name)) {
            throw new BadRequestException("Введенная значение пустой");
        } else if (ValidatorInstructor.nameInstructorValidator(name)) {
            throw new NameFormatException("Значение внесенный в поле 'Название' является недействительной.");
        }

        if (ValidatorInstructor.instructorIsNull(serviceInstructors.getInstructorByCourse(name))) {
            throw new ResourceNotFoundException("Инструктор введенного курса не было найдено в базе данных");
        }

        return new ResponseEntity<InstructorDTO>(serviceInstructors.getInstructorByCourse(name), HttpStatus.OK);
    }

    @RequestMapping("/id/{id}")
    public ResponseEntity<InstructorDTO> findById(@PathVariable String id) {

        if (ValidatorInstructor.idEmptyInstructorValidator(id)) {
            throw new BadRequestException("Введенная значение пустой");
        }else if (ValidatorModule.idModuleValidator(id)) {
            throw new NumberFormatException("Введенный ID являться недействительной");
        }

        InstructorDTO instructorDTO = serviceInstructors.findById(new Integer(id));

        if (ValidatorInstructor.instructorIsNull(instructorDTO)) {
            throw new ResourceNotFoundException("Введенный ID не был найден в базе данных.");
        }

        return new ResponseEntity<InstructorDTO>(instructorDTO, HttpStatus.OK);
    }

    @RequestMapping("/name/{name}")
    public ResponseEntity<InstructorDTO> findByName(@PathVariable String name) {

        if (ValidatorInstructor.nameIsEmptyInstructorValidator(name)) {
            throw new BadRequestException("Введенная значение пустой");
        }else if (ValidatorInstructor.nameInstructorValidator(name)) {
            throw new NameFormatException("Значение внесенный в поле 'Название' является недействительной.");
        }
        InstructorDTO instructorDTO = serviceInstructors.findByName(name);

        if(ValidatorInstructor.instructorIsNull(instructorDTO)) {
            throw new ResourceNotFoundException("Введенное название не был найден в базе данных.");
        }

        return new ResponseEntity<InstructorDTO>(instructorDTO, HttpStatus.OK);
    }

    @PostMapping("/modification/new")
    public ResponseEntity<InstructorDTO> createNewInstructor(@RequestBody InstructorDTO instructorDTO) {

        if (ValidatorInstructor.nameIsEmptyInstructorValidator(instructorDTO.getName())) {
            throw new BadRequestException("Введенная значение пустой");
        }else if (ValidatorInstructor.nameInstructorValidator(instructorDTO.getName())) {
            throw new NumberFormatException("Значение внесенный в поле 'Название' является недействительной.");
        }

        return new ResponseEntity<InstructorDTO>(serviceInstructors.save(instructorDTO), HttpStatus.CREATED);
    }

    @PutMapping("/modification/update/{id}")
    public ResponseEntity<InstructorDTO> updateInstructor(@RequestBody InstructorDTO instructorDTO, @PathVariable String id) {

        if (ValidatorInstructor.idEmptyInstructorValidator(id)) {
            throw new BadRequestException("Введенная значение пустой");
        }else if (ValidatorInstructor.idInstructorValidator(id)) {
            throw new NumberFormatException("Введенный ID являться недействительной");
        }else if (ValidatorInstructor.nameIsEmptyInstructorValidator(instructorDTO.getName())) {
            throw new BadRequestException("Введенная значение пустой");
        } else if (ValidatorInstructor.nameInstructorValidator(instructorDTO.getName())) {
            throw new NameFormatException("Значение внесенный в поле 'Название' является недействительной.");
        } else if (ValidatorInstructor.instructorIsNull(serviceInstructors.findById(new Integer(id)))) {
            throw new ResourceNotFoundException("Введенное ID не был найден в базе данных.");
        }


        return new ResponseEntity<InstructorDTO>(serviceInstructors.findById(new Integer(id)), HttpStatus.OK);
    }

    @DeleteMapping("/modification/delete")
    public ResponseEntity<Void> delete(@RequestBody InstructorDTO instructorDTO) {

        if (ValidatorInstructor.nameIsEmptyInstructorValidator(instructorDTO.getName())) {
            throw new BadRequestException("Введенная значение пустой");
        } else if (ValidatorInstructor.nameInstructorValidator(instructorDTO.getName())) {
            throw new NameFormatException("Значение внесенный в поле 'Название' является недействительной.");
        } else if (ValidatorInstructor.instructorIsNull(serviceInstructors.findByName(instructorDTO.getName()))) {
            throw new ResourceNotFoundException("Введенное ID не был найден в базе данных.");
        }

        serviceInstructors.delete(instructorDTO);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }


    @DeleteMapping("/modification/delete/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable String id) {

        if (ValidatorInstructor.idEmptyInstructorValidator(id)) {
            throw new BadRequestException("Введенная значение пустой");
        }else if (ValidatorInstructor.idInstructorValidator(id)) {
            throw new NumberFormatException("Веденный формат ID недействительный.");
        }else if (ValidatorInstructor.instructorIsNull(serviceInstructors.findById(new Integer(id)))) {
            throw new ResourceNotFoundException("Введенный ID не был найден в базе данных.");
        }


        serviceInstructors.deleteById(new Integer(id));
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
}
