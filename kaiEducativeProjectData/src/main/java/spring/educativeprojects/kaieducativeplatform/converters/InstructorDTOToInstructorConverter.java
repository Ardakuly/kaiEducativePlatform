package spring.educativeprojects.kaieducativeplatform.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import spring.educativeprojects.kaieducativeplatform.dto.InstructorDTO;
import spring.educativeprojects.kaieducativeplatform.entities.Instructor;


@Component
public class InstructorDTOToInstructorConverter implements Converter<InstructorDTO, Instructor> {


    @Override
    public Instructor convert(InstructorDTO source) {
        if (source == null) return null;

        final Instructor instructor = new Instructor();

        // instructor.setId(source.getId());
        instructor.setName(source.getName());

        return instructor;
    }
}
