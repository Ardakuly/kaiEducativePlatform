package spring.educativeprojects.kaieducativeplatform.services.datajpa;

import spring.educativeprojects.kaieducativeplatform.converters.AuthorDTOToAuthorConverter;
import spring.educativeprojects.kaieducativeplatform.converters.AuthorToAuthorDTOConverter;
import spring.educativeprojects.kaieducativeplatform.dto.AuthorDTO;
import spring.educativeprojects.kaieducativeplatform.entities.Module;
import spring.educativeprojects.kaieducativeplatform.repositories.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.educativeprojects.kaieducativeplatform.entities.Author;
import spring.educativeprojects.kaieducativeplatform.services.AuthorService;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository repositoryAuthor;

    private final AuthorDTOToAuthorConverter converterAuthor;

    private final AuthorToAuthorDTOConverter converterAuthorDTO;

    @Autowired
    public AuthorServiceImpl(AuthorRepository repositoryAuthor, AuthorDTOToAuthorConverter converterAuthor,
                             AuthorToAuthorDTOConverter converterAuthorDTO) {
        this.repositoryAuthor = repositoryAuthor;
        this.converterAuthor = converterAuthor;
        this.converterAuthorDTO = converterAuthorDTO;
    }

    //------------------ Common Methods -----------------//

    @Override
    public Set<AuthorDTO> findAll() {
        Set<AuthorDTO> authorsDTO = new HashSet<>();
        repositoryAuthor.findAll().forEach(course -> authorsDTO.add(converterAuthorDTO.convert(course)));
        return authorsDTO;
    }

    @Override
    public AuthorDTO findById(Integer id) {
        Optional<Author> optAuthor = repositoryAuthor.findById(id);

        Author author = null;
        if (optAuthor.isPresent())  author = optAuthor.get();

        return converterAuthorDTO.convert(author);
    }

    @Override
    public AuthorDTO findByName(String name) {
        Optional<Author> optAuthor = repositoryAuthor.findByName(name);

        Author author = null;
        if (optAuthor.isPresent())  author = optAuthor.get();

        return converterAuthorDTO.convert(author);
    }

    @Override
    public AuthorDTO updateAuthor(AuthorDTO authorDTO, Integer id) {
        Author author = converterAuthor.convert(authorDTO);
        author.setId(id);
        Author authorReturned = repositoryAuthor.save(author);
        return converterAuthorDTO.convert(authorReturned);
    }

    @Override
    public AuthorDTO save(AuthorDTO authorDTO) {

         Author author = repositoryAuthor.save(converterAuthor.convert(authorDTO));

         return converterAuthorDTO.convert(author);
    }

    @Override
    public void delete(AuthorDTO authorDTO) {
        Author author = repositoryAuthor.findByName(authorDTO.getName()).get();
        repositoryAuthor.delete(author);
    }

    @Override
    public void deleteById(Integer id) {
        repositoryAuthor.deleteById(id);
    }

    //----------------------END------------------------------//
}
