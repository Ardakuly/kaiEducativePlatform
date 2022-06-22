package spring.educativeprojects.kaieducativeplatform.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import spring.educativeprojects.kaieducativeplatform.dto.SphereDTO;
import spring.educativeprojects.kaieducativeplatform.entities.Sphere;

@Component
public class SphereToSphereDTOConverter implements Converter<Sphere, SphereDTO> {

    public final CourseToCourseDTOConverter converter;

    @Autowired
    public SphereToSphereDTOConverter(CourseToCourseDTOConverter converter) {
        this.converter = converter;
    }

    @Override
    public SphereDTO convert(Sphere source) {

        if (source == null) return null;

        SphereDTO sphereCommand = new SphereDTO();

        //sphereCommand.setId(source.getId());
        sphereCommand.setName(source.getName());

        if (source.getCourses() != null && source.getCourses().size() > 0) {
            source.getCourses().forEach(course -> sphereCommand.getCoursesDTO().add(converter.convert(course)));
        }

        return sphereCommand;
    }
}
