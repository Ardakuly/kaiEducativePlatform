package spring.educativeprojects.kaieducativeplatform.converters;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import spring.educativeprojects.kaieducativeplatform.dto.CourseDTO;
import spring.educativeprojects.kaieducativeplatform.entities.Course;


@Component
public class CourseDTOToCourseConverter implements Converter<CourseDTO, Course> {

    private final InstructorDTOToInstructorConverter convertInstructor;

    private final AuthorDTOToAuthorConverter convertAuthor;

    private final ModuleDTOToModuleConverter convertModule;

    @Autowired
    public CourseDTOToCourseConverter(InstructorDTOToInstructorConverter convertInstructor, AuthorDTOToAuthorConverter convertAuthor, ModuleDTOToModuleConverter convertModule) {
        this.convertInstructor = convertInstructor;
        this.convertAuthor = convertAuthor;
        this.convertModule = convertModule;
    }

    @Override
    public Course convert(CourseDTO source) {

        final Course course = new Course();

        //course.setId(source.getId());
        course.setName(source.getName());
        course.setAuthor(convertAuthor.convert(source.getAuthorDTO()));
        course.setInstructor(convertInstructor.convert(source.getInstructorDTO()));
        if (source.getModulesDTO() != null && source.getModulesDTO().size() > 0) {
            source.getModulesDTO().forEach(moduleDTO -> course.getModules().add(convertModule.convert(moduleDTO)));
        }

        return course;
    }
}
