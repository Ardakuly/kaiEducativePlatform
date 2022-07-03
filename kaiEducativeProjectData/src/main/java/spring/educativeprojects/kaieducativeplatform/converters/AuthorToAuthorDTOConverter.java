package spring.educativeprojects.kaieducativeplatform.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import spring.educativeprojects.kaieducativeplatform.dto.AuthorDTO;
import spring.educativeprojects.kaieducativeplatform.entities.Author;

@Component
public class AuthorToAuthorDTOConverter implements Converter<Author, AuthorDTO> {


    @Override
    public AuthorDTO convert(Author source) {
        if (source == null) return null;

        final AuthorDTO authorCommand = new AuthorDTO();

        //authorCommand.setId(source.getId());
        authorCommand.setName(source.getName());

        return authorCommand;
    }
}
