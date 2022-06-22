package spring.educativeprojects.kaieducativeplatform.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import spring.educativeprojects.kaieducativeplatform.dto.InstructorDTO;
import spring.educativeprojects.kaieducativeplatform.entities.Instructor;

@Component
public class InstructorToInstructorDTOConverter implements Converter<Instructor, InstructorDTO> {


    @Override
    public InstructorDTO convert(Instructor source) {
        if (source == null) return null;

        final InstructorDTO instructorCommand = new InstructorDTO();

       // instructorCommand.setId(source.getId());
        instructorCommand.setName(source.getName());


        return instructorCommand;
    }
}
