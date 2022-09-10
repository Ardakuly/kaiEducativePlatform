package spring.educativeprojects.kaieducativeplatform.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import spring.educativeprojects.kaieducativeplatform.dto.LessonDTO;
import spring.educativeprojects.kaieducativeplatform.exceptions.BadRequestException;
import spring.educativeprojects.kaieducativeplatform.exceptions.NameFormatException;
import spring.educativeprojects.kaieducativeplatform.exceptions.NumberFormatException;
import spring.educativeprojects.kaieducativeplatform.exceptions.ResourceNotFoundException;
import spring.educativeprojects.kaieducativeplatform.services.LessonService;
import spring.educativeprojects.kaieducativeplatform.services.datajpa.AwsService;
import spring.educativeprojects.kaieducativeplatform.validators.ValidatorLesson;

import javax.transaction.Transactional;
import java.util.Set;

@Controller
@RequestMapping("/sphere/v1/course/module")
public class LessonController {

    private final LessonService serviceLessons;
    private final AwsService awsService;

    @Autowired
    public LessonController(LessonService serviceLessons, AwsService awsService) {
        this.serviceLessons = serviceLessons;
        this.awsService = awsService;
    }

    @RequestMapping("/{name}/lesson/allLessons")
    public ResponseEntity<Set<LessonDTO>> findAllCourses(@PathVariable String name) {

        Set<LessonDTO> lessons = serviceLessons.findAllByModule(name);

        if (ValidatorLesson.lessonIsEmptyValidator(lessons)) {
            throw new ResourceNotFoundException("Не один урок не было зафиксированно в базе данных");
        }
        return new ResponseEntity<Set<LessonDTO>>(lessons, HttpStatus.OK);

    }

    @RequestMapping("/lesson/id/{id}")
    public ResponseEntity<LessonDTO> findById(@PathVariable String id) {

        if (ValidatorLesson.idLessonEmptyValidator(id)) {
            throw new BadRequestException("Введенная значение пустой");
        }else if (ValidatorLesson.idLessonValidator(id)) {
            throw new NumberFormatException("Введенный ID являться недействительной");
        }

        LessonDTO lessonDTO = serviceLessons.findById(new Integer(id));

        if (lessonDTO == null) {
            throw new ResourceNotFoundException("Введенный ID не был найден в базе данных.");
        }

        return new ResponseEntity<LessonDTO>(lessonDTO, HttpStatus.OK);
    }

    @RequestMapping("/lesson/name/{name}")
    public ResponseEntity<LessonDTO> findByName(@PathVariable String name) {

        if (ValidatorLesson.nameLessonEmptyValidator(name)) {
            throw new BadRequestException("Введенная значение пустой");
        }else if (ValidatorLesson.nameLessonValidator(name)) {
            throw new NameFormatException("Значение внесенный в поле 'Название' является недействительной.");
        }

        LessonDTO lessonDTO = serviceLessons.findByName(name);

        if (lessonDTO == null) {
            throw new ResourceNotFoundException("Введенный ID не был найден в базе данных.");
        }

        return new ResponseEntity<LessonDTO>(lessonDTO,HttpStatus.OK);
    }

    @PostMapping("/lesson/modification/new")
    public ResponseEntity<LessonDTO> createNewLesson(@RequestBody LessonDTO lessonDTO) {

        if (ValidatorLesson.nameLessonEmptyValidator(lessonDTO.getName())) {
            throw new BadRequestException("Введенная значение пустой");
        }else if (ValidatorLesson.nameLessonValidator(lessonDTO.getName())) {
            throw new NameFormatException("Значение внесенный в поле 'Название' является недействительной.");
        }

        LessonDTO lessonDTO1  = serviceLessons.save(lessonDTO);

        if (lessonDTO1 == null) {
            throw new BadRequestException(String.format("Урок с названием %s уже существует.", lessonDTO.getName()));
        }

        return new ResponseEntity<LessonDTO>(lessonDTO1, HttpStatus.CREATED);
    }

    @PutMapping("/lesson/modification/update/{id}")
    public ResponseEntity<LessonDTO> updateLesson(@RequestBody LessonDTO lessonDTO, @PathVariable String id) {

        if (ValidatorLesson.idLessonEmptyValidator(id)) {
            throw new BadRequestException("Введенная значение пустой");
        }else if (ValidatorLesson.idLessonValidator(id)) {
            throw new NumberFormatException("Введенный ID являться недействительной");
        }else if (ValidatorLesson.nameLessonEmptyValidator(lessonDTO.getName())) {
            throw new BadRequestException("Введенная значение пустой");
        }else if (ValidatorLesson.nameLessonValidator(lessonDTO.getName())) {
            throw new NameFormatException("Значение внесенный в поле 'Название' является недействительной.");
        }

        if (ValidatorLesson.nullLessonValidator(serviceLessons.findById(new Integer(id)))) {
            throw new ResourceNotFoundException("Введенный ID не был найден в базе данных.");
        }

        LessonDTO returned = serviceLessons.updateLesson(lessonDTO, new Integer(id));

        return new ResponseEntity<LessonDTO>(returned, HttpStatus.OK);
    }

    @DeleteMapping("/lesson/modification/delete")
    public ResponseEntity<Void> delete(@RequestBody LessonDTO lessonDTO) {

        if (ValidatorLesson.nameLessonEmptyValidator(lessonDTO.getName())) {
            throw new BadRequestException("Введенная значение пустой");
        }else if (ValidatorLesson.nameLessonValidator(lessonDTO.getName())) {
            throw new NameFormatException("Значение внесенный в поле 'Название' является недействительной.");
        }else if (ValidatorLesson.nullLessonValidator(serviceLessons.findByName(lessonDTO.getName()))) {
            throw new ResourceNotFoundException("Введенный урок не был найден в базе данных.");
        }

        serviceLessons.delete(lessonDTO);
        return new ResponseEntity<Void>(HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/lesson/modification/delete/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable String id) {

        if (ValidatorLesson.idLessonEmptyValidator(id)) {
            throw new BadRequestException("Введенная значение пустой");
        }else if (ValidatorLesson.idLessonValidator(id)) {
            throw new NumberFormatException("Введенный ID являться недействительной");
        }else if (ValidatorLesson.nullLessonValidator(serviceLessons.findById(new Integer(id)))) {
            throw new ResourceNotFoundException("Введенный ID не был найден в базе данных.");
        }

        serviceLessons.deleteById(new Integer(id));
        return new ResponseEntity<Void>(HttpStatus.ACCEPTED);
    }


}
