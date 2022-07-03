package spring.educativeprojects.kaieducativeplatform.services;

import spring.educativeprojects.kaieducativeplatform.dto.AuthorDTO;

public interface AuthorService extends CrudService<AuthorDTO, Integer>{

    public AuthorDTO findByName(String name);

    AuthorDTO updateAuthor(AuthorDTO authorDTO, Integer id);

    AuthorDTO findAuthorByCourse(String name);
}
