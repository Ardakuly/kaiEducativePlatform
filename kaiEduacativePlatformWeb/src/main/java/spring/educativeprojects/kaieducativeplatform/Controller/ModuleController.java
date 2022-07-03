package spring.educativeprojects.kaieducativeplatform.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import spring.educativeprojects.kaieducativeplatform.dto.*;
import spring.educativeprojects.kaieducativeplatform.exceptions.BadRequestException;
import spring.educativeprojects.kaieducativeplatform.exceptions.NameFormatException;
import spring.educativeprojects.kaieducativeplatform.exceptions.NumberFormatException;
import spring.educativeprojects.kaieducativeplatform.exceptions.ResourceNotFoundException;
import spring.educativeprojects.kaieducativeplatform.services.ModuleService;
import spring.educativeprojects.kaieducativeplatform.validators.ValidatorModule;

import java.util.Set;

@Controller
@RequestMapping("/sphere/v1/course")
public class ModuleController {

     private final ModuleService serviceModules;

     @Autowired
    public ModuleController(ModuleService serviceModules) {
        this.serviceModules = serviceModules;
    }

    @RequestMapping("/{name}/module/allModules")
    public ResponseEntity<Set<ModuleDTO>> findAllCourses(@PathVariable String name) {

        Set<ModuleDTO>  modulesDTO = serviceModules.findAllByCourse(name);
        if (ValidatorModule.moduleIsEmptyValidator(modulesDTO)) {
            throw new ResourceNotFoundException("Не один модуль не было зафиксированно в базе данных");
        }
        return new ResponseEntity<Set<ModuleDTO>>(serviceModules.findAllByCourse(name), HttpStatus.OK);
    }

    @RequestMapping("/module/id/{id}")
    public ResponseEntity<ModuleDTO> findById(@PathVariable String id) {

         if (ValidatorModule.idEmptyModuleValidator(id)) {
             throw new BadRequestException("Введенная значение пустой");
         }else if (ValidatorModule.idModuleValidator(id)) {
             throw new NumberFormatException("Введенный ID являться недействительной");
         }

         ModuleDTO moduleDTO = serviceModules.findById(new Integer(id));

         if (ValidatorModule.moduleIsNullValidator(moduleDTO)) {
             throw new ResourceNotFoundException("Введенный ID не был найден в базе данных.");
         }
        return new ResponseEntity<ModuleDTO>(moduleDTO, HttpStatus.OK);
    }

    @RequestMapping("/module/name/{name}")
    public ResponseEntity<ModuleDTO> findByName(@PathVariable String name) {

        if (ValidatorModule.nameIsEmptyModuleValidator(name)) {
            throw new BadRequestException("Введенная значение пустой");
        }



        ModuleDTO moduleDTO = serviceModules.findByName(name);

        if (ValidatorModule.moduleIsNullValidator(moduleDTO)) {
            throw new ResourceNotFoundException("Введенное название не был найден в базе данных.");
        }

        return new ResponseEntity<ModuleDTO>(moduleDTO,HttpStatus.OK);
    }

    @PostMapping("/module/new")
    public ResponseEntity<ModuleDTO> createNewModule(@RequestBody ModuleDTO moduleDTO) {

        if (ValidatorModule.nameIsEmptyModuleValidator(moduleDTO.getName())) {
            throw new BadRequestException("Введенная значение пустой");
        }

         ModuleDTO moduleDTO1 = serviceModules.save(moduleDTO);

        return new ResponseEntity<ModuleDTO>(moduleDTO1, HttpStatus.CREATED);
    }

    @PutMapping("/module/update/{id}")
    public ResponseEntity<ModuleDTO> updateModule(@RequestBody ModuleDTO moduleDTO, @PathVariable String id) {

        if (ValidatorModule.idEmptyModuleValidator(id)) {
            throw new BadRequestException("Введенная значение пустой");
        }else if (ValidatorModule.idModuleValidator(id)) {
            throw new NumberFormatException("Введенный ID являться недействительной");
        }else if (ValidatorModule.nameIsEmptyModuleValidator(moduleDTO.getName())) {
            throw new BadRequestException("Введенная значение пустой");
        } else if (ValidatorModule.nameModuleValidator(moduleDTO.getName())) {
            throw new NameFormatException("Значение внесенный в поле 'Название' является недействительной.");
        } else if (ValidatorModule.moduleIsNullValidator(serviceModules.findById(new Integer(id)))) {
            throw new ResourceNotFoundException("Введенное ID не был найден в базе данных.");
        }

        return new ResponseEntity<ModuleDTO>(serviceModules.updateModule(moduleDTO,new Integer(id)), HttpStatus.OK);
    }

    @PatchMapping("/module/update/{name}")
    public ResponseEntity<ModuleDTO> addLesson(@RequestBody ModuleDTO moduleDTO, @PathVariable String name) {

        if (ValidatorModule.nameIsEmptyModuleValidator(name)) {
            throw new BadRequestException("Введенная значение пустой");
        }else if (ValidatorModule.nameModuleValidator(name)) {
            throw new NumberFormatException("Введенное название являться недействительной");
        }else if (ValidatorModule.nameIsEmptyModuleValidator(moduleDTO.getName())) {
            throw new BadRequestException("Введенная значение пустой");
        } else if (ValidatorModule.nameModuleValidator(moduleDTO.getName())) {
            throw new NameFormatException("Значение внесенный в поле 'Название' является недействительной.");
        } else if (ValidatorModule.moduleIsNullValidator(serviceModules.findByName(name))) {
            throw new ResourceNotFoundException("Введенное ID не был найден в базе данных.");
        }


         return new ResponseEntity<ModuleDTO>(serviceModules.addLessons(moduleDTO,name), HttpStatus.OK);
    }

    @DeleteMapping("/module/delete")
    public ResponseEntity<Void> delete(@RequestBody ModuleDTO moduleDTO) {

        if (ValidatorModule.nameIsEmptyModuleValidator(moduleDTO.getName())) {
            throw new BadRequestException("Введенная значение пустой");
        } else if (ValidatorModule.nameModuleValidator(moduleDTO.getName())) {
            throw new NameFormatException("Значение внесенный в поле 'Название' является недействительной.");
        } else if (ValidatorModule.moduleIsNullValidator(serviceModules.findByName(moduleDTO.getName()))) {
            throw new ResourceNotFoundException("Введенное ID не был найден в базе данных.");
        }
         serviceModules.delete(moduleDTO);
         return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @DeleteMapping("/module/delete/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable String id) {

        if (ValidatorModule.idEmptyModuleValidator(id)) {
            throw new BadRequestException("Введенная значение пустой");
        }else if (ValidatorModule.idModuleValidator(id)) {
            throw new NumberFormatException("Введенный ID являться недействительной");
        }else if (ValidatorModule.moduleIsNullValidator(serviceModules.findById(new Integer(id)))) {
            throw new ResourceNotFoundException("Введенное ID не был найден в базе данных.");
        }

        serviceModules.deleteById(new Integer(id));
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
}
