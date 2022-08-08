package spring.educativeprojects.kaieducativeplatform.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import spring.educativeprojects.kaieducativeplatform.dto.SphereDTO;
import spring.educativeprojects.kaieducativeplatform.entities.Sphere;

@Component
public class SphereDTOToSphereConverter implements Converter<SphereDTO, Sphere> {

    public final CourseDTOToCourseConverter converter;

    @Autowired
    public SphereDTOToSphereConverter(CourseDTOToCourseConverter converter) {
        this.converter = converter;
    }

    @Override
    public Sphere convert(SphereDTO source) {
        final Sphere sphere = new Sphere();

        //sphere.setId(source.getId());
        sphere.setName(source.getName());
        sphere.setDescription(source.getDescription());
        if(source != null && source.getCoursesDTO().size() > 0) {
            source.getCoursesDTO().forEach(courseDTO -> sphere.getCourses().add(converter.convert(courseDTO)));
        }
        return sphere;
    }
}
