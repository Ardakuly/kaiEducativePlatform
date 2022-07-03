package spring.educativeprojects.kaieducativeplatform.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import spring.educativeprojects.kaieducativeplatform.dto.AuthorDTO;
import spring.educativeprojects.kaieducativeplatform.exceptions.BadRequestException;
import spring.educativeprojects.kaieducativeplatform.exceptions.NameFormatException;
import spring.educativeprojects.kaieducativeplatform.exceptions.NumberFormatException;
import spring.educativeprojects.kaieducativeplatform.exceptions.ResourceNotFoundException;
import spring.educativeprojects.kaieducativeplatform.services.AuthorService;
import spring.educativeprojects.kaieducativeplatform.validators.ValidateAuthor;


import java.util.Set;

@Controller
@RequestMapping("/author/v1")
public class AuthorController {

    private final AuthorService serviceAuthors;

    public AuthorController(AuthorService serviceAuthors) {
        this.serviceAuthors = serviceAuthors;
    }

    @RequestMapping("/allAuthors")
    public ResponseEntity<Set<AuthorDTO>> findAllAuthors() {

        Set<AuthorDTO> allAuthors = serviceAuthors.findAll();

        if (ValidateAuthor.allAuthorsIsEmpty(allAuthors)) {
            throw new ResourceNotFoundException("Не один автор не было зафиксированно в базе данных");
        }

        return new ResponseEntity<Set<AuthorDTO>>(allAuthors, HttpStatus.OK);
    }

    @RequestMapping("/course/{name}")
    public ResponseEntity<AuthorDTO> findAuthorByCourse(@PathVariable String name) {

        AuthorDTO authorDTO = serviceAuthors.findAuthorByCourse(name);

        if (ValidateAuthor.authorIsNull(authorDTO)) {
            throw new ResourceNotFoundException("Автор не был зафиксирован в базе данных");
        }

        return new ResponseEntity<AuthorDTO>(authorDTO, HttpStatus.OK);
    }

    @RequestMapping("/id/{id}")
    public ResponseEntity<AuthorDTO> findById(@PathVariable String id) {

        if (ValidateAuthor.idEmptyAuthorValidator(id)) {
            throw new BadRequestException("Введенная значение пустой");
        }else if (ValidateAuthor.idAuthorValidator(id)) {
            throw new NumberFormatException("Введенный ID являться недействительной");
        }

        AuthorDTO authorDTO = serviceAuthors.findById(new Integer(id));

        if (authorDTO == null) throw new ResourceNotFoundException("Введенный ID не был найден в базе данных.");

        return new ResponseEntity<AuthorDTO>(authorDTO, HttpStatus.OK);
    }

    @RequestMapping("/name/{name}")
    public ResponseEntity<AuthorDTO> findByName(@PathVariable String name) {

        if (ValidateAuthor.nameIsEmptyAuthorValidator(name)) {
            throw new BadRequestException("Введенная значение пустой");
        }else if (ValidateAuthor.nameAuthorValidator(name)) {
            throw new NumberFormatException("Значение внесенный в поле 'Название' является недействительной.");
        }

        AuthorDTO authorDTO = serviceAuthors.findByName(name);

        if (authorDTO == null) throw new ResourceNotFoundException("Введенный ID не был найден в базе данных.");

        return new ResponseEntity<AuthorDTO>(authorDTO, HttpStatus.OK);
    }

    @PostMapping("/new")
    public ResponseEntity<AuthorDTO> createNewAuthor(@RequestBody AuthorDTO authorDTO) {

        if (ValidateAuthor.nameIsEmptyAuthorValidator(authorDTO.getName())) {
            throw new BadRequestException("Введенная значение пустой");
        }else if (ValidateAuthor.nameAuthorValidator(authorDTO.getName())) {
            throw new NumberFormatException("Значение внесенный в поле 'Название' является недействительной.");
        }

        return new ResponseEntity<AuthorDTO>(serviceAuthors.save(authorDTO), HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<AuthorDTO> updateAuthor(@RequestBody AuthorDTO authorDTO, @PathVariable String id) {

        if (ValidateAuthor.idEmptyAuthorValidator(id)) {
            throw new BadRequestException("Введенная значение пустой");
        }else if (ValidateAuthor.idAuthorValidator(id)) {
            throw new NumberFormatException("Введенный ID являться недействительной");
        }else if (ValidateAuthor.nameIsEmptyAuthorValidator(authorDTO.getName())) {
            throw new BadRequestException("Введенная значение пустой");
        } else if (ValidateAuthor.nameAuthorValidator(authorDTO.getName())) {
            throw new NameFormatException("Значение внесенный в поле 'Название' является недействительной.");
        } else if (ValidateAuthor.authorIsNull(serviceAuthors.findById(new Integer(id)))) {
            throw new ResourceNotFoundException("Введенное ID не был найден в базе данных.");
        }

        return new ResponseEntity<AuthorDTO>(serviceAuthors.updateAuthor(authorDTO, new Integer(id)), HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> delete(@RequestBody AuthorDTO authorDTO) {

        if (ValidateAuthor.nameIsEmptyAuthorValidator(authorDTO.getName())) {
            throw new BadRequestException("Введенная значение пустой");
        }else if (ValidateAuthor.nameAuthorValidator(authorDTO.getName())) {
            throw new NameFormatException("Значение внесенный в поле 'Название' является недействительной.");
        }else if (ValidateAuthor.authorIsNull(serviceAuthors.findByName(authorDTO.getName()))) {
            throw new ResourceNotFoundException("Введенный имя не был найден в базе данных.");
        }

        serviceAuthors.delete(authorDTO);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable String id) {

        if (ValidateAuthor.idEmptyAuthorValidator(id)) {
            throw new BadRequestException("Введенная значение пустой");
        }else if (ValidateAuthor.idAuthorValidator(id)) {
            throw new NumberFormatException("Веденный формат ID недействительный.");
        }else if (ValidateAuthor.authorIsNull(serviceAuthors.findById(new Integer(id)))) {
            throw new ResourceNotFoundException("Введенный ID не был найден в базе данных.");
        }

        serviceAuthors.deleteById(new Integer(id));
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

}
