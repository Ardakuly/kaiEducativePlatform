package spring.educativeprojects.kaieducativeplatform.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import spring.educativeprojects.kaieducativeplatform.dto.AuthorDTO;
import spring.educativeprojects.kaieducativeplatform.dto.ModuleDTO;
import spring.educativeprojects.kaieducativeplatform.services.AuthorService;

import java.util.HashSet;
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

        return new ResponseEntity<Set<AuthorDTO>>(new HashSet<>(serviceAuthors.findAll()), HttpStatus.OK);
    }

    @RequestMapping("/{id}")
    public ResponseEntity<AuthorDTO> findById(@PathVariable String id) {

        return new ResponseEntity<AuthorDTO>(serviceAuthors.findById(new Integer(id)), HttpStatus.OK);
    }
    @PostMapping("/new")
    public ResponseEntity<AuthorDTO> createNewAuthor(@RequestBody AuthorDTO authorDTO) {
        return new ResponseEntity<AuthorDTO>(serviceAuthors.save(authorDTO), HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<AuthorDTO> updateAuthor(@RequestBody AuthorDTO authorDTO, @PathVariable Integer id) {
        return new ResponseEntity<AuthorDTO>(serviceAuthors.updateAuthor(authorDTO,id), HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> delete(@RequestBody AuthorDTO authorDTO) {
        serviceAuthors.delete(authorDTO);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable String id) {
        serviceAuthors.deleteById(new Integer(id));
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

}
