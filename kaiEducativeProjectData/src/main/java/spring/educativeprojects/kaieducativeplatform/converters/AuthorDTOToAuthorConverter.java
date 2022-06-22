package spring.educativeprojects.kaieducativeplatform.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import spring.educativeprojects.kaieducativeplatform.dto.AuthorDTO;
import spring.educativeprojects.kaieducativeplatform.entities.Author;

@Component
public class AuthorDTOToAuthorConverter implements Converter<AuthorDTO, Author> {


    @Override
    public Author convert(AuthorDTO source) {
        final Author author = new Author();
        //author.setId(source.getId());
        author.setName(source.getName());

        return author;
    }
}
