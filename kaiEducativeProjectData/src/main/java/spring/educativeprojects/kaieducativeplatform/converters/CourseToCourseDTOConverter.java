package spring.educativeprojects.kaieducativeplatform.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import spring.educativeprojects.kaieducativeplatform.dto.CourseDTO;
import spring.educativeprojects.kaieducativeplatform.entities.Course;

@Component
public class CourseToCourseDTOConverter implements Converter<Course, CourseDTO> {

    public final AuthorToAuthorDTOConverter convertAuthor;

    public final InstructorToInstructorDTOConverter convertInstructor;

    public final ModuleToModuleDTOConverter converterModule;

    @Autowired
    public CourseToCourseDTOConverter(AuthorToAuthorDTOConverter convertAuthor,
                                      InstructorToInstructorDTOConverter convertInstructor,
                                      ModuleToModuleDTOConverter converterModule) {
        this.convertAuthor = convertAuthor;
        this.convertInstructor = convertInstructor;
        this.converterModule = converterModule;
    }

    @Override
    public CourseDTO convert(Course source) {
        if (source == null) return null;

        final CourseDTO courseCommand = new CourseDTO();
        //courseCommand.setId(source.getId());
        courseCommand.setName(source.getName());
        courseCommand.setAuthorDTO(convertAuthor.convert(source.getAuthor()));
        courseCommand.setInstructorDTO(convertInstructor.convert(source.getInstructor()));

        if (source.getModules() != null && source.getModules().size() > 0) {
            source.getModules().forEach(module -> courseCommand.getModulesDTO().add(converterModule.convert(module)));
        }

        return courseCommand;
    }
}
